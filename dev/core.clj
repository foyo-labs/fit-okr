(ns core
  (:require [clojure.tools.namespace.repl :as tools-ns]
            [integrant.repl :as ig-repl :refer [go halt]]
            [nrepl.server]
            [fitokr.services.config :as c]))

(defn start-interactive []
  (tools-ns/set-refresh-dirs "dev" "server/src")
  (ig-repl/set-prep!
   (fn []
     (dissoc (c/read-config :dev) :reitit/routes :http/server)))
  (go)
  :ready!)

(defn restart []
  (halt)
  (tools-ns/refresh :after 'core/start-interactive))

(defn halt!! [] (halt))