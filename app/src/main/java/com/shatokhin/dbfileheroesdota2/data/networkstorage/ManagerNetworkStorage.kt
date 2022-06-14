package com.shatokhin.dbfileheroesdota2.data.networkstorage

import android.util.Log
import com.shatokhin.dbfileheroesdota2.utilites.TAG_FOR_LOGCAT
import kotlinx.coroutines.delay
import okhttp3.*
import java.io.IOException

class ManagerNetworkStorage {

    suspend fun getListAllHeroes(): String? {
        var result: String? = null

        val fullUrl = "https://api.opendota.com/api/heroStats"
        val okHttpClient = OkHttpClient()
        val request: Request = Request.Builder()
            .url(fullUrl)
            .build()

        var attempts = 5 // количество попыток дождаться ответ с сервера
        val waitingTimeMillis = 1000L // время ожидания 1сек (1 попытка дождаться = 1 сек)
        var callCompleted = false // обработан ли вызов с интернета, если нет, ждем attempts количество раз

        okHttpClient.newCall(request).enqueue(object: Callback {

            override fun onFailure(call: Call, e: IOException) {
                callCompleted = true
                Log.d(TAG_FOR_LOGCAT, "onFailure")
            }

            override fun onResponse(call: Call, response: Response) {
                val json = response.body?.string()
                result = json
                callCompleted = true
            }
        })

        // если ответ с сервера еще в обработке, ожидаем 1 сек и снова проверяем, всего ожидаем 5 сек
        while (!callCompleted && attempts >= 0) {
            attempts-- // всего 5 попыток дождаться ответ, перед следующим ожиданием уменьшаем оставшиеся кол-во попыток
            delay(waitingTimeMillis) // ожидание еще 1 сек
        }

        return result
    }
}