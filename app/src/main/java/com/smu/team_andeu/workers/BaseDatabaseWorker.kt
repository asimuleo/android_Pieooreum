package com.smu.team_andeu.workers

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.stream.JsonReader
import com.smu.team_andeu.data.AppDatabase
import com.smu.team_andeu.data.Exercise
import com.smu.team_andeu.utilities.EXER_DATA_FILENAME
import kotlinx.coroutines.coroutineScope
import java.lang.Exception


// 초기 작업 수행 Worker with json
class BaseDatabaseWorker(
        context: Context,
        workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = coroutineScope {
        try {
            applicationContext.assets.open(EXER_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val exerType = object : TypeToken<List<Exercise>>() {}.type
                    val exerList: List<Exercise> = Gson().fromJson(jsonReader, exerType)

                    val database = AppDatabase.getInstance(applicationContext)
                    database.exerDao().insertAll(exerList)
                    Result.success()
                }
            }
        } catch (ex: Exception) {
            Log.e(TAG, "Error base database")
            Result.failure()
        }
    }

    // 객체를 생성하지 않고 접근 가능한 변수를 선언.
    companion object {
        private const val TAG = "baseDatabaseWorker"
    }

}