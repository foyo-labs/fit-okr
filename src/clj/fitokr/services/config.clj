(ns fitokr.services.config
  (:require [aero.core :as aero]
            [clojure.java.io :as io]
            [taoensso.timbre :as log]
            [integrant.core :as ig]
            [fitokr.services.db]
            [fitokr.services.http]
            [fitokr.services.router]))

(defmethod aero/reader 'ig/ref
  [_ _ value]
  (ig/ref value))

(defmethod ig/init-key :system/config
  [_ config]
  (log/info "system starting with config" config)
  config)

(defn read-config
  ([] (read-config :prod))
  ([profile]
   (aero/read-config
    (io/resource "config.edn")
    {:profile profile})))


(comment
  (read-config)
)