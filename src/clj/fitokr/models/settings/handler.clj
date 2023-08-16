(ns fitokr.models.settings.handler
  (:require [fitokr.models.settings.db :as settings.db]
            [fitokr.models.cycles.db :as cycles.db]
            [ring.util.response :as rr]))


(defn handle-get-settings [{:keys [env]}]
  (let [{:keys [db]} env
        result (settings.db/get-all db)]
    (rr/response result)))


(defn handle-system-initialize
  " Initialize system
  * Create a company info
  * Create cycle data with customize by Year/Quarter/Month/Week/Special"
  [{:keys [env parameters]}]
  (let [db (:db env)
        data (:body parameters)
        initialized? (settings.db/find-setting-item-by-label db "initialized")]

    (if initialized?
      (rr/bad-request "error request.")
      (let [init-settings? (settings.db/initialize-system db data initialized?)
            init-cycled? (cycles.db/create-default-cycles)]
        (if (and init-settings? init-cycled?)
          (rr/response {:res init-settings?})
          (rr/response {:status 500
                        :body "Error during initialization."}))))))