package com.shatokhin.dbfileheroesdota2.data.repository

import com.shatokhin.dbfileheroesdota2.data.localstorage.ManagerFileStorage
import com.shatokhin.dbfileheroesdota2.data.models.HeroJson
import com.shatokhin.dbfileheroesdota2.data.networkstorage.ManagerNetworkStorage
import com.shatokhin.dbfileheroesdota2.domain.repository.Repository
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class RepositoryImpl(
    private val managerFileStorage: ManagerFileStorage,
    private val managerNetworkStorage: ManagerNetworkStorage): Repository {

    override suspend fun getListAllHeroes(): List<HeroJson> {
        var jsonListHero: String?

        // 1. получаем файл-json с героями из файла
        jsonListHero = managerFileStorage.getListAllHeroes()

        // 2. если в файле нету героев
        if (jsonListHero == null || jsonListHero.isEmpty()){
            // 2.1 получаем файл-json с героями из сервера
            jsonListHero = managerNetworkStorage.getListAllHeroes()

            // 2.2 сохраняем героев в файл
            if (jsonListHero != null && jsonListHero.isNotEmpty()) {
                managerFileStorage.addHeroes(jsonListHero)
            }
        }

        return if (jsonListHero != null) {
            val moshi = Moshi.Builder().build()
            val listType = Types.newParameterizedType(List::class.java, HeroJson::class.java)
            val adapter: JsonAdapter<List<HeroJson>> = moshi.adapter(listType)
            adapter.fromJson(jsonListHero)!!
        } else {
            emptyList()
        }

    }

}