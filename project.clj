(defproject beamly-core/logging "0.0.1"
  :description "Beamly config library, wrapping Typesafe config"
  :url "https://github.com/beamly/beamly-core.logging"
  :license {:name "The MIT License"
            :url "http://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [com.typesafe/config "1.2.1"]]
  :source-paths ["src/clj"]
  :test-paths ["test/clj"]
  :profiles {:dev {:resource-paths ["test/resources"]}})
