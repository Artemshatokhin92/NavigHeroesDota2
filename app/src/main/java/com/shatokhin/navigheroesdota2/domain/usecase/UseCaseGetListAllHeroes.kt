package com.shatokhin.navigheroesdota2.domain.usecase

import com.shatokhin.navigheroesdota2.data.models.HeroJson
import com.shatokhin.navigheroesdota2.domain.repository.Repository

class UseCaseGetListAllHeroes(private val repository: Repository) {
    suspend fun execute(): List<HeroJson> {
        return repository.getListAllHeroes()
    }
}