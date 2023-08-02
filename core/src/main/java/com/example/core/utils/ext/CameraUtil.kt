package com.example.core.utils.ext

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import timber.log.Timber
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

fun registerImagePickerRequest(
    page: Fragment,
    listener: PickImageAction
): ActivityResultLauncher<Intent> = registerImagePickerRequest(page, listener)

fun registerImagePickerRequest(
    page: AppCompatActivity,
    listener: PickImageAction
): ActivityResultLauncher<Intent> = registerImagePickerRequest(page, listener)

fun registerImagePickerRequest(
    page: Any,
    listener: PickImageAction
): ActivityResultLauncher<Intent>? = when(page){
    is AppCompatActivity -> page.registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            if (result.data != null && result.data!!.data != null) {
                val imgUri = result.data!!.data
                listener.onGetPathImage(imgUri!!)
            } else {
                Toast.makeText(
                    page.applicationContext,
                    "Gagal mendapatkan gambar, silahkan coba lagi.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
    is Fragment -> page.registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            if (result.data != null && result.data!!.data != null) {
                val imgUri = result.data!!.data
                listener.onGetPathImage(imgUri!!)
            } else {
                Toast.makeText(
                    page.requireContext(),
                    "Gagal mendapatkan gambar, silahkan coba lagi.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
    else -> null
}

fun registerTakePhotoRequest(
    page: AppCompatActivity,
    listener: ResultTakePhoto
): ActivityResultLauncher<Intent> = registerTakePhotoRequest(page, listener)

fun registerTakePhotoRequest(
    page: Fragment,
    listener: ResultTakePhoto
): ActivityResultLauncher<Intent> = registerTakePhotoRequest(page, listener)

fun registerTakePhotoRequest(
    page: Any,
    listener: ResultTakePhoto
): ActivityResultLauncher<Intent>? = when(page){
    is AppCompatActivity -> page.registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            listener.onSuccessTakePhoto()
        }
    }
    is Fragment -> page.registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            listener.onSuccessTakePhoto()
        }
    }
    else -> null
}

@SuppressLint("SimpleDateFormat")
@Throws(IOException::class)
fun createImageFile(context: Context, listener: CreateImageAction): File? {
    // Create an image file name
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"
    val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    val image = File.createTempFile(
        imageFileName,  /* prefix */
        ".jpg",  /* suffix */
        storageDir /* directory */
    )

    // Save a file: path for use with ACTION_VIEW intents
    listener.onGetPhotoPath(image.absolutePath)
    return image
}

fun runningTakePicture(
    activity: Activity,
    listener: CreateImageAction,
    picker: ActivityResultLauncher<Intent>
) {
    val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    var photoFile: File? = null
    try {
        photoFile = createImageFile(activity, listener)
    } catch (e: IOException) {
        // Error occurred while creating the File
        Timber.e(e)
    }
    // Continue only if the File was successfully created
    if (photoFile != null) {
        val photoURI = FileProvider.getUriForFile(
            activity, "${activity.packageName}.provider", photoFile
        )
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
        picker.launch(takePictureIntent)
    } else {
        Toast.makeText(
            activity,
            "Failed to create file photo",
            Toast.LENGTH_SHORT
        ).show()
    }
}

fun runningImagePicker(picker: ActivityResultLauncher<Intent?>) = picker.launch(Intent(
    Intent.ACTION_PICK,
    MediaStore.Images.Media.INTERNAL_CONTENT_URI
))

interface CreateImageAction {
    fun onGetPhotoPath(path: String?)
}

interface PickImageAction {
    fun onGetPathImage(uri: Uri)
}

interface ResultTakePhoto {
    fun onSuccessTakePhoto()
}