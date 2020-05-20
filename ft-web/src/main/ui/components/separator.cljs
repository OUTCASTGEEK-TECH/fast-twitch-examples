(ns ui.components.separator)

(defn hr [bg-color]
      [:hr {:class (str "mw4 center bn " bg-color) :style {:height "5px"}}])
