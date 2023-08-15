(ns core
  (:require [clojure.tools.namespace.repl :as tools-ns]
            [integrant.repl :as ig-repl :refer [go halt]]
            [nrepl.server]
            [fitokr.services.config :as c]
            [integrant.repl.state :as state]))

(defn start-interactive []
  (tools-ns/set-refresh-dirs "dev" "server/src")
  (ig-repl/set-prep!
   (fn []
     ;; exclude routes and server.
     (dissoc (c/read-config) :reitit/routes :http/server)))
  (go)
  :ready!)

(defn get-repl-db [] (:postgres/db state/system))

(defn restart []
  (halt)
  (tools-ns/refresh :after 'core/start-interactive))

(defn halt!! [] (halt))

(comment
  (print (get-repl-db)))