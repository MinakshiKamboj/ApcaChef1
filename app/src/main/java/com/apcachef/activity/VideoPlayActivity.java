package com.apcachef.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ProcessLifecycleOwner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.apcachef.R;
import com.apcachef.base.BaseActivity;
import com.ct7ct7ct7.androidvimeoplayer.model.PlayerState;
import com.ct7ct7ct7.androidvimeoplayer.view.VimeoPlayerActivity;
import com.ct7ct7ct7.androidvimeoplayer.view.VimeoPlayerView;

public class VideoPlayActivity extends BaseActivity {
    ImageView img_back;
    TextView tv_title, tv_video_title,tv_description;
    String video_url, desc, title;
    VimeoPlayerView vimeoPlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);

        img_back = findViewById(R.id.img_back);
        tv_title = findViewById(R.id.tv_title);
        tv_video_title = findViewById(R.id.tv_video_title);
        tv_description = findViewById(R.id.tv_description);
        vimeoPlayerView = findViewById(R.id.vimeoPlayer);

        video_url = getIntent().getStringExtra("video_url");
        title = getIntent().getStringExtra("title");
        desc = getIntent().getStringExtra("desc");

        tv_title.setText(title);
        tv_video_title.setText(title);
        tv_description.setText(Html.fromHtml(desc));

        setupVideoView(Integer.parseInt(video_url));
    }

    private void setupVideoView(int homeVideoId) {
        ProcessLifecycleOwner.get().getLifecycle().addObserver(vimeoPlayerView);
        vimeoPlayerView.initialize(true, homeVideoId);
        vimeoPlayerView.setFullscreenVisibility(true);

        vimeoPlayerView.setFullscreenClickListener(view -> {
            String requestOrientation = VimeoPlayerActivity.REQUEST_ORIENTATION_AUTO;
            startActivityForResult(VimeoPlayerActivity.createIntent(this, requestOrientation, vimeoPlayerView), 123);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 123) {
            float playAt = data.getFloatExtra(VimeoPlayerActivity.RESULT_STATE_VIDEO_PLAY_AT, 0f);
            vimeoPlayerView.seekTo(playAt);

            PlayerState playerState = PlayerState.valueOf(data.getStringExtra(VimeoPlayerActivity.RESULT_STATE_PLAYER_STATE));
            switch (playerState) {
                case PLAYING:
                    vimeoPlayerView.play();
                    break;
                case PAUSED:
                    vimeoPlayerView.pause();
                    break;
            }
        }
    }

}