package com.smu.team_andeu.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.smu.team_andeu.data.AppDatabase
import com.smu.team_andeu.data.Routine
import com.smu.team_andeu.utilities.DATABASE_WORKER_TAG
import com.smu.team_andeu.utilities.ROUTINE_DATA_FILENAME
import kotlinx.coroutines.coroutineScope
import java.lang.Exception

class BaseRoutineDatabaseWorker(
        context: Context,
        workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = coroutineScope {
        try {
            applicationContext.assets.open(ROUTINE_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val routineType = object : TypeToken<List<Routine>>() {}.type
                    val routineList: List<Routine> = Gson().fromJson(jsonReader, routineType)

                    val database = AppDatabase.getInstance(applicationContext)
                    database.routineDao().insertAll(routineList)
                    Result.success()
                }
            }


        } catch (ex: Exception) {
            Log.e(DATABASE_WORKER_TAG, "Error routine base database")
            Result.failure()
        }
    }
}