package com.mocking.bird;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.mocking.bird.annotation.AnnotationActivity;
import com.mocking.bird.annotation.BindView;
import com.mocking.bird.annotation.BindViewUtils;
import com.mocking.bird.retrofit.MkRetrofit;
import com.mocking.bird.retrofit.WeatherApi;

import java.io.IOException;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.hello_tv)
    TextView mHelloTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BindViewUtils.InjectView(this);
        mHelloTv.setText("跳转");

        mHelloTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AnnotationActivity.class);
                intent.putExtra("name", "mockingbird");
                intent.putExtra("age", 11);
                startActivity(intent);
            }
        });

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
}
