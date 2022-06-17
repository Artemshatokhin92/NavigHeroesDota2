package com.shatokhin.navigheroesdota2.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class HeroParcelable(
    val name: String,
    val primaryAttr: String,
    val attackType: String,
    val roles: List<String>,
    val img: String,
    val baseHealth: Int,
    val baseMana: Int,
    val baseArmor: Double,
    val baseStrength: Int,
    val baseAgility: Int,
    val baseIntellect: Int
    ): Parcelable {
        fun getHealthString() = baseHealth.toString()
        fun getManaString() = baseMana.toString()
        fun getArmorString() = baseArmor.toString()
        fun getStrengthString() = baseStrength.toString()
        fun getAgilityString() = baseAgility.toString()
        fun getIntellectString() = baseIntellect.toString()
    }