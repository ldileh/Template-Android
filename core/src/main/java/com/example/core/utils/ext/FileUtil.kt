package com.example.core.utils.ext

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.util.Base64
import timber.log.Timber
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException

fun getFileName(pathFile: String): String {
    var file: File? = null
    try {
        file = File(pathFile)
    } catch (e: Exception) {
        Timber.e(e)
    }
    return if (file == null || !file.exists()) "-" else file.name
}

fun fileImageToBase64(file: File, contentResolver: ContentResolver?): String? {
    if (!file.exists()) return null
    val scaleDivider = 4

    // 1. Convert uri to bitmap
    val imageUri = Uri.fromFile(file)
    val fullBitmap: Bitmap
    return try {
        fullBitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageUri)

        // 2. Get the downsized image content as a byte[]
        val scaleWidth = fullBitmap.width / scaleDivider
        val scaleHeight = fullBitmap.height / scaleDivider
        val downsizedImageBytes = getDownsizedImageBytes(fullBitmap, scaleWidth, scaleHeight)
        Base64.encodeToString(downsizedImageBytes, Base64.DEFAULT)
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }
}

fun fileImageToBitmap(file: File, contentResolver: ContentResolver?): Bitmap? {
    if (!file.exists()) return null
    val scaleDivider = 4

    // 1. Convert uri to bitmap
    val imageUri = Uri.fromFile(file)
    val fullBitmap: Bitmap
    return try {
        fullBitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageUri)

        // 2. Get the downsized image content as a byte[]
        val scaleWidth = fullBitmap.width / scaleDivider
        val scaleHeight = fullBitmap.height / scaleDivider
        val downsizedImageBytes = getDownsizedImageBytes(fullBitmap, scaleWidth, scaleHeight)
        BitmapFactory.decodeByteArray(downsizedImageBytes, 0, downsizedImageBytes.size)
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }
}

fun uriImageToBase64(imageUri: Uri, contentResolver: ContentResolver?): String? {
    val scaleDivider = 4
    val fullBitmap: Bitmap
    return try {
        fullBitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageUri)

        // 2. Get the downsized image content as a byte[]
        val scaleWidth = fullBitmap.width / scaleDivider
        val scaleHeight = fullBitmap.height / scaleDivider
        val downsizedImageBytes = getDownsizedImageBytes(fullBitmap, scaleWidth, scaleHeight)
        Base64.encodeToString(downsizedImageBytes, Base64.DEFAULT)
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }
}

@Throws(IOException::class)
private fun getDownsizedImageBytes(
    fullBitmap: Bitmap,
    scaleWidth: Int,
    scaleHeight: Int
): ByteArray {
    val scaledBitmap = Bitmap.createScaledBitmap(fullBitmap, scaleWidth, scaleHeight, true)

    // 2. Instantiate the downsized image content as a byte[]
    val baos = ByteArrayOutputStream()
    scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos)
    return baos.toByteArray()
}