package com.apcachef.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.ProcessLifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apcachef.BuildConfig;
import com.apcachef.R;
import com.apcachef.Services.ServiceGenerator;
import com.apcachef.adapter.CulinaryProgramAdapter;
import com.apcachef.adapter.MasterChefSeriesAdapter;
import com.apcachef.adapter.MasterProgramAdapter;
import com.apcachef.adapter.MultiViewTypeAdapter;
import com.apcachef.adapter.PastryProgramAdapter;
import com.apcachef.adapter.ShortLessonAdapter;
import com.apcachef.adapter.TestimonialAdapter;
import com.apcachef.base.BaseActivity;
import com.apcachef.constant.AppConstants;
import com.apcachef.fragments.PastryFragment;
import com.apcachef.fragments.SliderFragment;
import com.apcachef.interfaces.OnListItemClickListener;
import com.apcachef.model.MasterChefSeries.MasterChefSeriesItem;
import com.apcachef.model.Model;
import com.apcachef.model.PagerModel;
import com.apcachef.model.RenewStatus;
import com.apcachef.model.RenewStatusRequest;
import com.apcachef.model.detail.CourseDetailRequest;
import com.apcachef.model.goodCustomer.GoodCustomerRequest;
import com.apcachef.model.home.ChefsItem;
import com.apcachef.model.home.HomePageResponse;
import com.apcachef.model.home.MasterClassItem;
import com.apcachef.model.home.SubsItem;
import com.apcachef.model.home.TestimonialItem;
import com.apcachef.model.home.TopicsItem;
import com.apcachef.model.shortLesson.RowsItem;
import com.apcachef.model.shortLesson.ShortLessonResponse;
import com.apcachef.model.subscribed.SubscribeRequest;
import com.apcachef.model.subscribed.SubscribedResponse;
import com.apcachef.networking.ApiClient;
import com.apcachef.networking.ApiService;
import com.apcachef.util.AutoScrollViewPager;
import com.apcachef.util.SharedPrefsHelper;
import com.ct7ct7ct7.androidvimeoplayer.model.PlayerState;
import com.ct7ct7ct7.androidvimeoplayer.view.VimeoPlayerActivity;
import com.ct7ct7ct7.androidvimeoplayer.view.VimeoPlayerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.Gravity.CENTER;

