(ns fitokr.models.users.handler
  (:require [fitokr.models.users.db :as user.db]
            [fitokr.utils.auth :as auth]
            [ring.util.response :as rr]
            [clojure.spec.alpha :as s]
            [fitokr.models.specs :as spec]
            [taoensso.timbre :as log]))

(defn handle-get-all [{:keys [env]}]
  (let [{:keys [db]} env
        result (user.db/get-all db)]
    (log/info result)
    (rr/response (map #(dissoc % :password) result))))

(defn handle-login [{:keys [env parameters]}]
  (let [{:keys [db jwt-secret]} env
        {:keys [email password]} (:body parameters)]
    (if (s/valid? ::spec/create-user {:email email :password password})
      (let [user (user.db/find-user-by-email db email)
            user (auth/password-match? user password)
            res (when user 
                       (auth/user->response user jwt-secret ))]
        (if res
          (rr/response res)
          (rr/response {:error "Invalid credentials"})))
      (rr/response {:error "User login error"}))))

(defn handle-sign [{:keys [env parameters]}]
  (let [{:keys [db]} env
        data (:body parameters)]
    (if (s/valid? ::spec/create-user data)
      (let [result (user.db/create db (assoc data :status 0))]
        (rr/response {:id (:id result)}))
      (rr/response {:error "Error create user"}))))

(comment
  ;; (require '[fitokr.services.config :as r]
  ;;          '[integrant.repl :as ir]
  ;;          '[integrant.repl.state :as state])

  ;; (ir/set-prep!
  ;;  (fn []
  ;;    (dissoc (r/read-config) :reitit/routes :http/server)))
  
  ;; (ir/go)

  ;; ;;构参
  ;; (def env {:env {:db (:postgres/db state/system)}})
  ;; (print env)
  ;; (handle-get-all env)
  
  ;; (ir/halt)

  )