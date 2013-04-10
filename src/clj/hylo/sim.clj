(ns hylo.sim
  "Command line interface to running Hylo simulations."
  (:gen-class)
  (:use [hylo.core :as c]
        [hylo.viz :as v]
        [clojure.tools.cli]        
        [clojure.string :only (join)])
  (:require [incanter.core :as i]))

(defn parse-sim-args
  "Parse command line argument string."
  [args]
  (try
    (cli args
         ["-h" "--init_hylos" "Initial number of Hylos per user."
          :parse-fn #(Integer. %)
          :default 10]
         ["-u" "--num_users" "Number of users."
          :parse-fn #(Integer. %)
          :default 10]
         ["-i" "--num_ideas" "Number of ideas."
          :parse-fn #(Integer. %)
          :default 5]
         ["-e" "--num_earners" "Number of earners."
          :parse-fn #(Integer. %)
          :default 2]
         ["-v" "--invest_amt" "Investment ammount."
          :parse-fn #(Double. %)
          :default 1]
         ["-d" "--dividend_amt" "Dividend ammount."
          :parse-fn #(Double. %)
          :default 1.5]
         ["-t" "--num_threads" "Number of threads."
          :parse-fn #(Integer. %)
          :default 10]
         ["-g" "--num_generations" "Number of generations."          
          :parse-fn #(Integer. %)
          :default 10]
         ["-w" "--workspace" "Workspace directory to save graphs to."
          :default "/tmp/hylo-sim.png"])
    (catch Exception e (do (println (.getMessage e)) nil))))

(defn -main
  "Entry point for command line interface."
  [& args]
  (when-let [[args-map trails usage] (parse-sim-args args)]
    (if (= "help" (first trails))
      (println usage)
      (do
        (println "Running Hylo simulation with:" args-map)
        (let [workspace (:workspace args-map)
              evolution (c/go run args-map)]
          (println (format "Simulation completed, creating viz in %s" workspace))
          (i/save (v/sim-ts evolution) workspace)
          (println (format "Done!")))))))
