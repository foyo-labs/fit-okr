(ns fitokr.core
  (:require
   [reagent.dom :as rdom]
   [re-frame.core :as re-frame]
   [fitokr.db.core :as events]
   [fitokr.components.core :refer [app]]))


(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (let [root-el (.getElementById js/document "app")]
    (rdom/render [app] root-el)))


(defn ^:export init []
  (js/console.log "application starting")
  ;; (router/start!)
  (re-frame/dispatch-sync [::events/initialize-db])
  (js/console.log "Begin rendering")
  (mount-root))
