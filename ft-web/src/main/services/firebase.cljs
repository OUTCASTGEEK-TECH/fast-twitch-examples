(ns services.firebase
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs.core.async :as async :refer [>! chan]]))

(defn fire [url]
  "Construct a Firebase ref from the a Firebase url"
  (js/Firebase. url))

                                        ;(defn fire [conf]
                                        ;  "Construct a Firebase ref from the a Firebase url"
                                        ;  (. js/firebase initializeApp conf))

(defn- snapshot-val-callback [ch ss]
  "Extract the value from the snapshot ss and place it on the channel ch"
  (go
    (let [value (js->clj
                 (. ss val)
                 :keywordize-keys true)]
      (>! ch value))))

(defn child [ref name]
  "Returns a firebase ref that corresponding to name"
  (let [child-ref (-> ref
                      (. child name))]
    child-ref))

(defn value [ref name]
  "Returns a channel that will yield the value corresponding to name"
  (let [item-chan (chan 1)]
    (-> ref
        (. child name)
        (. on "value"
           #(snapshot-val-callback item-chan %)))
    item-chan))

