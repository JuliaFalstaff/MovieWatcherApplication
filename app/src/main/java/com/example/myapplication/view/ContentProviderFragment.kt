package com.example.myapplication.view

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentContentProviderBinding
import com.example.myapplication.viewmodel.ContentProviderViewModel

class ContentProviderFragment : Fragment() {

    private lateinit var binding: FragmentContentProviderBinding
    private val viewModel: ContentProviderViewModel by lazy {
        ViewModelProvider(this).get(ContentProviderViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentContentProviderBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermisson()
    }

    private fun checkPermisson() {
        context?.let {
            when {
                ContextCompat.checkSelfPermission(it, android.Manifest.permission.READ_CONTACTS) ==
                        PackageManager.PERMISSION_GRANTED -> {
                    getContacts()
                }
                shouldShowRequestPermissionRationale(android.Manifest.permission.READ_CONTACTS) -> {
                    AlertDialog.Builder(it)
                            .setTitle(R.string.alert_title)
                            .setMessage(R.string.alert_message)
                            .setPositiveButton(R.string.alert_apply) { _, _ ->
                                requestPermissionLauncher.launch(android.Manifest.permission.READ_CONTACTS)
                            }
                            .setNegativeButton(R.string.alert_decline) { dialog, _ ->
                                dialog.dismiss()
                            }
                            .create()
                            .show()
                }
                else -> {
                    requestPermissionLauncher.launch(android.Manifest.permission.READ_CONTACTS)
                }
            }
        }
    }

    private val requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                if (isGranted) {
                    getContacts()
                } else {
                    context?.let {
                        AlertDialog.Builder(it)
                                .setTitle(R.string.alert_title)
                                .setMessage(R.string.alert_decline_message)
                                .setNegativeButton(R.string.alert_decline_button) { dialog, _ ->
                                    dialog.dismiss()
                                }
                                .create()
                                .show()
                    }
                }
            }

    private fun getContacts() {
        Toast.makeText(requireContext(), "Get Contacts", Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance() = ContentProviderFragment()
    }
}