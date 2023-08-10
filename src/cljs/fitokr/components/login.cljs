(ns fitokr.components.login
  (:require [re-frame.core :as rf]
            [reagent.core :as r]
            [antizer.reagent :as ant]
            [fitokr.db.auth :as auth]
            [fitokr.components.common :refer [form-input button-class]]))



(defn login
  []
  (let [form-state (r/atom {:username ""
                            :password ""})
        on-change (fn [k] #(swap! form-state assoc k (-> % .-target .-value)))]
    (fn []
      [:div {:class "flex min-h-full flex-col justify-center px-6 py-12 lg:px-8"}
       [:div {:class "sm:mx-auto sm:w-full sm:max-w-sm"}
        [:h2 {:class "mt-10 text-2xl font-bold leading-9 tracking-tight text-gray-900"} "Hey, Hello"]
        [:p {:class "mt-1 text-slate-500"} "输入系统登录所需的账户信息."]]
       [:div {:class "mt-10 sm:mx-auto sm:w-full sm:max-w-sm"}
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
         
          [:button {:class "flex w-full justify-center rounded-md bg-indigo-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
                    :type "submit"}
           "Sign in"]]]])))