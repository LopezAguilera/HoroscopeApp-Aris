package com.ljla.yourfuture.ui.horoscope

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.ljla.yourfuture.databinding.FragmentHoroscopeBinding
import com.ljla.yourfuture.databinding.FragmentHoroscopeBinding.inflate
import com.ljla.yourfuture.domain.model.HoroscopeInfo.Aquario
import com.ljla.yourfuture.domain.model.HoroscopeInfo.Aries
import com.ljla.yourfuture.domain.model.HoroscopeInfo.Cancer
import com.ljla.yourfuture.domain.model.HoroscopeInfo.Capricornio
import com.ljla.yourfuture.domain.model.HoroscopeInfo.Escorpio
import com.ljla.yourfuture.domain.model.HoroscopeInfo.Geminis
import com.ljla.yourfuture.domain.model.HoroscopeInfo.Leo
import com.ljla.yourfuture.domain.model.HoroscopeInfo.Libra
import com.ljla.yourfuture.domain.model.HoroscopeInfo.Piscis
import com.ljla.yourfuture.domain.model.HoroscopeInfo.Sagitario
import com.ljla.yourfuture.domain.model.HoroscopeInfo.Tauro
import com.ljla.yourfuture.domain.model.HoroscopeInfo.Virgo
import com.ljla.yourfuture.domain.model.HoroscopeModel
import com.ljla.yourfuture.ui.horoscope.adapter.HoroscopeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeFragment : Fragment() {

    private val horoscopeViewModel by viewModels<HoroscopeViewModel>()

    private lateinit var horoscopeAdapter: HoroscopeAdapter

    private var _binding: FragmentHoroscopeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iniUI()
    }

    private fun iniUI() {
        iniList()
        iniUiState()
    }

    private fun iniList() {
        horoscopeAdapter = HoroscopeAdapter(onItemSelected = {
            val type = when (it) {
                Aquario -> HoroscopeModel.Aquario
                Aries -> HoroscopeModel.Aries
                Cancer -> HoroscopeModel.Cancer
                Capricornio -> HoroscopeModel.Capricornio
                Escorpio -> HoroscopeModel.Escorpio
                Geminis -> HoroscopeModel.Geminis
                Leo -> HoroscopeModel.Leo
                Libra -> HoroscopeModel.Libra
                Piscis -> HoroscopeModel.Piscis
                Sagitario -> HoroscopeModel.Sagitario
                Tauro -> HoroscopeModel.Tauro
                Virgo -> HoroscopeModel.Virgo
            }

            findNavController().navigate(
                HoroscopeFragmentDirections.actionHoroscopeFragmentToHoroscopeDetailActivity(type)
            )
        })

        binding.rvHoroscope.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = horoscopeAdapter
        }
    }

    private fun iniUiState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                horoscopeViewModel.horoscope.collect {
                    //CHANGES EN HOROSCOPE
                    horoscopeAdapter.updateList(it)

                }
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = inflate(layoutInflater, container, false)
        return binding.root
    }

}