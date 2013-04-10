(ns hylo.viz
  "Visualize the simulation."
  (:require [hylo.core]
            [incanter.core :as i]
            [incanter.charts :as c]))

(defn extract-ts
  "Extract time series for a user from supplied evolution 2d vector."
  [evolution uid]
  (let [ref-ts (nth evolution uid)]
    (map #(-> @% :user :hylo) ref-ts)))

(defn series-plot
  "Add lines to the specified plot reflecting the hylo series."
  [plot series]
  (c/add-lines plot (range (count series)) series :x-label "" :y-label ""))

(defn sim-ts
  "Generate timeseries plot from supplied 2d evolution array of user generations."
  [evolution]
  (let [user-ids (range (count evolution))
        ts-data (map (partial extract-ts evolution) user-ids)
        abs-max (reduce max (map #(reduce max %) ts-data))
        plot (c/xy-plot)]
    (loop [iter  hylo.core/num-users]
      (if (pos? iter)
        (do (series-plot plot (nth ts-data (dec iter)))
            (recur (dec iter)))
        (-> plot
            (c/set-y-range 0 abs-max)
            (c/set-x-label "generation")
            (c/set-y-label "hylos")
            (c/set-x-range 0 100))))))
