package com.shatokhin.dbfileheroesdota2.domain.usecase

import com.shatokhin.dbfileheroesdota2.data.models.HeroJson
import com.shatokhin.dbfileheroesdota2.domain.repository.Repository

class UseCaseGetListAllHeroes(private val repository: Repository) {
    suspend fun execute(): List<HeroJson> {
        return repository.getListAllHeroes()
    }
}