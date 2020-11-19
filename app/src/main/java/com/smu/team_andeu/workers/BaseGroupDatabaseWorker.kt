package com.smu.team_andeu.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.smu.team_andeu.data.AppDatabase

import com.smu.team_andeu.data.Group
import com.smu.team_andeu.utilities.DATABASE_WORKER_TAG
import com.smu.team_andeu.utilities.GROUP_DATA_FILENAME

import kotlinx.coroutines.coroutineScope
import java.lang.Exception

// 초기  Exer 작업 수행 Worker with json
class BaseGroupDatabaseWorker(
        context: Context,
        workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = coroutineScope {
        try {
            applicationContext.assets.open(GROUP_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val groupType = object : TypeToken<List<Group>>() {}.type
                    val groupList: List<Group> = Gson().fromJson(jsonReader, groupType)

                    val database = AppDatabase.getInstance(applicationContext)
                    database.groupDao().insertAll(groupList)
                    Result.success()
                }
            }


        } catch (ex: Exception) {
            Log.e(DATABASE_WORKER_TAG, "Error group base database")
            Result.failure()
        }
    }
}