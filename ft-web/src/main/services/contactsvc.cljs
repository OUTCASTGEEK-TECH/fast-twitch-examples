(ns services.contactsvc
  (:require-macros [fast-twitch.macros :as m])
  (:require [clojure.string :refer [blank?]]
            [taoensso.timbre :as log]
            ["mailgun.js" :as mailgun]))

(def budget-selection
  [{:value "less_than_10000" :display "Less than $10,000"}
   {:value "10000_to_50000" :display "$10,000 - $50,000"}
   {:value "50000_to_100000" :display "$50,000 - $100,000"}
   {:value "100000_to_250000" :display "$100,000 - $250,000"}
   {:value "250000_to_50000" :display "$250,000 - $500,000"}
   {:value "more_than_50000" :display "More than $500,000"}])

(def budget-lookup
  (zipmap
   (map #(% :value) budget-selection)
   (map #(% :display) budget-selection)))

(defn- not-blank? [s]
  (not (blank? s)))

(defn contact-ogtech [{:keys [budget channel company email message name phone]
                       :or   {budget  "No budget" channel "Heaven sent channel"
                              company "a company" phone "No phone number"}}]
  (let [ogtech-contact (m/env-var "OG_CONTACT")
        mg-client (-> {:apiKey (m/env-var "MG_API_KEY")
                       :domain (m/env-var "MG_DOMAIN")}
                      clj->js
                      mailgun)]
           ;(log/debug "MG_API_KEY: " (m/env-var "MG_API_KEY"))
           ;(log/debug "MG_DOMAIN: " (m/env-var "MG_DOMAIN"))
    (when (and (not-blank? email) (not-blank? message) (not-blank? name))
      (.send (.messages mg-client)
             (clj->js
              {:from    (str name " <" email ">")
               :to      ogtech-contact
               :subject (str "New Lead from " company " through channel [" channel "] with budget (" (budget-lookup budget) ")")
               :text    (str "\nContact: " name
                             "\nEmail: " email
                             "\nPhone: " phone
                             "\nChannel: " channel
                             "\n\n" message)})
             (fn [error body]
               (when error (log/error "CONTACT_EMAIL_ERROR:::: " (js->clj error)))
               (when body (log/debug "CONTACT_EMAIL_SUCCESS:::: " (js->clj body))))))))
