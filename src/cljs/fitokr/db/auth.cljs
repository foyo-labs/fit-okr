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
   {:fx []}))


(rf/reg-sub
 ::account
 (fn [db]
   (get-in db [::auth :account])))