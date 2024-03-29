package com.example.schoovid_app

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ext.rtmp.RtmpDataSourceFactory
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.SimpleExoPlayerView
import kotlinx.android.synthetic.main.stream.*

class Stream : Fragment() {

    private var player: SimpleExoPlayer? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.stream, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userId = arguments?.getString("userId")
        val courseId = arguments?.getString("courseId")
        val course = arguments?.getString("courseName")
        val firstname = arguments?.getString("firstname")
        val lastname = arguments?.getString("lastname")
        val date = arguments?.getString("date")?.replace("T", " ")?.replace(".000Z", "")
        val desc = arguments?.getString("description")
        val category = arguments?.getString("category")
        val level = arguments?.getString("level")

        btnBack.setOnClickListener {
            val fragment = InfoCourse()
            val args = Bundle()
            args.putString("userId", userId)
            args.putString("courseId", courseId)
            args.putString("courseName", course)
            args.putString("firstname",firstname)
            args.putString("lastname", lastname)
            args.putString("date", date)
            args.putString("description", desc)
            args.putString("category", category)
            args.putString("level", level)
            fragment.arguments = args

            parentFragmentManager
                .beginTransaction()
                .replace(R.id.container1, fragment)
                .commitAllowingStateLoss()

            }

        play(view)

        view.findViewById<ImageView>(R.id.retry).setOnClickListener {
            retry(view)
        }

    }

    private fun play(view: View) {
        println("TATA")
        val STREAMING_URL = "rtmp://193.70.36.58:1935/schoovid/94733c29-00f1-4658-a88b-af0ebf681c29"
        player = ExoPlayerFactory.newSimpleInstance(
            DefaultRenderersFactory(this.context), DefaultTrackSelector(), DefaultLoadControl()
        )
        val playerView = view.findViewById<SimpleExoPlayerView>(R.id.player_view)
        val uri = Uri.parse(STREAMING_URL)
        println("Toto " + uri)

        playerView.player = player

        val mediaSource = ExtractorMediaSource(uri, RtmpDataSourceFactory(), DefaultExtractorsFactory(), null, null)

        player?.prepare(mediaSource)
        player?.playWhenReady = true
    }

    private fun stop() {
        player?.playWhenReady = false
        player?.release()
    }

    private fun retry(view: View) {
        stop()
        play(view)
    }

}