package com.shatokhin.navigheroesdota2.presentation.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shatokhin.navigheroesdota2.data.localstorage.ManagerFileStorage
import com.shatokhin.navigheroesdota2.data.networkstorage.ManagerNetworkStorage
import com.shatokhin.navigheroesdota2.data.repository.RepositoryImpl
import com.shatokhin.navigheroesdota2.domain.usecase.UseCaseGetListAllHeroes

class ViewModelListAllHeroesFactory(appContext: Context): ViewModelProvider.Factory {

    private val managerFileStorage = ManagerFileStorage(appContext)
    private val managerNetworkStorage = ManagerNetworkStorage()
    private val repositoryImpl = RepositoryImpl(managerFileStorage, managerNetworkStorage)

    private val useCaseGetListAllHeroes = UseCaseGetListAllHeroes(repositoryImpl)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ViewModelListAllHeroes(useCaseGetListAllHeroes) as T
    }

}