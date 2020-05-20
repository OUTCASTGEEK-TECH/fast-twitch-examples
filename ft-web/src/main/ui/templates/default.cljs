(ns ui.templates.default
  (:require [taoensso.timbre :as log]
            [tick.alpha.api :as t]
            [bidi.bidi :refer [path-for]]
            [routing :refer [routing-data]]
            [fast-twitch.preload :refer [quick-css quick-img quick-js
                                         preload-styles preload-scripts
                                         load-styles load-scripts]]
            [ui.components.core :as c]))

(defn template-ui [data]
      (let [{:keys [content scripts styles title]} data]
           [:html {:lang "en"}
            [:head
             [:meta {:charset "utf-8"}]
             [:meta {:http-equiv "content-type" :content "text/html; charset=UTF-8"}]
             [:meta {:content "width=device-width, initial-scale=1.0", :name "viewport"}]
             [:title (str "YOUR STARTUP | " title)]

             [:link {:rel "dns-prefetch" :href "//cdnjs.cloudflare.com"}]
             [:link {:rel "dns-prefetch" :href "//ajax.googleapis.com"}]
             [:link {:rel "dns-prefetch" :href "//fonts.googleapis.com"}]
             [:link {:rel "dns-prefetch" :href "//code.getmdl.io"}]
             [:link {:rel "dns-prefetch" :href "//code.jquery.com"}]
             [:link {:rel "dns-prefetch" :href "//maxcdn.bootstrapcdn.com"}]
             [:link {:rel "dns-prefetch" :href "//unpkg.com"}]
             [:link {:rel "dns-prefetch" :href "//use.fontawesome.com"}]

             ;; Preloads
             [quick-img (path-for routing-data :image :name "Lambda.svg")]

             [quick-css "//unpkg.com/tachyons/css/tachyons.min.css"]

             [quick-js "//use.fontawesome.com/releases/v5.12.1/js/all.js"]

             (preload-styles styles)

             (preload-scripts scripts)

             ;; Styles
             [:link {:rel "stylesheet" :href "//unpkg.com/tachyons/css/tachyons.min.css"}]
             [:link {:rel "stylesheet" :href "//assets.calendly.com/assets/external/widget.css"}]

             (load-styles styles)]
            [:body.w-100.sans-serif.bg-white
             [:main

              content

              [c/footer (str "&copy; " (t/year (t/now))) "YOUR STARTUP" "All rights reserved."]]

             (load-scripts
               (conj scripts
                     [:src (path-for routing-data :javascript :name "common.js")]))
             ]]
           ))

