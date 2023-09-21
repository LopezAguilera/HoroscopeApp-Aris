package com.ljla.yourfuture.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navArgs
import com.ljla.yourfuture.R
import com.ljla.yourfuture.databinding.ActivityDetailBinding
import com.ljla.yourfuture.domain.model.HoroscopeModel
import com.ljla.yourfuture.domain.model.HoroscopeModel.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val horoscopeDetailViewModel: HoroscopeDetailViewModel by viewModels()


    private val args: HoroscopeDetailActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        horoscopeDetailViewModel.getHoroscope(args.type)

    }

    private fun initUI() {
        initListener()
        initUIState()
    }

    private fun initListener() {
        binding.ivBack.setOnClickListener { onBackPressed() }
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                horoscopeDetailViewModel.state.collect {
                    when (it) {
                        is HoroscopeDetailState.Error -> errorState()
                        HoroscopeDetailState.Loading -> loadingState()
                        is HoroscopeDetailState.Success -> successState(it)
                    }
                }
            }
        }
    }

    private fun loadingState() {
        binding.pb.isVisible = true
    }
    private fun errorState(){

    }
    private fun successState(state: HoroscopeDetailState.Success) {
        binding.pb.isVisible = false
        binding.tvTitle.text = state.sign
        binding.tvBody.text = state.prediction

        val image = when (state.horoscopeModel){
            Aquarius -> R.drawable.detail_aquarius
            Aries -> R.drawable.detail_aries
            Cancer -> R.drawable.detail_cancer
            Capricorn -> R.drawable.detail_capricorn
            Scorpio -> R.drawable.detail_scorpio
            Gemini -> R.drawable.detail_gemini
            Leo -> R.drawable.detail_leo
            Libra -> R.drawable.detail_libra
            Pisces -> R.drawable.detail_pisces
            Sagittarius -> R.drawable.detail_sagittarius
            Taurus -> R.drawable.detail_taurus
            Virgo -> R.drawable.detail_virgo
        }

        binding.ivDetail.setImageResource(image)
    }
}