package com.example.yaratech.yaratube.ui.exoplayer;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.VideoView;

import com.example.yaratech.yaratube.R;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class VideoPlayerActivity extends AppCompatActivity {

    private static String TAG = VideoPlayerActivity.class.getName();
    private static String APP_NAME = "ExoPlayer";
    private PlayerView playerView;
    private SimpleExoPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);
        getSupportActionBar().hide();

        Intent intent = getIntent();
        String productVideo = intent.getStringExtra("file");

        playerView = findViewById(R.id.player);

        player = ExoPlayerFactory.newSimpleInstance(this, new DefaultTrackSelector());

        // Measures bandwidth during playback. Can be null if not required.
        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();

        // Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, APP_NAME), bandwidthMeter);

        // This is the MediaSource representing the media to be played.
        HlsMediaSource mediaSource = new HlsMediaSource
                .Factory(dataSourceFactory)
                .createMediaSource(Uri.parse(productVideo));

        player.prepare(mediaSource);
        player.setPlayWhenReady(true);
        playerView.setKeepScreenOn(true);
        playerView.setPlayer(player);

    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart() called");
        super.onStart();
        player.setPlayWhenReady(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        player.setPlayWhenReady(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        player.setPlayWhenReady(false); //to pause a video because now our video player is not in focus
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        playerView.setPlayer(null);
        player.release();
    }
}
