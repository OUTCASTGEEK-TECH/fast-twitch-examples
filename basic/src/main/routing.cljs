(ns routing
  (:require [bidi.bidi :refer [path-for]]))

(def routing-data
  ["/"
   ;; public
   {""                                  {:get
                                         {"" :home}}
    "home.transit"                      {:get
                                         {"" :home-transit}}
    "home.json"                         {:get
                                         {"" :home-json}}}])

(defn path-img [name]
  (path-for routing-data :image :name name))

(defn path-js [name]
  (path-for routing-data :javascript :name name))

(defn path-css [name]
  (path-for routing-data :style :name name))

