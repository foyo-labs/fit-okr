(ns fitokr.models.settings.handler
  (:require [clojure.spec.alpha :as s]
             [fitokr.models.settings.db :as settings.db]
             [fitokr.models.specs :as spec]
             [ring.util.response :as rr]
             [taoensso.timbre :as log]))


(defn handle-get-settings [{:keys [env]}]
  (let [{:keys [db]} env
        result (settings.db/get-all db)]
    (rr/response result)))