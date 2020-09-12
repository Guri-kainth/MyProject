package com.mergevideo.com.bindingadapter

import android.net.Uri
import android.widget.VideoView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.mergevideo.com.view.MainActivityVM

object BindingAdapter {

    @BindingAdapter(value = ["setVideo","setListener"], requireAll = false)
    @JvmStatic
    fun setVideo(videoView: VideoView,uri:ObservableField<String>, mainActivityVM: MainActivityVM) {
        if (uri.get()!!.isNotEmpty())
        {
            videoView.setVideoURI(Uri.parse(uri.get()))
            videoView.start()

            videoView.setOnCompletionListener { mainActivityVM.videoViewBoolean.set(false) }
        }
    }

    @BindingAdapter(value = ["stopVideo"], requireAll = false)
    @JvmStatic
    fun stopVideo(videoView: VideoView, observableBoolean : ObservableBoolean) {
        if (!observableBoolean.get())
        {
            if (videoView.isPlaying)
            {
                videoView.pause()
                videoView.suspend()
            }
        }
    }

}