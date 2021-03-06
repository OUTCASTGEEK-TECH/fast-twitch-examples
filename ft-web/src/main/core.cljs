(ns core
  (:require-macros [fast-twitch.macros :as m])
  (:require [cljs.nodejs :as nodejs]
            [mount.core :as mount]
            [taoensso.timbre :as log]
            [fast-twitch.sugar :as ft]
            [fast-twitch.web-api :as web]
            [fast-twitch.server]
            [fast-twitch.config :refer [config]]
            [dispatch :refer [handle]]
            [routing :refer [routing-data]]
            ["morgan" :as logger]
            ["serve-static" :as serve-static]
            ["xhr2" :as xhr2]
            ["body-parser" :as body-parser]
            ["cookie-parser" :as cookie-parser]
            ["csurf" :as csurf]
            ["serve-favicon" :as serve-favicon]
            ["helmet" :as helmet]))

(nodejs/enable-util-print!)

(set! js/XMLHttpRequest xhr2)

(def routes
  (web/routes
   routing-data
   handle))

(defn middlewares []
  (let [static-folder (if-let [STATIC (m/env-var "STATIC")] STATIC (:static @config))
        secret (if-let [SECRET (m/env-var "SECRET")] SECRET (:secret @config))]
    (log/debug "Static Folder: " static-folder)
    (log/debug "Secret: " secret)
    [(serve-static static-folder (clj->js {:index false}))
     (serve-favicon (str static-folder "/img/favicon.ico"))
     (helmet)
     (logger "combined")     ;; Logger
     (body-parser/json)      ;; support json encoded bodies
     (body-parser/urlencoded (clj->js {:extended true})) ;; support encoded bodies
     (cookie-parser secret)
     (csurf (clj->js {:cookie true}))]))

(defn main []
  (-> (mount/with-args
        {:ft {:middlewares (middlewares)
              :routes routes}})
      (mount/start)))

(set! *main-cli-fn* main)

