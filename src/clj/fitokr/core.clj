(ns fitokr.core
    (:require [fitokr.services.config :as config]))

(defn -main []
  (config/read-config :prod))