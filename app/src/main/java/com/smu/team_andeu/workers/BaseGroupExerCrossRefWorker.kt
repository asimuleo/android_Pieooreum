package com.smu.team_andeu.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.smu.team_andeu.data.AppDatabase
import com.smu.team_andeu.data.Exercise
import com.smu.team_andeu.data.GroupExerCrossRef
import com.smu.team_andeu.utilities.DATABASE_WORKER_TAG
import com.smu.team_andeu.utilities.GROUP_EXER_CROSSREF_FILENAME
import kotlinx.coroutines.coroutineScope
import java.lang.Exception

class BaseGroupExerCrossRefWorker(
        context: Context,
        workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = coroutineScope {
        try {
            applicationContext.assets.open(GROUP_EXER_CROSSREF_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val crossType = object : TypeToken<List<GroupExerCrossRef>>() {}.type
                    val crossList: List<GroupExerCrossRef> = Gson().fromJson(jsonReader, crossType)

                    val database = AppDatabase.getInstance(applicationContext)
                    database.groupExerCrossRefDao().insertAll(crossList)
                    Result.success()
                }
            }


        } catch (ex: Exception) {
            Log.e(DATABASE_WORKER_TAG, "Error group_exer_cross base database")
            Result.failure()
        }
    }
}