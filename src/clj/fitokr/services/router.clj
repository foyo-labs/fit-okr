(ns fitokr.services.router
  (:require [clojure.java.io :as io]
            [taoensso.timbre :as log]
            [integrant.core :as ig]
            [reitit.ring :as ring]
            [muuntaja.core :as m]
            [reitit.coercion.spec :refer [coercion]]
            [fitokr.api.core :as api]
            [fitokr.router.middleware :refer [global-middleware]]
            [fitokr.router.exception :refer [handle-exception]]
            [fitokr.router.frontend :refer [create-frontend-handler]]))

(defn index []
  (slurp (io/resource "public/index.html")))

(defmethod ig/init-key :reitit/routes
  [_ {:keys [db config]}]
  (log/info "initializing routes")
  (ring/ring-handler 
   (ring/router
    ["" {:handler (fn [_] {:body (index) :status 200})}])
   (ring/router
    api/routes
    {:data {:env {:db db
                  :jwt-secret (:jwt-secret config)}
            :coercion coercion
            :muuntaja m/instance
            :middleware global-middleware}})
   (ring/routes
    (ring/redirect-trailing-slash-handler)
    (create-frontend-handler)
    (ring/create-default-handler
     {:not-found (handle-exception 404 "Route not found")
      :method-not-allowed (handle-exception 405 "Method not allowed")
      :not-acceptable (handle-exception 406 "Not acceptable")}))))