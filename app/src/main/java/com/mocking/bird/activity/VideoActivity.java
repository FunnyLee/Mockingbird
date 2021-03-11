package com.mocking.bird.activity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.MediaController;

import com.mocking.bird.R;
import com.mocking.bird.view.MyVideoView;

import java.io.IOException;

import androidx.appcompat.app.AppCompatActivity;

public class VideoActivity extends AppCompatActivity {

    private SurfaceView mSurfaceView;
    private MyVideoView mVideoView;

    public static void start(Context context) {
        Intent starter = new Intent(context, VideoActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        String uri = "android.resource://" + getPackageName() + "/" + R.raw.video1;

        mSurfaceView = findViewById(R.id.surface_view);
        mVideoView = findViewById(R.id.video_view);

        initVideoView(uri);

//        initSurfaceView(uri);

    }

    private void initVideoView(String uri) {
        mSurfaceView.setVisibility(View.GONE);
        mVideoView.setVisibility(View.VISIBLE);
        MediaController controller = new MediaController(this);
        mVideoView.setMediaController(controller);
        controller.show(0);
        mVideoView.setVideoURI(Uri.parse(uri));
        mVideoView.start();
    }

    private void initSurfaceView(String uri) {
        mSurfaceView.setVisibility(View.VISIBLE);
        mVideoView.setVisibility(View.GONE);

        MediaPlayer mediaPlayer = new MediaPlayer();
        mSurfaceView.setZOrderOnTop(false);
        mSurfaceView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        mSurfaceView.getHolder().addCallback(new SurfaceHolder.Callback2() {
            @Override
            public void surfaceRedrawNeeded(SurfaceHolder holder) {

            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                mediaPlayer.setDisplay(holder);
                try {
                    mediaPlayer.setDataSource(VideoActivity.this, Uri.parse(uri));
                    mediaPlayer.prepareAsync();
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mediaPlayer.start();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });
    }
}
