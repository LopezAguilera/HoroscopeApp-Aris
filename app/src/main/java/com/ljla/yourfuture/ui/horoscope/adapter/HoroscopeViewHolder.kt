package com.ljla.yourfuture.ui.horoscope.adapter

import android.view.View
import android.view.animation.LinearInterpolator
import androidx.core.graphics.rotationMatrix
import androidx.recyclerview.widget.RecyclerView
import com.ljla.yourfuture.databinding.ItemHoroscopeBinding
import com.ljla.yourfuture.domain.model.HoroscopeInfo

class HoroscopeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemHoroscopeBinding.bind(view)

    fun render(horoscopeInfo: HoroscopeInfo, onItemSelected: (HoroscopeInfo) -> Unit) {
        val context = binding.tvTitle.context
        binding.ivHoroscope.setImageResource(horoscopeInfo.img)
        binding.tvTitle.text = context.getString(horoscopeInfo.name)

        binding.parent.setOnClickListener {
            startRotationAnimation(binding.ivHoroscope, newLambda = { onItemSelected(horoscopeInfo) })

        }
    }

    fun startRotationAnimation(view: View, newLambda: () -> Unit) {
        view.animate().apply {
            duration = 500
            interpolator = LinearInterpolator()
            rotationY(360f)
            withEndAction(newLambda)
            start()

        }
    }
}