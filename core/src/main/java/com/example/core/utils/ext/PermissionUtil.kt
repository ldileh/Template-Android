package com.example.core.utils.ext

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

/**
 * Please add some permissions in android manifest before using this utils.
 */

fun showDialogPermission(activity: Activity){
    val alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(activity)
    alertDialogBuilder
        .setTitle("Permissions Required")
        .setMessage(
            "You have forcefully denied some of the required permissions " +
                    "for this action. Please open settings, go to permissions and allow them."
        )
        .setPositiveButton("Settings") { _, _ ->
            activity.startActivity(
                Intent(
                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.fromParts("package", activity.packageName, null)
                ).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
            )
        }
        .setNegativeButton("Cancel") { _, _ -> }
        .setCancelable(false)
        .create()
        .show()
}

fun permissionsCamera(): List<String> = mutableListOf(
    Manifest.permission.CAMERA
).apply {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
        add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }
}

fun permissionCameraArray(): Array<String> = permissionsCamera().toTypedArray()

fun isCameraPermissionsNotGranted(context: Context): Boolean {
    val checked: MutableList<Boolean> = ArrayList()
    for (item in permissionCameraArray()) {
        checked.add(
            ContextCompat.checkSelfPermission(
                context, item
            ) == PackageManager.PERMISSION_GRANTED
        )
    }
    return checked.contains(false)
}

fun registerOpenAppSettingResult(
    page: Fragment,
    listener: OpenSettingResult
): ActivityResultLauncher<Intent> = registerOpenAppSettingResult(page, listener)

fun registerOpenAppSettingResult(
    page: AppCompatActivity,
    listener: OpenSettingResult
): ActivityResultLauncher<Intent> = registerOpenAppSettingResult(page, listener)

fun registerOpenAppSettingResult(
    page: Any,
    listener: OpenSettingResult
): ActivityResultLauncher<Intent>? {
    return when(page){
        is AppCompatActivity -> page.registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { listener.onFinishOpenSettingApp() }
        is Fragment -> page.registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { listener.onFinishOpenSettingApp() }
        else -> null
    }
}

fun registerPermissionsCamera(
    page: Fragment,
    listener: PermissionsCameraResult
): ActivityResultLauncher<Array<String>> = registerPermissionsCameraObject(page, listener)!!

fun registerPermissionsCamera(
    page: AppCompatActivity,
    listener: PermissionsCameraResult
): ActivityResultLauncher<Array<String>> = registerPermissionsCameraObject(page, listener)!!

fun registerPermissionsCameraObject(
    page: Any,
    listener: PermissionsCameraResult
): ActivityResultLauncher<Array<String>>? {
    return when(page){
        is AppCompatActivity -> page.registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { result: Map<String, Boolean> ->
            listener.onFinishPermissionCamera(
                result,
                !result.containsValue(false)
            )
        }
        is Fragment -> page.registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { result: Map<String, Boolean> ->
            listener.onFinishPermissionCamera(
                result,
                !result.containsValue(false)
            )
        }
        else -> null
    }
}

fun openSettingApp(activity: Activity, picker: ActivityResultLauncher<Intent?>) {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    val uri = Uri.fromParts("package", activity.packageName, null)
    intent.data = uri
    picker.launch(intent)
}

fun runningPermissionCamera(picker: ActivityResultLauncher<Array<String>>) =
    picker.launch(permissionCameraArray())

interface OpenSettingResult {
    fun onFinishOpenSettingApp()
}

interface PermissionsCameraResult {
    fun onFinishPermissionCamera(result: Map<String, Boolean>, isAllGranted: Boolean)
}