(ns services.core
  (:require [services.contactsvc :as c]))

(def budget-selection
  c/budget-selection)

(defn contact-ogtech [payload]
      (c/contact-ogtech payload))
