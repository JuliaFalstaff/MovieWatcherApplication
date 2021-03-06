package com.example.myapplication.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentSettingBinding
import kotlin.properties.Delegates


class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingBinding
    private var isAdult: Boolean = false


    companion object {
        const val IS_ADULT_SETTING = "ADULT_SETTING"

        fun newInstance(bundle: Bundle): SettingsFragment {
            val fragment = SettingsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            switchAdult.isChecked = it.getPreferences(Context.MODE_PRIVATE)
                    .getBoolean(IS_ADULT_SETTING, false)
        }


        switchAdult.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                isAdult = true
                activity?.let {
                    it.getPreferences(Context.MODE_PRIVATE)
                            .edit()
                            .putBoolean(IS_ADULT_SETTING, isAdult)
                            .apply()
                }
            } else {
                isAdult = false
                activity?.let {
                    it.getPreferences(Context.MODE_PRIVATE)
                            .edit()
                            .putBoolean(IS_ADULT_SETTING, isAdult)
                            .apply()
                }
            }
        }
    }
}