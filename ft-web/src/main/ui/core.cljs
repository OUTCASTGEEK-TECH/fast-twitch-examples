(ns ui.core
    (:require [reagent.core :as r]
              [ui.widgets.core :as ui]))

(enable-console-print!)

(defn start []
  (r/render-component
   [ui/hello-ui {:upper-bound 9}]
   (.getElementById js/document "app")))

(start)

