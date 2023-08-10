(ns fitokr.models.objectives.handler
  (:require [clojure.spec.alpha :as s]
            [fitokr.models.objectives.db :as db]
            [fitokr.models.specs :as spec]
            [ring.util.response :as rr]
            [taoensso.timbre :as log]))

(defn handle-get-objective [{:keys [env parameters]}]
  (let [{:keys [db]} env
        id (get-in parameters [:path :id])]
    (log/info "path id: " id)
    (rr/response (db/get-by-id db id))))

(defn handle-put-objective []
  (rr/response "Ok"))

(defn handle-delete-objective []
  (rr/response "Ok"))

(defn handle-get-all [{:keys [env parameters]}]
  (let [{:keys [db]} env
        query-params (get-in parameters [:query])]
    (log/debugf "start process with handle-get-all")
    (if (s/valid? ::spec/query-objective query-params)
      (let [result (db/get-all-with-keyresults db query-params)]
        (rr/response result))
      (rr/response {:error "Invalid parameters"}))))

(defn handler-create-objective 
  [{:keys [env parameters]}]
  (let [{:keys [db]} env
        data (:body parameters)]
    (log/debugf "request body :" parameters)
    (if (s/valid? ::spec/create-objective data)
      (let [result (db/create db (assoc data :status 0))]
        (rr/response {:id (:id result)}))
      (rr/response {:error "Error create objective"}))))


(comment
  (def obj {:name "haha"})
  (assoc obj :status 0)
  )