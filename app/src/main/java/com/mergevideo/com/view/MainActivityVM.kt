package com.mergevideo.com.view

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.mergevideo.com.commonMethod.CommonMethods
import com.mergevideo.com.commonMethod.CommonMethods.mergeVideoEp
import com.mergevideo.com.commonMethod.Permissions

class MainActivityVM(val context:Context) : ViewModel() {

    lateinit var fileUri: Uri
    var uriField = ObservableField<String>("")
    var firstPath: ObservableField<String> = ObservableField("")
    var secondPath: ObservableField<String> = ObservableField("")
    var videoViewBoolean = ObservableBoolean(false)

    fun stopVideo()
    {
        videoViewBoolean.set(false)
    }

    fun crossVideoPath(path:String)
    {
        when(path)
        {
            "one" ->
            {
                firstPath.set("")
            }
            else ->
            {
                secondPath.set("")
            }
        }
    }

    fun onCameraClick()
    {
        if (Build.VERSION.SDK_INT >= 23)
        {
            if (Permissions.permissionCheck(context as Activity,2))
            {
                try
                {
                    val values = ContentValues()
                    fileUri = context.contentResolver.insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, values) as Uri
                    val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri)
                    intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1)
                    context.startActivityForResult(intent, 2)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        else
        {
            val values = ContentValues()
            fileUri = context.contentResolver.insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, values) as Uri
            val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri)
            intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1)
            (context as Activity).startActivityForResult(intent, 2)
        }
    }

    fun pickGallery()
    {
        if (Build.VERSION.SDK_INT >= 23)
        {
            if (Permissions.permissionCheck(context as Activity,1))
            {
                try {
                    val intent = Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
                    context.startActivityForResult(intent, 6)

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        else
        {
            try {
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
                (context as Activity).startActivityForResult(intent, 6)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun merge()
    {
        when
        {
            firstPath.get() == "" ->
            {
                Toast.makeText(context, "Select First Video", Toast.LENGTH_LONG).show()
            }
            secondPath.get() == "" ->
            {
                Toast.makeText(context, "Select Second Video", Toast.LENGTH_LONG).show()
            }
            else ->
            {
                CommonMethods.progessIsShowing(context)
                mergeVideoEp(context, firstPath.get()!!, secondPath.get()!!, object : OnMerged
                {
                    override fun mergedSuccess(parse: Uri)
                    {
                        videoViewBoolean.set(true)
                        CommonMethods.dismissProgress()
                        uriField.set(parse.toString())
                    }

                    override fun mergedFailure()
                    {
                        videoViewBoolean.set(false)
                        CommonMethods.dismissProgress()
                        Toast.makeText(context, "Some error occurred", Toast.LENGTH_LONG).show()
                    }

                    override fun mergedProgress(progress: Float)
                    {
                    }


                })
            }
        }
    }


    interface OnMerged {
        fun mergedSuccess(parse: Uri)
        fun mergedFailure()
        fun mergedProgress(progress: Float)
    }

}