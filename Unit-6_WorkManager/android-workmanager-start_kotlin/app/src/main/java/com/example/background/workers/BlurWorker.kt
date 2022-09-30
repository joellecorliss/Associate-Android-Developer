package com.example.background.workers

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.background.R

private const val TAG = "BlurWorker"

class BlurWorker(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {
    override fun doWork(): Result {
        val appContext = applicationContext
        makeStatusNotification("Blurring Image", appContext)

        return try {
            val picture = BitmapFactory.decodeResource(
                appContext.resources,
                R.drawable.android_cupcake
            )

            val blurred = blurBitmap(picture, appContext)
            val blurredURI = writeBitmapToFile(appContext, blurred)
            makeStatusNotification("Output is $blurredURI", appContext)

            Result.success()
        } catch (t: Throwable) {
            Log.e(TAG, "Error applying blur")
            Result.failure()
        }
    }
}