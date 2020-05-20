(ns endpoints
  (:require-macros [cljs.core.async.macros :refer [alt! go]])
  (:require [cljs-http.client :as http]
            [cljs.core.async :as async :refer [<! put! chan close! timeout]]
            [clojure.pprint :refer [pprint]]
            [taoensso.timbre :as log]
            [cljs.nodejs :as nodejs]
            [bidi.bidi :refer [path-for]]
            [fast-twitch.nav :refer [cached-routes]]
            [fast-twitch.web-api :as web]
            [services.core :as svc]
            [ui.templates.core :as tmpl]
            [ui.widgets.core :as ui]
            [ui.components.core :as c]
            [ui.pages.core :as page]))

(defn contact
      [req]
      (let [{:keys [csrf-token]} req
            data {:csrf-token csrf-token}]
           (web/send :html [tmpl/default-template-ui
                            {:title   "Contact"
                             :content [page/contact data]
                             }])))

(defn do-contact [req]
      (let [{:keys [body]} req]
           (do
             (log/debug body)
             (web/redirect :home))))

(defn home
      [req]
      (let [{:keys [csrf-token]} req
            data {:csrf-token csrf-token}]
           (web/send :html [tmpl/default-template-ui
                            {:title   "Welcome! :-)"
                             :content [page/home data]
                             :styles  [[:href "//cdnjs.cloudflare.com/ajax/libs/devicon/2.2/devicon.min.css"]
                                       [:href "//cdnjs.cloudflare.com/ajax/libs/devicon/2.2/devicon-colors.min.css"]]
                             }])))

(defn handle-response [response grab-data-fn]
      (let [status (:status response)]
           (prn "Status: " status)
           (condp = status
                  200 (grab-data-fn response)
                  401 ["Ouch!!!!"]
                  403 ["Remote Service Denied Access"]
                  404 ["Could not Find Anything"]
                  500 ["Something Broke"])))

(defn render-widget
      [req]
      (web/send :html [tmpl/default-template-ui
                       {:title   "SS Reacting"
                        :content [c/center
                                  [:div {:id "app"} "JS Loading..."]
                                  [ui/hello-ui {:upper-bound 9}]]
                        :scripts [[:src (path-for @cached-routes :javascript :name "web.js")]]
                        }]))

(defn check-github-users
      [req]
      ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
      ;;;; Fetch, Parse, Render, or Timeout  ;;;;
      ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
      (go
        (alt!
          (http/get "https://api.github.com/users" {:with-credentials false}
                    :query-params {:since 135})
          ([response]
           (let [names (handle-response
                         response
                         (fn [resp]
                             (map :login (:body resp))))]
                (prn "Names: " names)
                (web/send :html [tmpl/default-template-ui
                                 {:title   "Github Users"
                                  :content [c/center
                                            [:h1 "Github Users"]
                                            [:hr]
                                            [:ul (for [n names]
                                                      ^{:key n} [:li n])]]
                                  }])))
          (timeout 1000)
          (web/send :html [tmpl/default-template-ui
                           {:title   "Github Users"
                            :content [c/center
                                      [:p "Could not Fetch the Github Users!"]]
                            }])
          )))

(defn check-weather
      [req]
      (log/debug "Request: " (-> req pprint with-out-str))
      ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
      ;;;; Fetch, Parse, Render, or Timeout  ;;;;
      ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
      (go (let [;;params (aget req "params")
                ;;city-query (aget params "city")
                city-query (-> req :route-params :city)]
               (log/debug (str "City Query: " city-query))
               (alt!
                 (http/get (str "http://api.openweathermap.org/data/2.5/weather?q=" city-query "&appid=b6907d289e10d714a6e88b30761fae22"))
                 ([raw-resp]
                  (let [grab-data-fn (fn [resp]
                                         (let [city (-> resp :body :name)
                                               country (-> resp :body :sys :country)
                                               description (-> resp :body :weather first :description)
                                               temperature (-> resp :body :main :temp)]
                                              [city country description temperature]))
                        weather-info (-> raw-resp (js->clj) (handle-response grab-data-fn))]
                       (prn weather-info)
                       (web/send :html [tmpl/default-template-ui
                                        {:title   "Weather"
                                         :content [ui/raw-str-widget-ui
                                                   {:text (clojure.string/join "," weather-info)}]
                                         }])
                       ))
                 (timeout 1000)
                 (web/send :html [tmpl/default-template-ui
                                  {:title   "Weather"
                                   :content "Could not Fetch the Weather Info."
                                   }])
                 ))
          ))

;; PingPong
(defn pong
      [req]
      (web/send "pong"))

;; Application LifeCycle
(defn app-start
      [req]
      (web/send "Started"))

(defn check-health
      [req]
      (web/send "Healthy!"))

(defn app-stop
      [req]
      (web/send "Stopping..."))

