package com.shatokhin.navigheroesdota2.data.localstorage

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.shatokhin.navigheroesdota2.utilites.NAME_FILE_HEROES
import com.shatokhin.navigheroesdota2.utilites.TAG_FOR_LOGCAT
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStreamReader

class ManagerFileStorage(private val appContext: Context) {

    fun addHeroes(json: String) {
        val fileOutput = appContext.openFileOutput(NAME_FILE_HEROES, AppCompatActivity.MODE_APPEND)
        val hero = json.toByteArray()
        fileOutput.write(hero)
        fileOutput.close()
    }

    fun getListAllHeroes(): String? {
        try {
            val inputStream = appContext.openFileInput(NAME_FILE_HEROES)
            val reader = InputStreamReader(inputStream)
            val bufferReader = BufferedReader(reader)
            val allHeroesJson = StringBuffer()
            bufferReader.forEachLine { line ->
                allHeroesJson.appendLine(line)
            }

            return allHeroesJson.toString()

        } catch (error: FileNotFoundException) {
            error.message?.let {
                Log.d(TAG_FOR_LOGCAT, error.toString())
            }

        } catch (error: IOException) {
            Log.d(TAG_FOR_LOGCAT, error.toString())
        }

        return null
    }

    fun deleteFile() {
        appContext.deleteFile(NAME_FILE_HEROES)
    }
}