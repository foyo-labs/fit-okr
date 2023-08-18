(ns fitokr.utils.auth
  (:require [buddy.hashers :refer [check]]
            [buddy.sign.jwt :as jwt]))


(defn user->response
  [user secret]
  (let [sanitized (dissoc user :password)
        token (jwt/sign sanitized secret)]
    (assoc sanitized :token token)))

(defn password-match?
  [user password]
  (when (and user
             (check password (:password user)))
    user))