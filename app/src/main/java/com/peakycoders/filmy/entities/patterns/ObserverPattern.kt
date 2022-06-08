package com.peakycoders.filmy.entities.patterns

import com.peakycoders.filmy.entities.models.Movie

open class Subject {
    private var observers = mutableListOf<Observer>()

    fun notify(notice : List<Movie>) {
        for(obs in observers) obs.update(notice)
    }

    fun attach(obs : Observer) {
        observers.add(obs)
    }

    fun detach(obs : Observer) {
        observers.remove(obs)
    }
}

interface Observer {
    fun update(notice : List<Movie>)
}
