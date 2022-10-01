package com.example.background.workers

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.text.TextUtils
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.background.KEY_IMAGE_URI
import timber.log.Timber

private const val TAG = "BlurWorker"

class BlurWorker(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {
    override fun doWork(): Result {
        val appContext = applicationContext
        makeStatusNotification("Blurring Image", appContext)

        val resourceUri = inputData.getString(KEY_IMAGE_URI)

        sleep()

        return try {
//            val picture = BitmapFactory.decodeResource(
//                appContext.resources,
//                R.drawable.android_cupcake
//            )

            if (TextUtils.isEmpty(resourceUri)) {
                Timber.e("Invalid input uri")
                throw IllegalArgumentException("Invalid input uri")
            }

            val picture = BitmapFactory.decodeStream(appContext.contentResolver.openInputStream(Uri.parse(resourceUri)))

            val blurred = blurBitmap(picture, appContext)
            val blurredURI = writeBitmapToFile(appContext, blurred)
            makeStatusNotification("Output is $blurredURI", appContext)

            val outputData = workDataOf(KEY_IMAGE_URI to blurredURI.toString())

            Result.success(outputData)
        } catch (t: Throwable) {
            Log.e(TAG, "Error applying blur")
            Result.failure()
        }
    }
}