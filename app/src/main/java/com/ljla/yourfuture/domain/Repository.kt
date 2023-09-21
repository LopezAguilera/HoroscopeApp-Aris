package com.ljla.yourfuture.domain

import com.ljla.yourfuture.domain.model.PredictionModel

interface Repository {
    suspend fun getPrediction(sign:String): PredictionModel?
}