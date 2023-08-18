(ns fitokr.db.auth
  (:require [re-frame.core :as rf]))

(def initial-state
  {::auth {}})


(rf/reg-event-fx
 ::check-identity
 [(rf/inject-cofx :local-storage :account/token)]
 (fn [cofx]
   (let [token (:account/token cofx)]
     {:fx [[:dispatch [:http {:url "/api/account"
                              :headers {"Authorization" (str "Bearer " token)}
                              :on-success [::auth-success]
                              :on-failure [:logout]}]]]})))


(rf/reg-event-fx
 ::login
 (fn [_ [_ data]]
   {:fx [[:dispatch [:http {:url "/api/users/login"
                            :method :post
                            :data data
                            :on-success [::auth-success]
                            :on-failure [:http-failure]}]]]}))

(rf/reg-event-fx
 ::register
 (fn [_ [_ data]]
   {:fx [[:dispatch [::auth-success data] ]]}))

(rf/reg-event-fx
 ::auth-success
 (fn [{:keys [db]} [_ data]]
   {:db (assoc-in db [::auth :account] data)
    :fx [[:set-local-storage [:account/token (:account/token data)]]]}))

(rf/reg-sub
 ::account
 (fn [db]
   (get-in db [::auth :account])))

(rf/reg-sub
 ::admin-account
 (fn [db]
  ;;  (get-in db [::auth :account :admin])
   true))