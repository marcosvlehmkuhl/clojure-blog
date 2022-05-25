(defproject my-website "0.0.1-SNAPSHOT"
  :description "My blog written in clojure"
  :url "http://marcosvlehmkuhl.io"
  :license {:name "The MIT License"
            :url "http://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [selmer "1.12.50"]
                 [markdown-clj "1.11.1"]]
  :main ^:skip-aot my-website.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
