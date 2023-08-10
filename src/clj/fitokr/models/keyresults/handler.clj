(ns fitokr.models.keyresults.handler
  (:require [clojure.spec.alpha :as s]
            [fitokr.models.keyresults.db :as db]
            [fitokr.models.specs :as spec]
            [ring.util.response :as rr]
            [taoensso.timbre :as log]))


(defn handler-create-key-result
  [{:keys [env parameters]}]
  (let [{:keys [db]} env
        data (:body parameters)
        oid (get-in parameters [:path :id])]
    (log/info "parameters " data ", " oid)
    (if (s/valid? ::spec/create-key-result data)
      (let [result (db/create db (assoc data :oid oid))]
        (rr/response {:id (:id result)}))
      (rr/response {:error "Error create keyresult"}))))

(defn handler-update-key-result
  [{:keys [env parameters]}]
  (let [{:keys [db]} env
        data (:body parameters)
        oid (get-in parameters [:path :id])]
    (rr/response {:message "ok"})))


(defn handler-delete-key-result
  [{:keys [env parameters]}]
  (let [{:keys [db]} env
        data (:body parameters)
        oid (get-in parameters [:path :id])]
    (rr/response {:message "ok"})))