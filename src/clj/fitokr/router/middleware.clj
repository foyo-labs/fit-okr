(ns fitokr.router.middleware
  (:require [reitit.ring.coercion :as coercion]
            [ring.middleware.cors :refer [wrap-cors]]
            [reitit.ring.middleware.parameters :as parameters]
            [reitit.ring.middleware.muuntaja :as muuntaja]
            [fitokr.router.exception :as exception]))

(def wrap-env
  {:name ::env
   :compile
   (fn [{:keys [env]} _]
     (fn [handler]
       (fn [request]
         (handler (assoc request :env env)))))})

(def global-middleware
  [parameters/parameters-middleware
   muuntaja/format-middleware
   [wrap-cors :access-control-allow-origin [#"http://localhost:3000"]
    :access-control-allow-methods [:get :post :put :delete]]
   muuntaja/format-negotiate-middleware
   muuntaja/format-response-middleware
   muuntaja/format-request-middleware
   exception/exception-middleware
   coercion/coerce-request-middleware
   coercion/coerce-response-middleware 
   wrap-env])