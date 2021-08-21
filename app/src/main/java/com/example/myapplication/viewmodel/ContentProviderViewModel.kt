package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.AppState
import com.example.myapplication.model.repository.RepositoryContacts
import com.example.myapplication.model.repository.RepositoryContactsImpl

class ContentProviderViewModel(private  val repository: RepositoryContacts = RepositoryContactsImpl() ) : ViewModel() {

    val contacts: MutableLiveData<AppState> = MutableLiveData()

    fun getContacts() {
        contacts.value = AppState.Loading
        val answer = repository.getListOfContact()
        contacts.value = AppState.SuccessContentProvider(answer)
    }
}
