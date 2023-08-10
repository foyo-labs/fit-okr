(ns fitokr.components.entry
  (:require [re-frame.core :as rf]
            ["@heroicons/react/24/solid" :refer [CheckCircleIcon
                                                 PlusCircleIcon]]
            [fitokr.db.okr :as okr]
            [fitokr.components.common :refer [form-input button-class]]))


(defn entry-form []
  (let [{:keys [okr-date
                start-time
                end-time]
         :as form-state} @(rf/subscribe [::okr/entry-form])
        date-selected? (nil? okr-date)]
    [:form {:class "mt-4"
            :on-submit (fn [e]
                         (.preventDefault e))}
     [form-input {:id "date"
                  :label "Date"
                  :type "date"
                  :value okr-date
                  :required? true}]
     [form-input {:id "start-time"
                  :label "Start Time"
                  :type "time"
                  :value start-time
                  :required? true
                  :disabled? date-selected?}]
     [form-input {:id "end-time"
                  :label "End Time"
                  :type "time"
                  :value end-time
                  :required? true
                  :disabled? date-selected?}]
     [:button {:class (str button-class " bg-indigo-500 hover:bg-indigo-600 text-white")
               :type "submit"}
      "Confirm"]]))