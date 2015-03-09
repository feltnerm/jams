(ns jams.sessions.rhythmn
  (:use [overtone.live :as o]
        [overtone.inst.drum :only [quick-kick haziti-clap soft-hat open-hat]]
        [overtone.inst.synth]
        [shadertone.tone :as t]))

(def m (metronome 128))

(defn player
  [beat]
  (let [next-beat (inc beat)]

    ;; kick drum
    (at (m beat)
        (quick-kick :amp 0.5)
        (if (zero? (mod beat 2))
          (open-hat :amp 0.1)))

    ;; clapper
    (at (m (+ 0.5 beat))
        (haziti-clap :decay 1.05 :amp 0.3))

    ;; high-hat
    (when (zero? (mod beat 3))
      (at (m (+ 0.75 beat))
          (soft-hat :decay 0.03 :amp 0.2)))

    ;; high-hat 2
    (when (zero? (mod beat 8))
      (at (m (+ 1.25 beat))
          (soft-hat :decay 0.03)))

    ;; recurse
    (apply-by (m next-beat) #'player [next-beat]))
)

(metro-bpm m 172)
(player(m))
