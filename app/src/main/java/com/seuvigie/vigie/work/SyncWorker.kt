package com.seuvigie.vigie.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.seuvigie.domain.repository.SyncScheduler

//Worker = tarefa que roda em background
class SyncWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    //doWork() = o que será executado
    override suspend fun doWork(): Result {
        return try {
            // Aqui vai a lógica
            println("Sincronizando dados...")

            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }
}


class WorkManagerSyncScheduler(
    private val context: Context
) : SyncScheduler {

    override fun scheduleSync() {
        val request = OneTimeWorkRequestBuilder<SyncWorker>().build()

        WorkManager
            .getInstance(context)
            .enqueue(request)
    }
}