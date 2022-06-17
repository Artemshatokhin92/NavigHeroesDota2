package com.shatokhin.navigheroesdota2.domain.repository

import com.shatokhin.navigheroesdota2.data.models.HeroJson

interface Repository {
    suspend fun getListAllHeroes(): List<HeroJson>
}