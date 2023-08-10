(ns fitokr.db.okr
  (:require [re-frame.core :as rf]
            [fitokr.db.ui :as ui]))


(def initial-form
  {:okr-date nil})


(def initial-state
  {::okr {:form initial-form
          :data []}})


(rf/reg-event-fx
 ::open-entry-form
 (fn [_ [_ date]]
   {:fx [[:dispatch [::ui/set-dialog :entry]]
         (when date [:dispatch [::select-sleep-date date]])]}))

(rf/reg-sub
 ::entry-form
 (fn [db]
   (get-in db [::sleep :form])))

(rf/reg-sub
 ::okr-data
 (fn [db]
   (get-in db [::okr :data])))