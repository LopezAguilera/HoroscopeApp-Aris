package com.ljla.yourfuture.ui.palmistry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ljla.yourfuture.databinding.FragmentPalmistryBinding
import com.ljla.yourfuture.databinding.FragmentPalmistryBinding.*

class PalmistryFragment : Fragment() {

    private var _binding: FragmentPalmistryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = inflate(layoutInflater, container, false)
        return binding.root
    }


}