(ns fitokr.db.ui  (:require [re-frame.core :as rf]))

(def initial-state
  {::dialog {:open? false
             :type :loading
             :error-message ""}
   ::active-page :home})


(rf/reg-event-db
 ::close-dialog
 (fn [db] (assoc-in db [::dialog :open?] false)))

(rf/reg-event-db
 ::set-dialog
 (fn [db [_ type error-message]]
   (assoc db ::dialog {:open? true
                       :type type
                       :error-message error-message})))

(rf/reg-sub
 ::dialog
 (fn [db]
   (get db ::dialog)))



(rf/reg-event-fx
 ::set-active-page  ;; usage: (dispatch [:set-active-page {:page :home})
 (fn [{:keys [db]} [_ {:keys [page]}]] 
   (let [set-page (assoc db ::active-page page)]
     (case page
       ;; -- URL @ "/" --------------------------------------------------------
       :home {:db set-page}
       (:login :register) {:db set-page}))))


(rf/reg-sub
 ::active-page
 (fn [db _]            ;; db is the (map) value stored in the app-db atom
   (::active-page db))) 