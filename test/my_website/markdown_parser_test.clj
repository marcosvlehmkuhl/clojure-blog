(ns my-website.markdown-parser-test
  (:require [clojure.test :refer :all]
            [my-website.markdown-parser :as md-parser]))


(deftest post-data-test
  (testing "Get initial post structure"
    (let [result (md-parser/get-metadata "test/my_website/mock-post.md")] 
      (is (= {:title "Hello World" 
              :date (java.time.LocalDate/parse "2022-03-02") 
              :tags ["sports" "news"]
              } 
             result)))))