(ns ui.pages.home
  (:require [bidi.bidi :refer [path-for]]
            [routing :refer [routing-data]]
            [ui.widgets.core :as ui]
            [ui.components.core :as c]
            [ui.pages.navigation :as n]))

(defn home [data]
      [:<>
       [n/navigation-fixed]
       [c/header
        "#19A974"
        [:h1.f2.f1-l.fw2.white-90.mb0.lh-title
         "We Build Lispy Things"]
        [:h2.fw1.f3.white-80.mt3.mb4
         "We Build Lispy Things"]
        [:a.f3.ph4.pv3.mb2.dib.link.no-underline.br-pill.dim.bg-green.ba.b--green.near-white.page-scroll
         {:href "#about"}
         "Learn more"]]
       [c/two-column
        "about"
        [c/center
         [:img.w-70
          {:src (path-for routing-data :image :name "Clojure_logo.svg")
           :alt "YOUR STARTUP"}]]
        [c/message
         "Company"
         "bg-green"
         [c/center
          [:p.lh-copy "YOUR STARTUP lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent euismod malesuada sem vel fringilla. In hac habitasse platea dictumst. Donec non mi vehicula, lobortis mauris sed, bibendum odio. Aenean pretium dolor a dui efficitur sollicitudin. Pellentesque tempus massa ac tempor rutrum. Sed commodo et dolor non sodales. Nam pharetra scelerisque lorem, sed rhoncus nulla porttitor at."]]]]
       [c/center
        [c/message
         "Lispy Engineering"
         "bg-green"
         "ed commodo pretium imperdiet. Maecenas felis tortor, laoreet ut nisl ut, suscipit commodo tellus. Quisque sed metus nibh. Duis at mauris eget odio interdum tincidunt eget id metus. Vivamus non fringilla neque. Nunc a nisi ut massa egestas luctus a sit amet eros. Cras sodales dapibus mi id porttitor. Donec iaculis nulla sed neque gravida, ac blandit dolor suscipit. Pellentesque pharetra eleifend est, ac molestie neque sodales id. Integer non est vulputate, vestibulum mi sit amet, ultrices urna. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Maecenas nec justo id libero pretium fermentum."]]
       [c/center
        [c/message
         "Talk to us"
         "bg-green"
         [c/contact "contact" data]]]])

