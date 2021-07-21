package com.example.schoovid_app

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ext.rtmp.RtmpDataSourceFactory
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.SimpleExoPlayerView

class StreamChat : AppCompatActivity(){

    private var player: SimpleExoPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.stream_chat)

        play()

        findViewById<ImageView>(R.id.retry).setOnClickListener {
            retry()
        }
    }

    private fun play() {
        val STREAMING_URL = "rtmp://193.70.36.58:1935/schoovid/1234"
        player = ExoPlayerFactory.newSimpleInstance(
            DefaultRenderersFactory(this), DefaultTrackSelector(), DefaultLoadControl())
        val playerView = findViewById<SimpleExoPlayerView>(R.id.player_view)
        val uri = Uri.parse(STREAMING_URL)

        playerView.player = player

        val mediaSource = ExtractorMediaSource(uri, RtmpDataSourceFactory(), DefaultExtractorsFactory(), null, null)

        player?.prepare(mediaSource)
        player?.playWhenReady = true
    }

    private fun stop() {
        player?.playWhenReady = false
        player?.release()
    }

    private fun retry() {
        stop()
        play()
    }

}