(ns fitokr.api.objectives
  (:require [fitokr.models.objectives.handler :as objectives]
            [fitokr.models.keyresults.handler :as keyresults]
            [fitokr.models.specs :as spec]))

;发生错误，parameters发生错误，:path可以
(def objective-routes
  ["/objectives"
   ["" {:get {:summary "query alll objectives with query-params"
              :handler objectives/handle-get-all
              :parameters {:query ::spec/query-objective}}
        :post {:summary "create an objective entity"
               :handler objectives/handler-create-objective
               :parameters {:body ::spec/create-objective}}}]
   ["/:id" {:get {:summary "get an objective enity"
                  :handler objectives/handle-get-objective
                  :parameters {:path {:id int?}}}
            :put {:summary "modify an objective"
                  :handler objectives/handle-put-objective}
            :delete {:summary "delete an objective"
                     :handler objectives/handle-delete-objective}}
    ["/keyresults" {:post {:summary "create a keyresult"
                           :handler keyresults/handler-create-key-result
                           :parameters {:body ::spec/create-key-result}}
                    :put {:summary "modify a keyresult"
                          :handler keyresults/handler-update-key-result}
                    :delete {:summary "delete a keyresult"
                             :handler keyresults/handler-delete-key-result}}]]])

(comment

  )