public class MainActivity extends BaseActivity implements View.OnClickListener, OnListItemClickListener, LifecycleObserver {
    public NavigationView navigationView;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle drawerToggle;
    protected FrameLayout frameLayout;
    Toolbar toolbar;
    boolean doubleBackToExitPressedOnce = false;
    AutoScrollViewPager viewPager;
    private int REQUEST_CODE = 1234;
    private int REQUEST_CODE_LOCATION = 201;
    LinearLayout layout_Profile;
    TextView btn_pastry_arts, btn_culinary_arts, tv_get_started, tv_offerText, ttt, tv_get_renew;
    BottomNavigationView bottom_navigation;
    RelativeLayout eggLess_program, so_good_customer;
    //PagerAdapter pagerAdapter;
    List<PagerModel> pagerModelList = new ArrayList<>();
    SharedPrefsHelper sharedPrefsHelper;
    VimeoPlayerView vimeoPlayerView;
    String homeVideoId;
    RecyclerView recycler_pastry_program, recycler_culinary_program, recycler_instructors, recycler_short_lesson, recycler_master_class,
            recycler_testimonials;
    PastryProgramAdapter pastryProgramAdapter;
    CulinaryProgramAdapter culinaryProgramAdapter;
    MasterProgramAdapter masterProgramAdapter;
    MasterChefSeriesAdapter masterChefSeriesAdapter;
    TestimonialAdapter testimonialAdapter;
    MultiViewTypeAdapter multiViewTypeAdapter;
    ShortLessonAdapter shortLessonAdapter;
    List<Model> modelList = new ArrayList<>();
    TextView tv_view_more, txt_pur_date;
    String firstCategoryName, secondCategoryName;
    List<SubsItem> onlineProgramList = new ArrayList<>();
    List<SubsItem> culinaryProgramList = new ArrayList<>();
    List<ChefsItem> chefsList = new ArrayList<>();
    List<TestimonialItem> testimonialList = new ArrayList<>();
    private List<MasterClassItem> masterClassList = new ArrayList<>();
    private List<MasterChefSeriesItem> masterChefSeriesList = new ArrayList<>();
    List<RowsItem> shortLessonList = new ArrayList<>();
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION};
            // REQUEST_CODE_LOCATION should be defined on your app level
            ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE_LOCATION);
        }

        ttt = findViewById(R.id.ttt);
        tv_offerText = findViewById(R.id.tv_offerText);
        txt_pur_date = findViewById(R.id.txt_pur_date);
        tv_get_started = findViewById(R.id.tv_get_started);
        tv_get_renew = findViewById(R.id.tv_get_renew);
        eggLess_program = findViewById(R.id.eggLess_program);
        so_good_customer = findViewById(R.id.so_good_customer);
  //      ttt.setText(sharedPrefsHelper.getUserId());
        // get country code ....
        AppConstants.countryCode = getCountryCode(getApplicationContext());
        Log.e("countryCode", AppConstants.countryCode);

        setupToolbar();
        /*getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);*/

        if (toolbar != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
            toolbar.setNavigationIcon(R.drawable.account);
            TextView toolbar_title = toolbar.findViewById(R.id.toolbar_title);
            toolbar_title.setText("Apca Chef Online");
        }

        initViews();

        sharedPrefsHelper = new SharedPrefsHelper(this);

        tv_view_more = findViewById(R.id.tv_view_more);

        //frameLayout = findViewById(R.id.content_frame);
        navigationView = findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        TextView user_name = header.findViewById(R.id.user_name);
        layout_Profile = header.findViewById(R.id.layout_Profile);
        drawerLayout = findViewById(R.id.drawer_layout);
        viewPager = findViewById(R.id.viewPager);

        if (sharedPrefsHelper.isLogin()) {
            user_name.setText(sharedPrefsHelper.getName());
        }

        vimeoPlayerView = findViewById(R.id.vimeoPlayer);

        // pastry program adapter...
        recycler_pastry_program = findViewById(R.id.recycler_pastry_program);
        pastryProgramAdapter = new PastryProgramAdapter(getApplicationContext(), onlineProgramList, this);
        recycler_pastry_program.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        recycler_pastry_program.setAdapter(pastryProgramAdapter);

        // culinary program adapter...
        recycler_culinary_program = findViewById(R.id.recycler_culinary_program);
        culinaryProgramAdapter = new CulinaryProgramAdapter(getApplicationContext(), culinaryProgramList, this);
        recycler_culinary_program.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        recycler_culinary_program.setAdapter(culinaryProgramAdapter);

        pagerModelList.add(new PagerModel("", "Chef Niklesh is the founder of the Academy of Pastry Arts Group and manages the operations of school in Malaysia, India and Philippines. With a Bachelors degree in Hotel Management, Chef Niklesh started his career in the field of Pastry & Bakery."));
        pagerModelList.add(new PagerModel("", "Chef Niklesh is the founder of the Academy of Pastry Arts Group and manages the operations of school in Malaysia, India and Philippines. With a Bachelors degree in Hotel Management, Chef Niklesh started his career in the field of Pastry & Bakery."));
        pagerModelList.add(new PagerModel("", "Chef Angelo Van Toorn graduated from Johnson and Wales University where he obtained degrees in both Culinary Arts and Baking and Pastry Arts. He continued on to work for Michelin Star Chef Michael Mina for several years. "));

        modelList.add(new Model(Model.LEFT_VIEW));
        modelList.add(new Model(Model.RIGHT_VIEW));

        // our instructors adapter...
        recycler_instructors = findViewById(R.id.recycler_instructors);
        multiViewTypeAdapter = new MultiViewTypeAdapter(this, chefsList, false);
        recycler_instructors.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recycler_instructors.setAdapter(multiViewTypeAdapter);

        // short lesson adapter...
        recycler_short_lesson = findViewById(R.id.recycler_short_lesson);
        shortLessonAdapter = new ShortLessonAdapter(this, shortLessonList, this);
        recycler_short_lesson.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        recycler_short_lesson.setAdapter(shortLessonAdapter);

        // master class adapter...
        recycler_master_class = findViewById(R.id.recycler_master_class);
    //    masterProgramAdapter = new MasterProgramAdapter(getApplicationContext(), masterClassList, this);
        masterChefSeriesAdapter = new MasterChefSeriesAdapter(getApplicationContext(), masterChefSeriesList);
        recycler_master_class.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        recycler_master_class.setAdapter(masterChefSeriesAdapter);

        // testimonial adapter...
        recycler_testimonials = findViewById(R.id.recycler_testimonials);
        testimonialAdapter = new TestimonialAdapter(this, testimonialList);
        recycler_testimonials.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        recycler_testimonials.setAdapter(testimonialAdapter);

        //pagerAdapter = new PagerAdapter(pagerModelList);
        //viewPager.setAdapter(pagerAdapter);
        // optional start auto scroll

        //bottom_navigation.setSelectedItemId(R.id.navigation_home);
        bottom_navigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        viewPager.startAutoScroll();
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        layout_Profile.setOnClickListener(view -> {
            if (sharedPrefsHelper.isLogin()) {
                //Toast.makeText(getApplicationContext(), "Already Login", Toast.LENGTH_SHORT).show();
            } else {
                Intent about = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(about);
            }
        });

        tv_get_started.setOnClickListener(view -> {
            if (sharedPrefsHelper.isLogin()) {
                if (!sharedPrefsHelper.isSubscribed()) {
                    Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
                    intent.putExtra("showMode", true);
                    startActivity(intent);
                }
            } else {
                Intent about = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(about);
            }
        });

        tv_get_renew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
    //            Toast.makeText(MainActivity.this, "Oooo meri jaan...", Toast.LENGTH_SHORT).show();
                if (sharedPrefsHelper.isLogin()) {
           //         if (!sharedPrefsHelper.isSubscribed()) {
                        Intent intent = new Intent(getApplicationContext(), RenewPaymentActivity.class);
                        intent.putExtra("showMode", true);
                        startActivity(intent);
           //         }
                } else {
                    Intent about = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(about);
                }
            }
        });
        tv_view_more.setOnClickListener(view -> {
            Intent intent = new Intent(this, OurInstructorsActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("chefsList", (ArrayList<? extends Parcelable>) chefsList);
            intent.putExtras(bundle);
            this.startActivity(intent);
        });
        eggLess_program.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ProgramListActivity.class);
            intent.putExtra("showMode", false);
            intent.putExtra("modeID", "3");
            intent.putExtra("id", "eggless");
            intent.putExtra("name", "EGG LESS PROGRAM");
            intent.putExtra("title", "EGGLESS CERTIFICATE PROGRAM");
            startActivity(intent);
        });
        so_good_customer.setOnClickListener(view -> {
            if (sharedPrefsHelper.isLoginGoodCustomer()) {
                Intent intent = new Intent(getApplicationContext(), GoodCustomerDetailActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(getApplicationContext(), LoginGoodCustomerActivity.class);
                startActivity(intent);
            }
        });
        checkIsSubscribed();
        if (sharedPrefsHelper.isLogin() && !sharedPrefsHelper.isSubscribed()) {
            checkIsSubscribed();
        }

        if (sharedPrefsHelper.isLogin() && sharedPrefsHelper.isSubscribed()) {
            tv_get_started.setVisibility(View.GONE);
        }
        tv_get_started.setVisibility(View.GONE);
      /*  if (sharedPrefsHelper.isSubscribed() == false){
            tv_get_started.setVisibility(View.VISIBLE);
        }else {
            tv_get_started.setVisibility(View.GONE);
        }*/

        makeRenewRequest();
        getHomePageResponse();

        getShortLessonData();

        callFragment();

        setupNavigationDrawer();

        setupDrawerToggle();

    }

    private void checkIsSubscribed() {
        ApiClient.retrofit = null;
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        SubscribeRequest subscribeRequest = new SubscribeRequest();
        subscribeRequest.setUserId(Integer.parseInt(sharedPrefsHelper.getUserId()));
        Call<SubscribedResponse> call = apiService.isSubscribed(subscribeRequest);
        call.enqueue(new Callback<SubscribedResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<SubscribedResponse> call, Response<SubscribedResponse> response) {
                if (response.body().getStatus() == 1) {
                    String msg = response.body().getMsg();
                    //Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                    if (response.body().getData().getIsSubscribed() == 1) {
                        sharedPrefsHelper.setIsSubscribed(true);
                        tv_get_started.setVisibility(View.GONE);
                    } else {
                        sharedPrefsHelper.setIsSubscribed(false);
                        tv_get_started.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<SubscribedResponse> call, Throwable t) {
                showSnackBar(getResources().getString(R.string.errorMessage));
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_LOCATION && grantResults.length > 0
                && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            throw new RuntimeException("Location services are required in order to " +
                    "connect to a reader.");
        }
    }

    public static String getCountryCode(@Nullable Context context) {
        if (context != null) {
            TelephonyManager telephonyManager =
                    (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (telephonyManager != null) {
                String countryCode = telephonyManager.getNetworkCountryIso();
                if (!TextUtils.isEmpty(countryCode)) {
                    return (countryCode.toUpperCase((Locale.US)));
                }
            }
        }
        return (Locale.getDefault().getCountry().toUpperCase(Locale.US));
    }

    private void setupView(int homeVideoId) {
        //lifecycle.addObserver(vimeoPlayer)
        ProcessLifecycleOwner.get().getLifecycle().addObserver(vimeoPlayerView);
        vimeoPlayerView.initialize(true, homeVideoId, "https://apcachefonline.com");
        //vimeoPlayerView.initialize(true, homeVideoId);
        //vimeoPlayerView.initialize(true,477104793,"9d87d664c2", "https://vimeo.com/user118749463/review/");
        vimeoPlayerView.setFullscreenVisibility(true);
        vimeoPlayerView.setFullscreenClickListener(view -> {
            String requestOrientation = VimeoPlayerActivity.REQUEST_ORIENTATION_AUTO;
            startActivityForResult(VimeoPlayerActivity.createIntent(this, requestOrientation, vimeoPlayerView),REQUEST_CODE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
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

    private void getHomePageResponse() {
        showProgressDialog();
        GoodCustomerRequest goodCustomerRequest = new GoodCustomerRequest();
        goodCustomerRequest.setUserId(sharedPrefsHelper.getUserId());
        ApiClient.retrofit = null;
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<HomePageResponse> call = apiService.getHomePageData(goodCustomerRequest);
        call.enqueue(new Callback<HomePageResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<HomePageResponse> call, Response<HomePageResponse> response) {
                hideProgressDialog();
                homeVideoId = response.body().getData().getHomeVideo();
                setupView(Integer.parseInt(homeVideoId));
                Log.e("response", String.valueOf(response));
         //       ttt.setText(response.body().getData().getInr().getPurchasedate());

                Toast.makeText(MainActivity.this, sharedPrefsHelper.getUserId(), Toast.LENGTH_SHORT).show();

                if (!AppConstants.countryCode.equals("") && AppConstants.countryCode.equals("IN")) {
                    AppConstants.symbol = response.body().getData().getInr().getSymbol();
                    AppConstants.code = response.body().getData().getInr().getCode();
                    AppConstants.price = response.body().getData().getInr().getPrice();
                    AppConstants.alertMessage = response.body().getData().getInr().getLine();
                    tv_offerText.setText(response.body().getData().getInr().getLine() + "\n" + response.body().getData().getInr().getLineSm());
                } else if (!AppConstants.countryCode.equals("") && AppConstants.countryCode.equals("US")) {
                    AppConstants.purchasedate = response.body().getData().getInr().getPurchasedate();
                    AppConstants.symbol = response.body().getData().getInr().getSymbol();
                    AppConstants.code = response.body().getData().getInr().getCode();
                    AppConstants.price = response.body().getData().getInr().getPrice();
                    AppConstants.alertMessage = response.body().getData().getInr().getLine();
                    tv_offerText.setText(response.body().getData().getUsd().getLine() + "\n" + response.body().getData().getUsd().getLineSm());
                } else if (!AppConstants.countryCode.equals("") && AppConstants.countryCode.equals("MY")) {
                    AppConstants.symbol = response.body().getData().getInr().getSymbol();
                    AppConstants.code = response.body().getData().getInr().getCode();
                    AppConstants.price = response.body().getData().getInr().getPrice();
                    AppConstants.alertMessage = response.body().getData().getInr().getLine();
                    tv_offerText.setText(response.body().getData().getMyr().getLine() + "\n" + response.body().getData().getMyr().getLineSm());
                } else {
                    AppConstants.symbol = response.body().getData().getInr().getSymbol();
                    AppConstants.code = response.body().getData().getInr().getCode();
                    AppConstants.price = response.body().getData().getInr().getPrice();
                    AppConstants.alertMessage = response.body().getData().getInr().getLine();
                    tv_offerText.setText(response.body().getData().getUsd().getLine() + "\n" + response.body().getData().getUsd().getLineSm());
                }

                firstCategoryName = response.body().getData().getCates().get(0).getName();
                secondCategoryName = response.body().getData().getCates().get(1).getName();

                onlineProgramList.addAll(response.body().getData().getCates().get(0).getSubs());
                pastryProgramAdapter.notifyDataSetChanged();

                culinaryProgramList.addAll(response.body().getData().getCates().get(1).getSubs());
                culinaryProgramAdapter.notifyDataSetChanged();

                chefsList.addAll(response.body().getData().getChefs());
                multiViewTypeAdapter.notifyDataSetChanged();

                testimonialList.addAll(response.body().getData().getTestimonial());
                testimonialAdapter.notifyDataSetChanged();

     //          masterClassList.addAll(response.body().getData().getMasterClass());
                masterChefSeriesList.addAll(response.body().getData().getMasterChefSeriesItems());
        //        masterProgramAdapter.notifyDataSetChanged();
                masterChefSeriesAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<HomePageResponse> call, Throwable t) {
                hideProgressDialog();
                showSnackBar(getResources().getString(R.string.errorMessage));
            }
        });
    }

    private void makeRenewRequest() {
        ApiService interUserdata = ServiceGenerator.createService(ApiService.class);
        interUserdata.getRenewStatus( new RenewStatusRequest(sharedPrefsHelper.getUserId())
        ).enqueue(new Callback<RenewStatus>() {
            public void onResponse(Call<RenewStatus> call, Response<RenewStatus> response) {
                Log.d(TAG, "onResponse: "+new Gson().toJson(response.body()));
                Log.d(TAG, "onResponse: "+response.message());
                Log.d(TAG, "onResponse: "+response.code());
                if (response.isSuccessful()) {
                    if (response.body().getStatus() == 1) {
                        RenewStatusRequest loginRequest = response.body().getData();
                        sharedPrefsHelper.setPurchaseDate(loginRequest.getPurchase_date());
                            if (!AppConstants.countryCode.equals("") && AppConstants.countryCode.equals("IN")) {
                                if (loginRequest.getPurchase_date().equals("2020")){
                                    tv_get_renew.setVisibility(View.VISIBLE);
                                }else{
                                    tv_get_renew.setVisibility(View.GONE);
                                }

                            } else if (!AppConstants.countryCode.equals("") && AppConstants.countryCode.equals("US")) {
                                if (loginRequest.getPurchase_date().equals("2020")){
                                    tv_get_renew.setVisibility(View.VISIBLE);
                                }else{
                                    tv_get_renew.setVisibility(View.GONE);
                                }

                            } else if (!AppConstants.countryCode.equals("") && AppConstants.countryCode.equals("MY")) {
                                if (loginRequest.getPurchase_date().equals("2020")){
                                    tv_get_renew.setVisibility(View.VISIBLE);
                                }else{
                                    tv_get_renew.setVisibility(View.GONE);
                                }

                            } else {
                                if (loginRequest.getPurchase_date().equals("2020")){
                                    tv_get_renew.setVisibility(View.VISIBLE);
                                }else{
                                    tv_get_renew.setVisibility(View.GONE);
                                }

                            }
                        ttt.setText(loginRequest.getPurchase_date());
                        Log.d(TAG, "onResponse: "+loginRequest);
                //        Toast.makeText(MainActivity.this, "" + "success", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<RenewStatus> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
                Toast.makeText(MainActivity.this, "not success", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getShortLessonData() {
        //showProgressDialog();
        CourseDetailRequest shortVideoRequest = new CourseDetailRequest();
        shortVideoRequest.setId("1");
        shortVideoRequest.setUserId(sharedPrefsHelper.getUserId());
        ApiClient.retrofit = null;
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<ShortLessonResponse> call = apiService.getShortLesson(shortVideoRequest);

        call.enqueue(new Callback<ShortLessonResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<ShortLessonResponse> call, Response<ShortLessonResponse> response) {
                //hideProgressDialog();
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    shortLessonList.addAll(response.body().getData().getRows());
                    shortLessonAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ShortLessonResponse> call, Throwable t) {
                hideProgressDialog();
                showSnackBar(getResources().getString(R.string.errorMessage));
            }
        });
    }

    private void initViews() {
        btn_pastry_arts = findViewById(R.id.btn_pastry_arts);
        btn_culinary_arts = findViewById(R.id.btn_culinary_arts);
        bottom_navigation = findViewById(R.id.bottom_navigation);

        btn_pastry_arts.setOnClickListener(this);
        btn_culinary_arts.setOnClickListener(this);
    }

    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setupDrawerToggle() {
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerToggle.setDrawerIndicatorEnabled(false);
        drawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        drawerToggle.setHomeAsUpIndicator(R.mipmap.hamburger_icon);
    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            //openFragment(HomeFragment.newInstance("", ""));
                            return true;
                        case R.id.navigation_courses:
                            if (sharedPrefsHelper.isSubscribed()) {
                                startActivity(MyCoursesActivity.class);
                            }else {
                                warningDialog();
                            }
                            return true;
                        case R.id.navigation_account:
                            if (sharedPrefsHelper.isLogin()) {
                                startActivity(MyAccountActivity.class);
                            } else {
                                startActivity(LoginActivity.class);
                            }

                            return true;
                    }
                    return false;
                }
            };


    private void setupNavigationDrawer() {
        Menu nav_Menu = navigationView.getMenu();
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            drawerLayout.closeDrawers();

            switch (menuItem.getItemId()) {

                case R.id.faq:
                    Intent faq = new Intent(getApplicationContext(), FAQActivity.class);
                    faq.putExtra("type", "faq");
                    startActivity(faq);
                    break;

                case R.id.menu_terms:
                    Intent terms = new Intent(getApplicationContext(), TermsActivity.class);
                    terms.putExtra("type", "terms");
                    startActivity(terms);
                    break;

                case R.id.contact:
                    Intent contact = new Intent(getApplicationContext(), ContactUsActivity.class);
                    contact.putExtra("type", "contact");
                    startActivity(contact);
                    break;

                case R.id.mycourses:
                    if (sharedPrefsHelper.isSubscribed()) {
                        startActivity(MyCoursesActivity.class);
                    }else {
                        warningDialog();
                    }
                    break;

                case R.id.eGift:
                    startActivity(EGiftActivity.class);
                    break;

                case R.id.short_lesson:
                    startActivity(ShortLessonActivity.class);
                    break;

                case R.id.instructors:
                    Intent intent = new Intent(this, OurInstructorsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("chefsList", (ArrayList<? extends Parcelable>) chefsList);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    break;

                case R.id.about_us:
                    Intent about = new Intent(getApplicationContext(), AboutUsActivity.class);
                    about.putExtra("type", "about");
                    startActivity(about);
                    break;

                case R.id.menu_privacy:
                    Intent privacy = new Intent(getApplicationContext(), PrivacyActivity.class);
                    privacy.putExtra("type", "privacy");
                    startActivity(privacy);
                    break;

                case R.id.menu_share:

                    try {
                        Intent shareIntent = new Intent(Intent.ACTION_SEND);
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "");
                        String shareMessage= "\nLearn to \n Bake & Cook At Home \n From the Best in The World\n\n";
                        shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
                        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                        startActivity(Intent.createChooser(shareIntent, "choose one"));
                    } catch(Exception e) {
                        //e.toString();
                    }


                  /*  Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
                    //shareIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.take_a_look) + "\"" + getString(R.string.app_name) + "\" - " + Constant.PLAY_STORE_LINK + getPackageName());
                    shareIntent.setType("text/plain");
                    startActivity(Intent.createChooser(shareIntent, getString(R.string.share_via)));
                    break;*/

                case R.id.menu_rate:
                    //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Constant.PLAY_STORE_LINK + getPackageName())));
                    break;

                case R.id.menu_logout:
                    callLogoutFunction();
                    break;
            }

            return true;
        });

    }

    public void warningDialog() {
         final Dialog dialog = new Dialog(this, R.style.DialogTheme);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.custom_alert_dialog1);
            dialog.setCancelable(false);

            TextView tv_offerText = dialog.findViewById(R.id.tv_offerText);
            ImageView img_cancel = dialog.findViewById(R.id.img_cancel);
            Button btn_getStarted = dialog.findViewById(R.id.btn_getStarted);
            img_cancel.setOnClickListener(view -> dialog.dismiss());
            btn_getStarted.setOnClickListener(view -> {
                if (sharedPrefsHelper.isLogin()) {
                    //Toast.makeText(getApplicationContext(), "Already Login", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
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



     /*   AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setMessage("You have not Subscribed for any Program.");
        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();*/
    }
    private void callLogoutFunction() {
        if (sharedPrefsHelper.isLogin()) {
            new SharedPrefsHelper(getApplicationContext()).logout();
            Toast.makeText(getApplicationContext(), "Logout Successful", Toast.LENGTH_LONG).show();
            startActivityWithClearTask(LoginActivity.class);
        } else {
            Toast.makeText(getApplicationContext(), "User not Login", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(navigationView))
            drawerLayout.closeDrawers();
        else
            doubleBack();
    }

    public void doubleBack() {

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, getString(R.string.exit_msg), Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_pastry_arts:
                btn_pastry_arts.setBackgroundResource(R.drawable.button_background_filled);
                btn_culinary_arts.setBackgroundResource(R.drawable.button_background);
                callFragment();
                break;

            case R.id.btn_culinary_arts:
                btn_pastry_arts.setBackgroundResource(R.drawable.button_background);
                btn_culinary_arts.setBackgroundResource(R.drawable.button_background_filled);
                callFragment();
                break;
        }
    }

    private void callFragment() {
        Bundle bundle = new Bundle();
        PastryFragment pastryFragment = new PastryFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        pastryFragment.setArguments(bundle);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_category, pastryFragment);
        transaction.commit();
    }

    @Override
    public void onItemClick(int position, String type) {

        if (type.equalsIgnoreCase("pastry")) {
            Intent intent = new Intent(getApplicationContext(), ProgramListActivity.class);
            intent.putExtra("showMode", false);
            intent.putExtra("modeID", "1");
            intent.putExtra("id", onlineProgramList.get(position).getId());
            intent.putExtra("name", firstCategoryName);
            intent.putExtra("title", onlineProgramList.get(position).getTitle());
            intent.putExtra("video_url", onlineProgramList.get(position).getVideoUrl());
            intent.putExtra("desc", onlineProgramList.get(position).getShortDescription());
            startActivity(intent);

        } else if (type.equalsIgnoreCase("culinary")) {
            Intent intent = new Intent(getApplicationContext(), ProgramListActivity.class);
            intent.putExtra("showMode", false);
            intent.putExtra("modeID", "2");
            intent.putExtra("id", culinaryProgramList.get(position).getId());
            intent.putExtra("name", secondCategoryName);
            intent.putExtra("title", culinaryProgramList.get(position).getTitle());
            intent.putExtra("video_url", culinaryProgramList.get(position).getVideoUrl());
            intent.putExtra("desc", culinaryProgramList.get(position).getShortDescription());
            startActivity(intent);

        }
     /*   else if (type.equalsIgnoreCase("master")) {
            List<TopicsItem> topics = masterClassList.get(position).getTopics();
            Intent intent = new Intent(getApplicationContext(), MasterProgramsActivity.class);
            Bundle bundle = new Bundle();
            intent.putExtra("showMode", false);
            intent.putExtra("modeID", "4");
            intent.putExtra("id", masterClassList.get(position).getId());
            intent.putExtra("name", masterClassList.get(position).getChefName());
            intent.putExtra("title", masterClassList.get(position).getTitle());
            intent.putExtra("video_url", masterClassList.get(position).getVideoUrl());
            intent.putExtra("desc", masterClassList.get(position).getChefAbout());
            bundle.putParcelableArrayList("topicsList", (ArrayList<? extends Parcelable>) topics);
            AppConstants.topics = masterClassList.get(position).getTopics();
            intent.putExtras(bundle);
            this.startActivity(intent);

        }
        else if (type.equalsIgnoreCase("master chef series")) {
            List<TopicsItem> topics = masterClassList.get(position).getTopics();
            Intent intent = new Intent(getApplicationContext(), MasterProgramsActivity.class);
            Bundle bundle = new Bundle();
            intent.putExtra("showMode", false);
            intent.putExtra("modeID", "5");
            intent.putExtra("id", masterClassList.get(position).getId());
            intent.putExtra("name", masterClassList.get(position).getChefName());
            intent.putExtra("title", masterClassList.get(position).getTitle());
            intent.putExtra("video_url", masterClassList.get(position).getVideoUrl());
            intent.putExtra("desc", masterClassList.get(position).getChefAbout());
            bundle.putParcelableArrayList("topicsList", (ArrayList<? extends Parcelable>) topics);
            AppConstants.topics = masterClassList.get(position).getTopics();
            intent.putExtras(bundle);
            this.startActivity(intent);

        }*/
        else if (type.equalsIgnoreCase("short_lesson")) {
            Intent intent = new Intent(getApplicationContext(), ProgramListActivity.class);
            intent.putExtra("showMode", false);
            intent.putExtra("modeID", "8");
            intent.putExtra("id", shortLessonList.get(position).getId());
            intent.putExtra("name", "Short Videos");
            intent.putExtra("title", shortLessonList.get(position).getTitle());
            intent.putExtra("video_url", shortLessonList.get(position).getVideoUrl());
            intent.putExtra("desc", shortLessonList.get(position).getDescription());
            startActivity(intent);
        }

    }


    public class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
            SliderFragment sliderFragment = new SliderFragment();
            sliderFragment.setData(pagerModelList.get(position));
            return sliderFragment;
        }

        @Override
        public int getCount() {
            return pagerModelList.size();
        }
    }

}