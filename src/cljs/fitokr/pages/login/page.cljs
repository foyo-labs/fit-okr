(ns fitokr.pages.login.page
  (:require [re-frame.core :as rf]
            [reagent.core :as r]
            [fitokr.db.auth :as auth]
            [fitokr.components.common :refer [form-input button-class]]))

(defn page []
  (let [form-state (r/atom {:username ""
                            :password ""})
        on-change (fn [k] #(swap! form-state assoc k (-> % .-target .-value)))]
    [:div {:class "container-auth-bg flex w-full h-screen"}
     [:div {:class "w-full flex flex-col justify-center items-center"}
      [:div {:class "login shadow-lg mt-6 px-10 py-10 w-3/12 bg-white"}
       [:div {:class ""}
        [:div {:class "text-xl font-bold text-gray-900 text-center"} "登录到FIT-OKR"]
        [:div {:class "sm:mx-auto sm:w-full sm:max-w-sm"}
         [:form {:class "mt-4"
                 :on-submit (fn [e]
                              (.preventDefault e)
                              (rf/dispatch [::auth/login @form-state]))}
          [form-input {:id "username"
                       :label "用户名(邮箱)"
                       :value (:username @form-state)
                       :on-change (on-change :username)
                       :required true}]
          [form-input {:id "password"
                       :label "密码"
                       :type "password"
                       :value (:password @form-state)
                       :on-change (on-change :password)}]
          [:button {:class (str button-class "w-full justify-center rounded-md bg-slate-200 px-3 py-3 text-sm")
                    :type "submit"} "登录"]
          [:div {:class "mt-5 text-center text-sm"} "似乎忘记了我的密码"]]]]]]]))