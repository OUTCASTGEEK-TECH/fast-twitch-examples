(ns routing)

(def routing-data
  ["/"
   {"" {:get
        {"" :home}}
    "contact" {:get
               {"" :contact}}
    "do-contact" {:post
               {"" :do-contact}}
    ["img/" :name] {:get
                   {"" :image}}
    ["js/" :name] {:get
                    {"" :javascript}}
    "react" {:get
             {"" :react}}
    ["weather/" :city] {:get
                        {"" :weather}}
    "github-users" {:get
                    {"" :ghusers}}
    "_ah/start" {:get
                 {"" :start}}
    "_ah/health" {:get
                  {"" :health}}
    "_ah/stop" {:get
                {"" :stop}}
    "foo" :foo}
   ])

