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
import com.example.myapplication.adapters.ContactAdapter
import com.example.myapplication.databinding.FragmentContentProviderBinding
import com.example.myapplication.model.AppState
import com.example.myapplication.viewmodel.ContentProviderViewModel

class ContentProviderFragment : Fragment() {

    private lateinit var binding: FragmentContentProviderBinding
    private val viewModel: ContentProviderViewModel by lazy {
        ViewModelProvider(this).get(ContentProviderViewModel::class.java)
    }
    private val adapter: ContactAdapter by lazy {
        ContactAdapter()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentContentProviderBinding.inflate(layoutInflater, container, false)
        binding.contentProviderRecyclerView.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.contacts.observe(viewLifecycleOwner) { renderData(it) }
        checkPermission()
    }

    private fun renderData(data: AppState?) {
        when(data) {
            is AppState.SuccessContentProvider -> {
                binding.contentProviderRecyclerView.show()
                binding.loadingLayout.hide()
                adapter.contacts = data.contact
            }
            is AppState.Loading-> {
                binding.contentProviderRecyclerView.hide()
                binding.loadingLayout.show()
            }
        }

    }

    private fun checkPermission() {
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
        viewModel.getContacts()
    }

    companion object {
        fun newInstance() = ContentProviderFragment()
    }

    private fun View.show(): View {
        if (visibility != View.VISIBLE) {
            visibility = View.VISIBLE
        }
        return this
    }

    private fun View.hide(): View {
        if (visibility != View.GONE) {
            visibility = View.GONE
        }
        return this
    }
}