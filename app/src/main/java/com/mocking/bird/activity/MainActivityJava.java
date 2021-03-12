package com.mocking.bird.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.mocking.bird.R;
import com.mocking.bird.annotation.AnnotationActivity;
import com.mocking.bird.annotation.BindView;
import com.mocking.bird.annotation.BindViewUtils;
import com.mocking.bird.kotlin.KotlinActivity;
import com.mocking.bird.retrofit.MkRetrofit;
import com.mocking.bird.retrofit.WeatherApi;

import java.io.IOException;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivityJava extends AppCompatActivity {

    @BindView(R.id.hello_btn)
    Button mHelloBtn;

    @BindView(R.id.video_btn)
    Button mVideoBtn;

    @BindView(R.id.kotlin_btn)
    Button mKotlinBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BindViewUtils.InjectView(this);

        initEvent();

        MkRetrofit retrofit = new MkRetrofit.Builder().baseUrl("https://restapi.amap.com").build();
        WeatherApi weatherApi = retrofit.create(WeatherApi.class);
        Call call = weatherApi.getWeather("110101", "ae6c53e2186f33bbf240a12d80672d1b");


        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == 200) {
                    Log.d("Mockingbird", response.body().string());
                }
            }
        });
    }

    private void initEvent() {
        mHelloBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityJava.this, AnnotationActivity.class);
                intent.putExtra("name", "mockingbird");
                intent.putExtra("age", 11);
                startActivity(intent);
            }
        });

        mVideoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoActivity.start(MainActivityJava.this);
            }
        });

        mKotlinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityJava.this, KotlinActivity.class);
                startActivity(intent);
            }
        });
    }
}
