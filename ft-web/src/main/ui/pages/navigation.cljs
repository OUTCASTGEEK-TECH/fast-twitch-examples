(ns ui.pages.navigation
  (:require [bidi.bidi :refer [path-for]]
            [routing :refer [routing-data]]
            [ui.widgets.core :as ui]
            [ui.components.core :as c]))

(defn navigation []
  [c/navbar
   "YOUR STARTUP."
   {:id "gtrnslt_el"}
   {:title "About" :url "/#about"}
   {:title "Contact" :url (path-for routing-data :contact)}])

(defn navigation-fixed []
  [c/navbar-fixed
   "YOUR STARTUP"
   {:id "gtrnslt_el"}
   {:title "About" :url "/#about"}
   {:title "Contact" :url (path-for routing-data :contact)}])

