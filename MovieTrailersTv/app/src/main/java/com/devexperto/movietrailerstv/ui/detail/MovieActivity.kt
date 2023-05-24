package com.devexperto.movietrailerstv.ui.detail

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView
import androidx.fragment.app.FragmentActivity
import androidx.leanback.widget.AbstractDetailsDescriptionPresenter
import com.devexperto.movietrailerstv.R
import com.devexperto.movietrailerstv.domain.Movie

class MovieActivity : FragmentActivity(){

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_movie)
    val videoView = findViewById<VideoView>(R.id.vvMovie)
    val bundle = intent.extras
    val path = bundle?.getString("path")
    val uri = Uri.parse(path)
    videoView.setMediaController(MediaController(this));
    videoView.setVideoURI(uri)
    videoView.requestFocus()
    videoView.start()
  }

}