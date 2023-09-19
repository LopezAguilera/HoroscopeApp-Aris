package com.ljla.yourfuture.data.proveider

import com.ljla.yourfuture.domain.model.HoroscopeInfo
import com.ljla.yourfuture.domain.model.HoroscopeInfo.*
import javax.inject.Inject

class HoroscopeProvider @Inject constructor(){

    fun getHoroscopes(): List<HoroscopeInfo> {
        return listOf(
            Aquario, Aries, Cancer, Capricornio, Escorpio,
            Geminis, Leo, Libra, Piscis, Sagitario, Tauro, Virgo

        )
    }
}