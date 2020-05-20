(ns ui.components.forms
  (:require [clojure.string :as string]
            [bidi.bidi :refer [path-for]]
            [routing :refer [routing-data]]
            [ui.components.container :as c]
            [services.core :as svc]))

(defn action-button [params text]
      [:div.pv4.ph3.tc
       [:input.f3.ph4.pv3.mb2.dib.link.no-underline.br-pill.dim.bg-green.ba.b--green.near-white.page-scroll
        params
        text]])

(defn link-button [url text]
      [:div.pv4.ph3.tc
       [:a.f3.ph4.pv3.mb2.dib.link.no-underline.br-pill.dim.bg-green.ba.b--green.near-white.page-scroll
        {:href url}
        text]])

(defn select [{:keys [class style options selected name id] :or {selected "default"}}]
      (let [default-option [:option {:value selected} "Select an one..."]]
           [:select
            {:class class
             :style style
             :field :list
             :name  name
             :id    id
             :value selected
             }

            (if (= selected "default") default-option)

            (for [option options]
                 ^{:key option}
                 [:option {:key (str option (rand-int 10)) :value (:value option)} (:display option)])]))

(def budget-selection
  [{:value "less_than_10000" :display "Less than $10,000"}
   {:value "10000_to_50000" :display "$10,000 - $50,000"}
   {:value "50000_to_100000" :display "$50,000 - $100,000"}
   {:value "100000_to_250000" :display "$100,000 - $250,000"}
   {:value "250000_to_50000" :display "$250,000 - $500,000"}
   {:value "more_than_50000" :display "More than $500,000"}])

(defn contact [id data]
      (let [{:keys [csrf-token]} data]
           [:<>
            [:form {:id id :action (path-for routing-data :do-contact) :method "POST"}
             [:input {:type "hidden", :name "_csrf", :value csrf-token}]
             [:div.cf.mb2
              [:div.fl.w-100
               [:div.w-33.fl                                ;;.w-25.mr2
                [:input.input-reset.ba.b--black-20.pa2.mb2.db.w-100
                 {:placeholder "Full name", :name "name", :type "text"}]]
               [:div.w-33.fl                                ;;.w-25.mr2
                [select {:class   "w-100 db f6 bg-white ba b--sliver gray"
                         :style {:height "2.3rem"}
                         :options budget-selection
                         :selected "less_than_10000"
                         :name    "budget"
                         :id      :budget-selection}]]
               [:div.w-33.fl
                [:input.input-reset.ba.b--black-20.pa2.mb2.db.w-100
                 {:placeholder "How did you hear of us?", :name "channel", :type "text"}]]]]
             [:div.cf.mb2
              [:div.fl.w-100
               [:div.w-33.fl
                [:input.input-reset.ba.b--black-20.pa2.mb2.db.w-100
                 {:placeholder "Email", :name "email", :type "text"}]]
               [:div.w-33.fl
                [:input.input-reset.ba.b--black-20.pa2.mb2.db.w-100
                 {:placeholder "Phone (optional)", :name "phone", :type "phone"}]]
               [:div.w-33.fl
                [:input.input-reset.ba.b--black-20.pa2.mb2.db.w-100
                 {:placeholder "Company (optional)", :name "company", :type "text"}]]]]
             [:div.cf.mb2
              [:div.fl.w-100
               [:div.fl
                [:textarea.w-100.pa2
                 {:cols        77 :rows 20
                  :placeholder "How can we help?", :name "message", :type "text"}]]]]
             [:div.cf.mb2
              [:div.fl.w-100
               [:div.fl.w-25.pa2]
               [:div.fl.w-50
                [action-button
                 {:type "submit" :value "Connect with us"}]]]]]
            [c/dangerous
             :script
             "console.log('Hello :-)');"]]))
