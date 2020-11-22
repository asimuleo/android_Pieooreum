package com.smu.team_andeu.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.smu.team_andeu.data.AppDatabase
import com.smu.team_andeu.data.Dexer
import com.smu.team_andeu.utilities.DATABASE_WORKER_TAG
import com.smu.team_andeu.utilities.DEXER_DATA_FILENAME
import kotlinx.coroutines.coroutineScope
import java.lang.Exception

class BaseDexerDatabaseWorker(
        context: Context,
        workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = coroutineScope {
        try {
            applicationContext.assets.open(DEXER_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val dexerType = object : TypeToken<List<Dexer>>() {}.type
                    val dexerList: List<Dexer> = Gson().fromJson(jsonReader, dexerType)

                    val database = AppDatabase.getInstance(applicationContext)
                    database.dexerDao().insertAll(dexerList)
                    Result.success()
                }
            }


        } catch (ex: Exception) {
            Log.e(DATABASE_WORKER_TAG, "Error dexer base database")
            Result.failure()
        }
    }
}