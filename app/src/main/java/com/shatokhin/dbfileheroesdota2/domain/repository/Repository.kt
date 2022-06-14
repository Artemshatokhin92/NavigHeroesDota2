package com.shatokhin.dbfileheroesdota2.domain.repository

import com.shatokhin.dbfileheroesdota2.data.models.HeroJson

interface Repository {
    suspend fun getListAllHeroes(): List<HeroJson>
}