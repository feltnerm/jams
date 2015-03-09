(ns jams.core
    (:use [overtone.live :as o])
    (:require [shadertone.tone :as t]))

(def sol-do   (o/sample (o/freesound-path 44929)))
(def sol-re   (o/sample (o/freesound-path 44934)))
(def sol-mi   (o/sample (o/freesound-path 44933)))

(sol-do)
(sol-re)
(sol-mi)
;(defn throb    (start-jam-session "throb"))

(defn rhythmn [] (start-jam-session "rhythmn"))
(defn throb [] (start-jam-session "throb"))

(defn start-jam-session
    "Begin a jam session"
    [session-name]
    (println (apply str "Session: " session-name))
    (let [session-glsl-file (apply str "src/jams/sessions/" session-name ".glsl") ]
        (println (apply str "Session: " session-glsl-file))
        (t/start-fullscreen session-glsl-file
            :title session-name
            :textures [:overtone-audio :previous-frame]
        )
    )
)

(defn stop-it-all "stop everything" []
    (t/stop)
    (o/stop)
)

(defn fire-up-jam-session "fire it up!" []
    (println "Firing up jam session")
    (readyNoise)
)

(defn -main
    "main method to launch jams"
    [& args]
    (fire-up-jam-session)
    ;(start-jam-session "throb"))
)
