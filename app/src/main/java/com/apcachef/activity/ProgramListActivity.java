package com.apcachef.activity;

import androidx.annotation.Nullable;
import androidx.lifecycle.ProcessLifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Html;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.apcachef.R;
import com.apcachef.adapter.ProgramListAdapter;
import com.apcachef.base.BaseActivity;
import com.apcachef.constant.AppConstants;
import com.apcachef.interfaces.OnListItemClickListener;
import com.apcachef.model.detail.CourseDetailRequest;
import com.apcachef.model.detail.CourseDetailResponse;
import com.apcachef.model.detail.RecipeItem;
import com.apcachef.model.detail.TopicsItem;
import com.apcachef.networking.ApiClient;
import com.apcachef.networking.ApiService;
import com.apcachef.util.SharedPrefsHelper;
import com.ct7ct7ct7.androidvimeoplayer.model.PlayerState;
import com.ct7ct7ct7.androidvimeoplayer.view.VimeoPlayerActivity;
import com.ct7ct7ct7.androidvimeoplayer.view.VimeoPlayerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.Gravity.CENTER;

public class ProgramListActivity extends BaseActivity implements OnListItemClickListener {
    ImageView img_back;
    SharedPrefsHelper sharedPrefsHelper;
    TextView tv_title, tv_header, tv_video_title, tv_description;
    RecyclerView recyclerPrograms;
    String id, name, title;
    String video_url, desc, modeID;
    List<TopicsItem> topics = new ArrayList<>();
    ProgramListAdapter programListAdapter;
    VimeoPlayerView vimeoPlayerView;
    int is_subscribed;
    boolean showMode;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_list);
        sharedPrefsHelper = new SharedPrefsHelper(this);
        img_back = findViewById(R.id.img_back);
        tv_title = findViewById(R.id.tv_title);
        tv_header = findViewById(R.id.tv_header);
        tv_video_title = findViewById(R.id.tv_video_title);
        tv_description = findViewById(R.id.tv_description);
        vimeoPlayerView = findViewById(R.id.vimeoPlayer);
        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        title = getIntent().getStringExtra("title");
        video_url = getIntent().getStringExtra("video_url");
        desc = getIntent().getStringExtra("desc");
        showMode = getIntent().getBooleanExtra("showMode", false);
        modeID = getIntent().getStringExtra("modeID");
        tv_title.setText(name);
        tv_header.setText(title);
        recyclerPrograms = findViewById(R.id.recyclerPrograms);
        programListAdapter = new ProgramListAdapter(getApplicationContext(), topics, this);
        recyclerPrograms.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerPrograms.setAdapter(programListAdapter);
        recyclerPrograms.setNestedScrollingEnabled(false);

        getCourseDetail();
        img_back.setOnClickListener(view -> {
            vimeoPlayerView.pause();
            finish();
        });

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


    private void getCourseDetail() {
        showProgressDialog();
        ApiClient.retrofit = null;
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        CourseDetailRequest courseDetailRequest = new CourseDetailRequest();
      /*  courseDetailRequest.setId("18");
        courseDetailRequest.setUserId("1009");*/
        courseDetailRequest.setId(id);
        courseDetailRequest.setUserId(sharedPrefsHelper.getUserId());
        //courseDetailRequest.setUserId("1009");  // 52

 //       tv_title.setText(id + "   "+sharedPrefsHelper.getUserId());

        Call<CourseDetailResponse> call;
        if (name.equals("Short Videos")) {
            call = apiService.getVideoDetail(courseDetailRequest);
        } else {
            call = apiService.getDetailData(courseDetailRequest);
        }
        call.enqueue(new Callback<CourseDetailResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<CourseDetailResponse> call, Response<CourseDetailResponse> response) {
                hideProgressDialog();

                title = response.body().getData().getRow().getTitle();
  //              desc = response.body().getData().getRow().getShortDescription();
                video_url = response.body().getData().getRow().getVideoUrl();

                setupVideoView(Integer.parseInt(video_url));

                tv_video_title.setText(title);
                if (desc != null) {
                    tv_description.setText(Html.fromHtml(desc));
                }

                is_subscribed = response.body().getData().getIsSubscribed();
                if (is_subscribed == 1) {
                    sharedPrefsHelper.setIsSubscribed(true);
                } else {
                    sharedPrefsHelper.setIsSubscribed(false);
                }
                topics.addAll(response.body().getData().getTopics());
                programListAdapter.notifyDataSetChanged();


                // for short video price only.....
                if (name.equals("Short Videos")) {
                    if (!AppConstants.countryCode.equals("") && AppConstants.countryCode.equals("IN")) {
                        AppConstants.symbol = response.body().getData().getInr().getSymbol();
                        AppConstants.code = response.body().getData().getInr().getCode();
                        AppConstants.price = response.body().getData().getInr().getPrice();
                        AppConstants.alertMessage = "Get Lesson in " + AppConstants.symbol + AppConstants.price;
                    } else if (!AppConstants.countryCode.equals("") && AppConstants.countryCode.equals("US")) {
                        AppConstants.symbol = response.body().getData().getInr().getSymbol();
                        AppConstants.code = response.body().getData().getInr().getCode();
                        AppConstants.price = response.body().getData().getInr().getPrice();
                        AppConstants.alertMessage = "Get Lesson in " + AppConstants.symbol + AppConstants.price;
                    } else if (!AppConstants.countryCode.equals("") && AppConstants.countryCode.equals("MY")) {
                        AppConstants.symbol = response.body().getData().getInr().getSymbol();
                        AppConstants.code = response.body().getData().getInr().getCode();
                        AppConstants.price = response.body().getData().getInr().getPrice();
                        AppConstants.alertMessage = "Get Lesson in " + AppConstants.symbol + AppConstants.price;
                    } else {
                        AppConstants.symbol = response.body().getData().getInr().getSymbol();
                        AppConstants.code = response.body().getData().getInr().getCode();
                        AppConstants.price = response.body().getData().getInr().getPrice();
                        AppConstants.alertMessage = "Get Lesson in " + AppConstants.symbol + AppConstants.price;
                    }
                }

            }

            @Override
            public void onFailure(Call<CourseDetailResponse> call, Throwable t) {
                hideProgressDialog();
                showSnackBar(getResources().getString(R.string.errorMessage));
            }
        });
    }

    @Override
    public void onItemClick(int position, String type) {
        if (sharedPrefsHelper.isSubscribed()) {
            List<RecipeItem> recipeList = topics.get(position).getRecipe();
            Intent intent = new Intent(getApplicationContext(), RecipeListActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("id", topics.get(position).getId());
            bundle.putString("title", topics.get(position).getTitle());
            bundle.putParcelableArrayList("recipeList", (ArrayList<? extends Parcelable>) recipeList);
            intent.putExtras(bundle);
            this.startActivity(intent);
        } else {
            openCustomDialog();
        }
    }
    private void openCustomDialog() {
        final Dialog dialog = new Dialog(this, R.style.DialogTheme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_alert_dialog);
        dialog.setCancelable(false);

        TextView tv_offerText = dialog.findViewById(R.id.tv_offerText);
        ImageView img_cancel = dialog.findViewById(R.id.img_cancel);
        Button btn_getStarted = dialog.findViewById(R.id.btn_getStarted);
        img_cancel.setOnClickListener(view -> dialog.dismiss());

        AppConstants.countryCode = sharedPrefsHelper.getCountryCode();
        if (!AppConstants.countryCode.equals("") && AppConstants.countryCode.equals("IN")) {
            tv_offerText.setText(AppConstants.alertMessage);
        } else if (!AppConstants.countryCode.equals("") && AppConstants.countryCode.equals("US")) {
            tv_offerText.setText(AppConstants.alertMessage);
        } else if (!AppConstants.countryCode.equals("") && AppConstants.countryCode.equals("MY")) {
            tv_offerText.setText(AppConstants.alertMessage);
        } else {
            tv_offerText.setText(AppConstants.alertMessage);
        }

        btn_getStarted.setOnClickListener(view -> {
            if (sharedPrefsHelper.isLogin()) {
                //Toast.makeText(getApplicationContext(), "Already Login", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
                intent.putExtra("showMode", showMode);
                if (name.equals("Short Videos")) {
                    intent.putExtra("modeID", "8");
                } else {
                    intent.putExtra("modeID", modeID);
                }
                startActivity(intent);
                dialog.dismiss();
            } else {
                Intent about = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(about);
                dialog.dismiss();
            }
        });

        dialog.getWindow().setGravity(CENTER);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        vimeoPlayerView.pause();
        finish();
    }

}