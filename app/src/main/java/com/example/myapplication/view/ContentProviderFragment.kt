package com.example.myapplication.view

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentContentProviderBinding
import com.example.myapplication.viewmodel.ContentProviderViewModel
import java.util.jar.Manifest

const val REQUEST_CODE = 42

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
                                requestPermission()
                            }
                            .setNegativeButton(R.string.alert_decline) { dialog, _ ->
                                dialog.dismiss()
                            }
                            .create()
                            .show()
                }
                else -> {
                    requestPermission()
                }
            }
        }
    }

    private fun requestPermission() {
        requestPermissions(arrayOf(android.Manifest.permission.READ_CONTACTS), REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
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
                return
            }
        }

    }

    private fun getContacts() {
        Toast.makeText(context, "Get Contacts", Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance() = ContentProviderFragment()
    }
}