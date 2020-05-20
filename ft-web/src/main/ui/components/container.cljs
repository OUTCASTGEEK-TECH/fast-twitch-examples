(ns ui.components.container
  (:require [bidi.bidi :refer [path-for]]
            [routing :refer [routing-data]]
            [ui.components.separator :as s]))

(defn header [background headline subheadline actions]
      [:header.near-black.dt.vh-100.w-100.bg-green.cover
       {:style
        {:background background}}
       [:div.dtc.v-mid.tc.w-100
        [:h1.f1.lh-solid.mw8.center.tc.b.mt6.ph5-ns
         headline]
        [s/hr "bg-white"]
        [:p.f3.mw8.center.tc.ph5 subheadline]
        actions]])

(defn lst-index [lst]
      [:ul.list.pl0
       (for [item lst :let [{nm :name txt :text} item]]
            ^{:key item}
            [:li.pa3.pa4-ns.bb.b--black-10
             [:b.db.f3.mb1 nm]
             [:span.f5.db.lh-copy.measure txt]])])

(defn center [& content]
      [:section.mw5.mw7-ns.center.pa3.ph5-ns
       content])

(defn center-flag [left right]
      [:div.dt.mw6.center.pt0.pb5.pv5-m.pv6-ns
       [:div.db.dtc-ns.v-mid-ns
        left]
       [:div.db.dtc-ns.v-mid.ph2.pr0-ns.pl3-ns
        right]])

(defn two-column [id left right]
      [:section.vh-50.w-100.dt.pv5
       {:id id}
       [:div.cf
        [:div.fl.w-50 left]
        [:div.fl.w-50 right]]])

(defn three-column [id left middle right]
      [:section.vh-50.w-100.dt
       {:id id}
       [:div.cf
        [:div.fl.w-33 left]
        [:div.fl.w-34 middle]
        [:div.fl.w-33 right]]])

(defn quote [text author]
      [:div.pa4
       [:blockquote.athelas.ml0.mt0.pl4.black-90.bl.bw2.b--green
        [:p.f5.f4-m.f3-l.lh-copy.measure.mt0
         text]
        [:cite.f6.ttu.tracked.fs-normal author]]])

(defn message [headline hr-color text action]
      [:div.dtc.v-mid
       [:h2.tc.near-black.f1 headline]
       [s/hr hr-color]
       [:p.mw7.center.b.near-black.lh-copy.f5.tc
        text]
       action
       ])

(defn dangerous
      ([comp content]
       (dangerous comp nil content))
      ([comp props content]
       [comp (assoc props :dangerouslySetInnerHTML {:__html content})]))
