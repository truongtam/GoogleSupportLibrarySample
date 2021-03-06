package com.sample.mysamples.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.sample.mysamples.R;

import java.lang.ref.WeakReference;

/**
 * Created by truong.tam on 15/06/18.
 */
public class SplashActivity extends AppCompatActivity {

    private boolean mRunFlag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // splash.xmlをViewに指定します。
        setContentView(R.layout.splash);

        getSupportActionBar().hide();

        Handler handler = new Handler();
        // 1s遅延させてsplashHandlerを実行します。
        handler.postDelayed(new SplashRunnable(this), getResources().getInteger(R.integer.splash_time));

    }

    private static class SplashRunnable implements Runnable {

        private final WeakReference<SplashActivity> mActivity;

        public SplashRunnable(SplashActivity activity) {
            this.mActivity = new WeakReference<SplashActivity>(activity);
        }

        public void run() {
            SplashActivity activity = mActivity.get();
            if (activity == null) {
                return;
            }
            if (activity.getRunFlag()) {
                Intent intent = new Intent(activity.getApplicationContext(), LoginActivity.class);
                activity.startActivity(intent);
                activity.finish();
            }
        }
    }

    /**
     * 戻るボタンでアプリ終了.
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            // 戻るボタンの処理
            mRunFlag = false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private boolean getRunFlag() {
        return mRunFlag;
    }
}
