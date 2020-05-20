(ns ui.pages.core
  (:require [ui.pages.contact :as s]
            [ui.pages.home :as h]
            [ui.pages.navigation :as n]))

(defn contact [data]
  (s/contact data))

(defn home [data]
  (h/home data))

(defn navigation []
  (n/navigation))

