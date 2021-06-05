package com.apcachef.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.apcachef.R;
import com.apcachef.util.SharedPrefsHelper;

public class SplashActivity extends AppCompatActivity {
    private static int SPALASH_TIME_OUT=3000;
    RelativeLayout anm_linear;
    Animation animation;

    private static int SPLASH_SCREEN_TIME_OUT = 3000;
    SharedPrefsHelper sharedPrefsHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);
        anm_linear=findViewById(R.id.anm_linear);
        animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animation_layout);
        anm_linear.startAnimation(animation);
      /*  new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                if (MyPreferences.getActiveInstance(SplashActivity.this).getIsLoggedIn() == true) {
                    Intent intent=new Intent(SplashActivity.this, MainHomeActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Intent i1 = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(i1);
                    finish();
                }

            }
        }, SPALASH_TIME_OUT);
*/


        sharedPrefsHelper = new SharedPrefsHelper(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sharedPrefsHelper.isLogin()) {
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }

            }
        }, SPLASH_SCREEN_TIME_OUT);
    }
}