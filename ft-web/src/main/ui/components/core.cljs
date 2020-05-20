(ns ui.components.core
  (:require [ui.components.navbar :as n]
            [ui.components.container :as c]
            [ui.components.footer :as f]
            [ui.components.separator :as s]
            [ui.components.forms :as forms]
            [ui.components.icons :as i]))

(defn devicon [clazz]
      (i/devicon clazz))

(defn navbar [site-name & links]
  (apply n/navbar site-name links))

(defn navbar-fixed [site-name & links]
  (apply n/navbar-fixed site-name links))

(defn contact [id data]
      (forms/contact id data))

(defn lst-index [lst]
      (c/lst-index lst))

(defn center [& content]
  (c/center content))

(defn center-flag [left right]
  (c/center-flag left right))

(defn two-column [id left right]
      (c/two-column id left right))

(defn three-column [id left middle right]
      (c/three-column id left middle right))

(defn quote [text author]
      (c/quote text author))

(defn message [headline hr-color text action]
      (c/message headline hr-color text action))

(defn select [params]
      (forms/select params))

(defn link-button [url text]
      (forms/link-button url text))

(defn footer [copyright company notice]
  (f/footer copyright company notice))

(defn hr [bg-color]
      (s/hr bg-color))

(defn header [background headline subheadline actions]
      (c/header background headline subheadline actions))

;(defn contact []
;      (c/contact))

(defn dangerous [& params]
      (apply c/dangerous params))
