(defproject hylo-sim "0.1.0-SNAPSHOT"
  :description "Simulation of hylo behavior"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
    :repositories {"conjars" "http://conjars.org/repo/"}
  :source-paths ["src/clj"]
  :test-paths ["test/clj"]
  :java-source-paths ["src/jvm"]
  :marginalia {:javascript ["mathjax/MathJax.js"]}
  :resources-paths ["resources"]
  :dev-resources-paths ["dev"]
  :aliases {"sim" ["run" "-m" "hylo.sim"]}
  :jvm-opts ["-XX:MaxPermSize=128M"
             "-XX:+UseConcMarkSweepGC"
             "-Xms1024M" "-Xmx1048M" "-server"]
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [org.clojure/tools.cli "0.2.2"]
                 [org.clojure/tools.logging "0.2.3"]
                 [org.clojure/math.numeric-tower "0.0.1"]
                 [incanter/incanter-core "1.3.0"]
                 [clj-time "0.3.4"]
                 [cascalog "1.9.0"]
                 [cartodb-clj "1.5.2"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [cascalog-checkpoint "0.1.1"]
                 [backtype/dfs-datastores "1.1.3"]
                 [backtype/dfs-datastores-cascading "1.2.0"]
                 [org.apache.thrift/libthrift "0.8.0"
                  :exclusions [org.slf4j/slf4j-api]]]
  :profiles {:dev {:dependencies [[org.apache.hadoop/hadoop-core "0.20.2-dev"]
                                  [midje-cascalog "0.4.0"]
                                  [incanter/incanter-charts "1.3.0"]]
                   :plugins [[lein-swank "1.4.4"]
                             [lein-midje "2.0.0-SNAPSHOT"]
                             [lein-emr "0.1.0-SNAPSHOT"]]}})
