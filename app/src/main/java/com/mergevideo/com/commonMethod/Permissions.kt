package com.mergevideo.com.commonMethod

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import kotlin.collections.ArrayList


object Permissions {

    fun permissionCheck(activity: Activity,selectorValue:Int): Boolean {

        if (Build.VERSION.SDK_INT >= 23)
        {
            val hasReadPermission =
                activity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            val hasWritePermission =
                activity.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
            val hasNetworkStatePermission = activity.checkSelfPermission(Manifest.permission.CAMERA)
            val hasRecordAudio = activity.checkSelfPermission(Manifest.permission.RECORD_AUDIO)

            val permissionList = ArrayList<String>()

            if (hasReadPermission != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
            if (hasWritePermission != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
            if (hasNetworkStatePermission != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(Manifest.permission.CAMERA)
            }
            if (hasRecordAudio != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(Manifest.permission.RECORD_AUDIO)
            }
            if (permissionList.isNotEmpty()) {
                activity.requestPermissions(permissionList.toTypedArray(), selectorValue)
            } else {
                return true
            }
        }
        return false
    }

    fun permissionCheck2 (activity: Activity): Boolean {

        if (Build.VERSION.SDK_INT >= 23)
        {
            val hasReadPermission =
                activity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            val hasWritePermission =
                activity.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
            val hasNetworkStatePermission = activity.checkSelfPermission(Manifest.permission.CAMERA)
            val hasRecordAudio = activity.checkSelfPermission(Manifest.permission.RECORD_AUDIO)

            val permissionList = ArrayList<String>()

            if (hasReadPermission != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
            if (hasWritePermission != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
            if (hasNetworkStatePermission != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(Manifest.permission.CAMERA)
            }
            if (hasRecordAudio != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(Manifest.permission.RECORD_AUDIO)
            }
            if (permissionList.isEmpty())
            {
                return true
            }
        }
        return false
    }

}