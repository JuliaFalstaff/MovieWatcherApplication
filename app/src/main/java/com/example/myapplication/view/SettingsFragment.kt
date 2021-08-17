package com.example.myapplication.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentSettingBinding



class SettingsFragment: Fragment() {

    private lateinit var binding: FragmentSettingBinding
    private var isAdultMovie: Boolean = false

    companion object {
        const val IS_ADULT_SETTING = "ADULT_SETTING"
        const val BUNDLE_EXTRA_SETTINGS = "settings"
        fun newInstance() = SettingsFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonApplySettings.setOnClickListener {
            saveOptions()
        }
        readSettings()
    }

    private fun readSettings() {
        activity?.let {
            it.getPreferences(Context.MODE_PRIVATE).getBoolean(IS_ADULT_SETTING,false)
        }
    }

    private fun saveOptions() {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        sharedPref?.edit()?.putBoolean(IS_ADULT_SETTING, isAdultMovie)?.apply()
    }


}