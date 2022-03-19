(ns my-website.core-test
  (:require [clojure.test :refer :all]
            [my-website.core :refer :all]))


;; (deftest post-data-test
;;   (testing "Get initial post structure"
;;     (is (= {
;;             :tite "Hello World"
;;             :date (java.time.LocalDate/parse "2022-03-02")
;;             :tags ["sports" "news"]
;;            } (get-metadata "test/my_website/mock-post.md")))))

(deftest post-data-test
  (testing "Get initial post structure"
    (let [result (get-metadata "test/my_website/mock-post.md")] 
      (is (= {:title "Hello World" 
              :date (java.time.LocalDate/parse "2022-03-02") 
              :tags ["sports" "news"]
              } 
             result)))))