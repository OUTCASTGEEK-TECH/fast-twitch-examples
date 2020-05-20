(ns ui.components.navbar
  (:require [bidi.bidi :refer [path-for]]
            [routing :refer [routing-data]]))

(defn navbar [site-name & links]
  [:nav.dt.w-100.border-box.pa3.ph5-ns.bg-green
   [:a.dtc.v-mid.mid-gray.link.dim.w-25
    {:title "Home", :href (path-for routing-data :home)}
    [:img.w-16.dib.w2.h2.br-100
     {:src (path-for routing-data :image :name "Lambda.svg")
      :alt site-name}]]
   [:div.dtc.v-mid.w-75.tr
    (for [link (butlast links)]
      (let [{:keys [title url]} link]
        ^{:key link}
        [:a.link.dim.white-70.f6.f5-ns.dib.mr3.mr4-ns
         {:href url} title]))
    (let [{:keys [id title url]} (last links)]
      [:a.link.dim.white-70.f6.f5-ns.dib
       {:id id :title title, :href url}
       title])]])

(defn navbar-fixed [site-name & links]
  [:nav.dt.fixed.w-100.border-box.pa3.ph5-ns.bg-green
   [:a.dtc.v-mid.mid-gray.link.dim.w-25
    {:title "Home", :href (path-for routing-data :home)}
    [:img.w-16.dib.w2.h2.br-100
     {:src (path-for routing-data :image :name "Lambda.svg")
      :alt site-name}]]
   [:div.dtc.v-mid.w-75.tr
    (for [link (butlast links)]
      (let [{:keys [id title url]} link]
        ^{:key link}
        [:a.link.dim.white-70.f6.f5-ns.dib.mr3.mr4-ns
         {:id id :href url} title]))
    (let [{:keys [id title url]} (last links)]
      [:a.link.dim.white-70.f6.f5-ns.dib
       {:id id :title title, :href url}
       title])]])

