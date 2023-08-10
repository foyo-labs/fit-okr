(ns fitokr.components.stats
  (:require [antizer.reagent :as ant]
            [reagent.core :as r]
            [re-frame.core :as rf]
            [fitokr.db.okr :as okr]))




(defn stats []
  (let [state (r/atom (clj->js {:startDate nil
                                :endDate nil}))]
    (fn []
      (let [data @(rf/subscribe [::okr/okr-data])]
        [:div {:class "p-2 mt-2"} "Hello OKR Stats"]
        [ant/button {:on-click #(ant/message-info "Hello Rum!")} "hello"]))))