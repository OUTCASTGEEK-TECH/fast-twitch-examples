(ns ui.components.footer)

(defn footer [copyright company notice]
  [:footer.pv4.ph3.ph5-m.ph6-l.mid-gray
  ;[:footer.pv4.ph3.ph5-m.ph6-l.white-70.bg-dark-gray
   ;{:style
   ; {:background "linear-gradient(#f6fffe, #001b44)"}}
   [:small.f6.db.tc
    [:span {:dangerouslySetInnerHTML {:__html (str copyright " ")}}]
    [:b.ttu company]
    (str " " notice)]
   [:div.tc.mt3
    [:a.f6.dib.ph2.link.mid-gray.dim
     {:title "Language", :href "#/language/"}
     "Language"]
    [:a.f6.dib.ph2.link.mid-gray.dim
     {:title "Terms", :href "#/terms/"}
     "Terms of Use"]
    [:a.f6.dib.ph2.link.mid-gray.dim
     {:title "Privacy", :href "#/privacy/"}
     "Privacy"]]])

