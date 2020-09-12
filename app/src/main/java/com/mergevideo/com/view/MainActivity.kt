package com.mergevideo.com.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.mergevideo.com.R
import com.mergevideo.com.commonMethod.CommonMethods
import com.mergevideo.com.commonMethod.Permissions
import com.mergevideo.com.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var mainActivityFactory : MainActivityFactory
    private lateinit var mainActivityVM : MainActivityVM
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mainActivityFactory = MainActivityFactory(this)
        mainActivityVM = ViewModelProvider(this, mainActivityFactory).get(MainActivityVM::class.java)
        binding.mainActivityVM = mainActivityVM
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            2 -> {
                if (resultCode == Activity.RESULT_OK)
                {
                    if(mainActivityVM.firstPath.get() == "")
                    {
                        mainActivityVM.firstPath.set(CommonMethods.getPath(data!!.data!!, this))
                    }
                    else
                    {
                        mainActivityVM.secondPath.set(CommonMethods.getPath(data!!.data!!, this))
                    }
                }
            }

            6 -> {
                if (resultCode == Activity.RESULT_OK)
                {
                    if(mainActivityVM.firstPath.get() == "")
                    {
                        mainActivityVM.firstPath.set(CommonMethods.getPath(data!!.data!!, this))
                    }
                    else
                    {
                        mainActivityVM.secondPath.set(CommonMethods.getPath(data!!.data!!, this))
                    }
                }
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode)
        {
            1 ->
            {
                if (Permissions.permissionCheck2(this))
                {
                    mainActivityVM.pickGallery()
                }
            }

            2 ->
            {
                if (Permissions.permissionCheck2(this))
                {
                    mainActivityVM.onCameraClick()
                }
            }
        }
    }

}