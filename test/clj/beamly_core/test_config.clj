(ns beamly-core.test-config
  (:use [clojure.test])
  (:require [beamly-core.config :as cfg])
  (:import (java.util HashMap ArrayList)))

(deftest can-remove-quotes-from-string
  (is "s" (cfg/strip-quotes "\"s"))
  (is "s" (cfg/strip-quotes "s\""))
  (is "s" (cfg/strip-quotes "\"s\""))
  (is "s\"s" (cfg/strip-quotes "s\"s"))
  (is "s\"s" (cfg/strip-quotes "\"s\"s\"")))

(deftest can-strip-leading-zeros
  (is "5" (cfg/strip-leading-zeros "5"))
  (is "5" (cfg/strip-leading-zeros "5 "))
  (is "5" (cfg/strip-leading-zeros "05"))
  (is "5" (cfg/strip-leading-zeros "05 "))
  (is "5" (cfg/strip-leading-zeros "00005"))
  (is "500" (cfg/strip-leading-zeros "500"))
  (is "500" (cfg/strip-leading-zeros "00500")))

(deftest can-parse-number
  (is (= 5 (cfg/parse-number "5")))
  (is (= 5.0 (cfg/parse-number "5.0")))
  (is (= 5 (cfg/parse-number "05")))
  (is (= 5.0 (cfg/parse-number "5e0")))
  (is (= 5.0 (cfg/parse-number "5E0")))
  (is (= 5.1 (cfg/parse-number "5.1")))
  (nil? (cfg/parse-number "Not a number"))
  (nil? (cfg/parse-number "10.0.0.1")))

(deftest can-convert-java-map-to-clojure
  (is (= {:a "b"} (cfg/java-to-clj (new HashMap { "a", "b" } ))))
  (is (= {:a 13} (cfg/java-to-clj (new HashMap { "a", 13 } ))))
  (is (= {:a 13} (cfg/java-to-clj (new HashMap { "a", "13" } ))))
  (is (= {:a {:b 13}} (cfg/java-to-clj (new HashMap { "a", (new HashMap { "b", "13" })} ))))
  (is (= [13,14,15] (cfg/java-to-clj (new ArrayList [13,14,15]) )))
  (is (= {:a {:b [13,14]}} (cfg/java-to-clj (new HashMap { "a", (new HashMap { "b", (new ArrayList [13,14]) })} )))))

(deftest can-get-elements-from-reference-config
  (let [conf (cfg/load-config (.getFile (clojure.java.io/resource "example.conf")))]
    (is (= "a value" (-> conf :foo :bar )))
    (is (= 42 (-> conf :foo :baz )))))

(deftest can-get-elements-from-example-config
  (let [conf (cfg/load-config (.getFile (clojure.java.io/resource "example.conf")))]
    (is (= "10 seconds" (-> conf :timeout)))
    (is (= [ 34, 56, 67, "10.0.0.1" ] (-> conf :values)))))