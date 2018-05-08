package yuvi.com.fancycalcy.view.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import yuvi.com.fancycalcy.R;

public class SplashActivity extends BaseActivity {

    private TextView mSplashTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {

        mSplashTextView = (TextView)findViewById(R.id.tv_splash_text);
        mSplashTextView.setTypeface(getCodeSnippet().pacificoFont());

        //navigate after 500 milli seconds
        navigateToMainActivity();
    }

    private void navigateToMainActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
            }
        },500);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }
}
