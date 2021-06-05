package com.apcachef.activity;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.apcachef.R;
import com.apcachef.adapter.RecipeListAdapter;
import com.apcachef.base.BaseActivity;
import com.apcachef.interfaces.ListVideoPlay;
import com.apcachef.interfaces.OnListItemClickListener;
import com.apcachef.model.detail.RecipeItem;
import com.ct7ct7ct7.androidvimeoplayer.model.PlayerState;
import com.ct7ct7ct7.androidvimeoplayer.view.VimeoPlayerActivity;
import com.ct7ct7ct7.androidvimeoplayer.view.VimeoPlayerView;

import java.util.List;

public class RecipeListActivity extends BaseActivity implements OnListItemClickListener, ListVideoPlay {
    ImageView img_back;
    TextView tv_title, tv_header;
    RecyclerView recyclerRecipe;
    String id, name, title;
    List<RecipeItem> recipeList;
    RecipeListAdapter recipeListAdapter;
    private int REQUEST_CODE = 123;
    VimeoPlayerView mVimeoPlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        img_back = findViewById(R.id.img_back);
        tv_title = findViewById(R.id.tv_title);
        tv_header = findViewById(R.id.tv_header);

        id = getIntent().getStringExtra("id");
        title = getIntent().getStringExtra("title");

        tv_title.setText("Recipes");
        tv_header.setText(Html.fromHtml(title));

        Bundle bundle = getIntent().getExtras();
        recipeList = bundle.getParcelableArrayList("recipeList");

        recyclerRecipe = findViewById(R.id.recyclerRecipe);
        recipeListAdapter = new RecipeListAdapter(getApplicationContext(), recipeList, this);
        recyclerRecipe.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerRecipe.setAdapter(recipeListAdapter);

        img_back.setOnClickListener(view -> {
            finish();
        });

    }

    @Override
    public void onItemClick(int position, String type) {

    }

    @Override
    public void onListVideoPlay(int position, VimeoPlayerView vimeoPlayerView) {
        mVimeoPlayerView = vimeoPlayerView;
        mVimeoPlayerView.setFullscreenClickListener(view -> {
            String requestOrientation = VimeoPlayerActivity.REQUEST_ORIENTATION_AUTO;
            startActivityForResult(VimeoPlayerActivity.createIntent(this, requestOrientation, vimeoPlayerView), REQUEST_CODE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            float playAt = data.getFloatExtra(VimeoPlayerActivity.RESULT_STATE_VIDEO_PLAY_AT, 0f);
            mVimeoPlayerView.seekTo(playAt);

            PlayerState playerState = PlayerState.valueOf(data.getStringExtra(VimeoPlayerActivity.RESULT_STATE_PLAYER_STATE));
            switch (playerState) {
                case PLAYING:
                    mVimeoPlayerView.play();
                    break;
                case PAUSED:
                    mVimeoPlayerView.pause();
                    break;
            }
        }
    }

}