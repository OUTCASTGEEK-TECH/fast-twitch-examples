(ns endpoints
  (:require-macros [cljs.core.async.macros :refer [alt! go]]
                   [fast-twitch.macros :as m])
  (:require [cljs-http.client :as http]
            [cljs.core.async
             :as async
             :refer [<! put! chan close! timeout]]
            [clojure.pprint :refer [pprint]]
            [cljs.nodejs :as nodejs]
            [taoensso.timbre :as log]
            [mount.core :as mount :refer [defstate]]
            [bidi.bidi :refer [path-for]]
            [routing :refer [routing-data]]
            [fast-twitch.os :as os]
            [fast-twitch.web-api :as web]
            [fast-twitch.config :refer [config]]))

(defn home
      [req]
      (web/send :html
                 [:<>
                  [:h1 "Welcome to FastTwitch!"]
                  [:a {:href (path-for routing-data :home-transit)} "Home as Transit Data"]
                  [:br]
                  [:a {:href (path-for routing-data :home-json)} "Home as JSON Data"]]))

(defn home-transit
      [req]
      (go
        (web/send :transit
                  {:msg "Welcome to FastTwitch!"}
                  {:headers {:Content-Type "application/json"}
                   :status  200})))

(defn home-json
      [req]
      (go
        (web/send :json
                  {:msg "Welcome to FastTwitch!"}
                  {:headers {:Content-Type "application/json"}
                   :status  200})))
