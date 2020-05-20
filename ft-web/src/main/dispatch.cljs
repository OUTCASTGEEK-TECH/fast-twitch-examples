(ns dispatch
  (:require [endpoints :as ep]
            [fast-twitch.web-api :as web]))

(defmulti handle (fn [req-data] (:endpoint req-data)))

(defmethod handle :contact [req-data]
  (ep/contact (:req req-data)))

(defmethod handle :do-contact [req-data]
  (ep/do-contact (:req req-data)))

(defmethod handle :home [req-data]
  (ep/home (:req req-data)))

(defmethod handle :react [req-data]
  (ep/render-widget (:req req-data)))

(defmethod handle :weather [req-data]
  (ep/check-weather (:req req-data)))

(defmethod handle :ghusers [req-data]
  (ep/check-github-users (:req req-data)))

(defmethod handle :start [req-data]
  (ep/app-start (:req req-data)))

(defmethod handle :health [req-data]
  (ep/check-health (:req req-data)))

(defmethod handle :stop [req-data]
  (ep/app-stop (:req req-data)))

(defmethod handle :foo [req-data]
  (ep/home (:req req-data)))

(defmethod handle :default [_]
  (web/send "Not Found"))



