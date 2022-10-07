package com.udacity.asteroidradar

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.api.NasaApi
import com.udacity.asteroidradar.api.asAsteroidEntity
import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.database.asAsteroid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class AsteroidRepository(private val database: AsteroidDatabase) {

    val asteroids: LiveData<List<Asteroid>> = Transformations.map( database.dao.getAll()){
        it.asAsteroid()
    }

    suspend fun refreshAsteroid(){
        withContext(Dispatchers.IO) {
            val asteroids = NasaApi.getAsteroids()
            database.dao.insertAll(asteroids.asAsteroidEntity())

        }
    }
    suspend fun getPictureOfDay(): PictureOfDay {
        lateinit var pictureOfDay: PictureOfDay
        withContext(Dispatchers.IO) {
            pictureOfDay = NasaApi.getPictureOfDay()
        }
        return pictureOfDay
    }
}




