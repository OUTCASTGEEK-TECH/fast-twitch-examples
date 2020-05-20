(ns core
  (:require-macros [fast-twitch.macros :as m])
  (:require [cljs.nodejs :as nodejs]
            [mount.core :as mount]
            [taoensso.timbre :as log]
            [fast-twitch.web-api :as web]
            [fast-twitch.server]
            [fast-twitch.config :refer [config]]
            [dispatch :refer [handle]]
            [routing :refer [routing-data]]))

(nodejs/enable-util-print!)

(set! js/XMLHttpRequest xhr2)

(def routes
  (web/routes
    routing-data
    handle))

(defn main []
  (-> (mount/with-args
        {:ft {:routes routes}})
      (mount/start)))

(set! *main-cli-fn* main)
