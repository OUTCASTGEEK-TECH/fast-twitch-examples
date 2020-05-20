(ns ui.pages.contact
  (:require [ui.components.core :as c]
            [ui.pages.navigation :as n]))

(defn contact [data & {:keys [contact support]
                       :or   {contact "contact@your-startup.io"
                              support "support@your-startup.io"}}]
      [:<>
       [n/navigation-fixed]
       [:div.cover.bg-left.bg-center-l.bg-green
        ;[n/navigation]
        [:br]]
       [c/center
        [c/message
         "Contact"
         "bg-green"
         "We're building a bunch of awesome products. If you're interested in working with us, or have a burning feature request you want to talk about, then let us know."]]
       [c/two-column
        "contact"
        [c/center
         [c/lst-index
          [{:name "General Inquiries" :text [:p
                                             "You may contact us at "
                                             [:a._01c91678.no-underline.pr1
                                              {:href (str "mailto:" contact)}
                                              contact]
                                             "."]}
           {:name "For support assistance" :text [:p
                                                  "You may let us know at "
                                                  [:a._01c91678.no-underline.pr1
                                                   {:href (str "mailto:" support)}
                                                   support]
                                                  "."]}
           {:name "Technical Issue" :text [:p
                                           "If you have a technical issue, take a look at "
                                           [:a._01c91678.no-underline.pr1
                                            {:href "#FAQ"}
                                            "our frequently asked questions"]
                                           "and then send us a line at "
                                           [:a._01c91678.no-underline.pr1
                                            {:href (str "mailto:" support)}
                                            support]
                                           "."]}
           ]]]
        [c/center
         [c/contact "contact" data]]]
       ])

