(ns hylo.core
  (:use [midje sweet])
  (:require [incanter.core :as i]
            [incanter.charts :as c]
            [incanter.stats :as s])
  (:import [java.util.concurrent Executors TimeUnit]))

(def init-hylo 10)
(def num-users 10)
(def num-ideas 5)
(def invest-amt 1)
(def dividend-amt 1.5)
(def num-earner 2)

(defstruct user :id :hylo :attr)
(defstruct idea :id :hylo :attr :originator :admin)
(defstruct transaction :user :idea)
(defstruct brilliance :user :idea :generation)
(defstruct generation :generation :user)

(defn- reset-world!
  [& {:keys [config num-runs] :or {config nil num-runs 10}}]
  (if config
    (do
     (def init-hylo (:init_hylos config))
     (def num-users (:num_users config))
     (def num-ideas (:num_ideas config))
     (def invest-amt (:invest_amt config))
     (def dividend-amt (:dividend_amt config))
     (def num-earner (:num_earners config))))
  (def gen-count (ref 0))
  (def world {:users (apply vector
                            (map (fn [x] (ref (struct user x init-hylo (rand))))
                                 (range num-users)))
              :ideas (apply vector
                            (map (fn [x] (ref (struct idea x 0 (rand))))
                                 (range num-ideas)))
              :evolution (apply vector 
                                (map (fn [i] 
                                       (apply vector
                                              (map (fn [x] (ref (struct generation -1))) 
                                                   (range num-runs)))) 
                                     (range num-users)))}))

(def ^:dynamic *pool*
  (Executors/newFixedThreadPool
   (+ 2 (.availableProcessors (Runtime/getRuntime)))))

(defn dothreads!
  [f & {thread-count :threads exec-count :times
        :or {thread-count 1 exec-count 1}}]
  (dotimes [t thread-count]
    (.submit *pool* #(dotimes [_ exec-count] (f)))))

(defn get-generation
  "accepts a user identification and returns the current generation"
  [user-id]
  (-> world :evolution (nth user-id) (nth @gen-count)))

(defn get-user
  "returns the ref to the user with the supplied id"
  [id]
  (-> world :users (nth id)))

(defn get-idea
  "returns the ref to the idea with the supplied id"
  [id]
  (-> world :ideas (nth id)))

(defn idea-invest
  "returns the transaction between a user and an idea, after taking
  the investment amount from the user and adding it to the idea
  wallet."
  [u i]
  (let [[uh ih] (map :hylo [u i])
        [uid iid] (map :id [u i])]
    (if (pos? (- uh invest-amt))
      (do (alter (get-user uid) assoc :hylo (- uh invest-amt))
          (alter (get-idea iid) assoc :hylo (+ ih invest-amt))
          (struct transaction uid iid)))))

(defn shuffle-attr
  "accepts a user or idea struct and alters the attribute of the
  reference with a random variable, distributed uniform on [0,1]"
  [stcr]
  (alter (get-user (:id stcr)) assoc :attr (rand)))

(defn shuffle-stage
  "accepts a sequence of either user or idea structs and alters the
  attributes of each, given the random distribution in the
  shuffle-attr function"
  [sseq]
  (dorun (for [s sseq] (shuffle-attr s))))

(defn simil-val
  "calculate the similarity between the user and idea.  this is a
  stand-in for a more complicated utility function, which will
  determine whether the user invests in the idea"
  [u i]
  (i/abs (- (:attr u) (:attr i))))

(defn best-idea
  "returns the idea within iseq that is the best for the supplied
  user" [u iseq]
  (first (sort-by #(simil-val u %) iseq)))

(defn user-invest
  "returns the transaction of the supplied user choosing to invest in
  the best idea out of all available ideas"
  [u iseq]
  (let [best (best-idea u iseq)
        tseq (apply idea-invest u (filter #(= % best) iseq))]
    tseq))

(defn invest-stage
  "applies user-invest to all users, constituting the investment
  stage"
  [useq iseq]
  (map #(user-invest % iseq) useq))

(defn idea-earners
  "returns a set of idea IDs that are among the top-ranked ideas by
  popularity in a given stage"
  [tseq]
  (let [tseq (filter #(not (nil? %)) tseq)
        fmap (frequencies (map :idea tseq))
        top  (take num-earner (sort-by val > fmap))]
    (set (map first top))))

(defn dividend-stage
  "Accepts a sequence of transaction and alters the hylo amounts of
  each user, conditional on their investment in a top project in the
  previous investment stage. Intuitively, this stage pays dividends to
  users that were able to identify popular projects."
  [tseq]
  (let [top-set (idea-earners tseq)]
    (doall
     (for [t tseq :when (contains? top-set (:idea t))]
       (do
         (let [u (get-user (:user t))
               ui (:hylo @u)]
           (alter u assoc :hylo (+ ui dividend-amt))))))))

(defn track-user-evolution
  "adds the count of user hylos to the evolution map for the current
  generation"
  []
  (doall
   (for [user (:users world)]
     (do
       (let [uid (:id @user)
             generation (get-generation uid)]
         (alter generation assoc :generation  @gen-count :user @user))))))

(defn run
  "Run simulation for a single generation in a transaction."
  []
  (dosync
   (let [users (map deref (:users world))
         ideas (map deref (:ideas world))
         tseq (invest-stage users ideas)]
     (dividend-stage tseq)
     (shuffle-stage users)
     (track-user-evolution)
     (alter gen-count inc))))

(defn go
  "Execute concurrently a simulation function using supplied configuration map.
   Return 2d evolution array of user generations."
  [fn config]
  (let [num-threads (:num_threads config)
        num-gens (:num_generations config)
        num-runs (* num-threads num-gens)]
    (reset-world! :config config :num-runs num-runs)
    (dothreads! fn :threads num-threads :times num-gens)
    (.shutdown *pool*)
    (.awaitTermination *pool* 60 TimeUnit/SECONDS)
    (:evolution world)))

