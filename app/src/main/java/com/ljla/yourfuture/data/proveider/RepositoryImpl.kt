package com.ljla.yourfuture.data.proveider

import android.util.Log
import com.ljla.yourfuture.data.network.HoroscopeApiService
import com.ljla.yourfuture.domain.Repository
import com.ljla.yourfuture.domain.model.PredictionModel
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: HoroscopeApiService) : Repository {
    override suspend fun getPrediction(sign: String): PredictionModel? {
        runCatching { apiService.getHoroscope(sign) }
            .onSuccess { return it.toDomain() }
            .onFailure { Log.i("luis","Ha ocurrido un error ${it.message}") }
        return null
    }
}