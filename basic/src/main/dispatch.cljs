(ns dispatch
  (:require [endpoints :as ep]
            [fast-twitch.web-api :as web]))

(defmulti handle (fn [req-data] (:endpoint req-data)))

;; public
(defmethod handle :home [req-data]
           (ep/home (:req req-data)))

(defmethod handle :home-transit [req-data]
           (ep/home-transit (:req req-data)))

(defmethod handle :home-json [req-data]
           (ep/home-json (:req req-data)))

;; default
(defmethod handle :default [_]
           (web/send "Not Found"))

