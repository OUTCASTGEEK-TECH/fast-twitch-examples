(ns ui.widgets.core
  (:require [bidi.bidi :refer [path-for]]
            [fast-twitch.nav :refer [cached-routes]]
            [ui.components.core :as c]))

;;;;;;;;;;;;;;;;;;;;;; Widgets

(defn raw-str-widget-ui [data]
  (let [{:keys [text]} data]
    [c/center-flag
     [:img.w-100
      {:src (path-for @cached-routes :image :name "outcastgeeklogo.svg")
       :alt "OUTCASTGEEK INC."}]
     [:p
      [:strong "Welcome to OUTCASTGEEK INC. on the Web!"]
      [:br]
      [:small "This Application is a Clojurescript POC!!"]
      [:br]
      [:strong text]]]
    ))


(defn un-bouton-ui [data]
  (let [{:keys [text]} data]
    [:div
     [:hr]
     [:input {
              :type "submit"
              :class "btn btn-default"
              :value text}]
     ]))


(defn hello-ui [data]
  (let [{:keys [upper-bound]} data]
    [:div "Hello world!"
     [:ul (for [n (range 1 upper-bound)]
            [:li {:key n} n])]
     [un-bouton-ui {:text "React!!"}]]))

