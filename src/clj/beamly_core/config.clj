(ns beamly-core.config
  (:import (com.typesafe.config ConfigFactory ConfigResolveOptions)
           (java.io File)
           (java.util List Map)))



(defn strip-quotes
  "strips leading and trailing quotes from a string"
  [s]
  (if (and (.startsWith s "\"")
           (.endsWith s "\""))
    (.substring s 1 (dec (.length s)))
    s))

(defn strip-leading-zeros
  "strips leading zeros from a string"
  [s]
  (if (.startsWith s "0")
    (strip-leading-zeros (.substring s 1 (.length s)))
    s))

(defn parse-number
  "parse a number from a string, handling E notation, strip leading zeros
   and trailing whitespace"
  [s]
  (when (re-find #"^-?\d+\.?\d*([Ee]\+\d+|[Ee]-\d+|[Ee]\d+)?$" (.trim s))
    (read-string (strip-leading-zeros s))))

(defn java-to-clj
  "convert the java representation to clojure one with appropriate types"
  [v]
  (cond
    (instance? Map v) (reduce
                        (fn [acc [k1 v1]]
                          (assoc acc (keyword k1) (java-to-clj v1)))
                        {}
                        (.entrySet v))
    (instance? List v)  (mapv java-to-clj v)
    (string? v)         (let [s (strip-quotes v)] (or (parse-number s) s))
    :else v))


(defn ^:private convert-config-to-map [config]
  (java-to-clj (.unwrapped (.root config))))

(defn ^:private get-class-loader []
  (.getContextClassLoader (Thread/currentThread)))

(defn ^:private get-config-from-file [filename]
  (let [file (new File filename)]
    (ConfigFactory/parseFile file)))

(defn ^:private get-default-conf []
  (ConfigFactory/defaultReference (get-class-loader)))

(defn ^:private get-default-overrides []
  (ConfigFactory/defaultOverrides (get-class-loader)))

(defn ^:private get-resolve-options []
  (ConfigResolveOptions/defaults))

(defn load-and-resolve-config
  "use the typesafe config libs to load and resolve"
  [filename]

  (.resolve
    (.withFallback
      (.withFallback
        (get-default-overrides)
        (get-config-from-file filename))
      (get-default-conf))
    (get-resolve-options)))

(defn load-config [filename]
  "takes the config from the filename, applies the system overrides,
   fallsbacks to default values and resolves"
  (convert-config-to-map (load-and-resolve-config filename)))
