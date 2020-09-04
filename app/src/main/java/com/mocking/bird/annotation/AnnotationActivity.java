package com.mocking.bird.annotation;

import android.os.Bundle;
import android.widget.TextView;

import com.mocking.bird.R;

import androidx.appcompat.app.AppCompatActivity;

public class AnnotationActivity extends AppCompatActivity {
    @Autowired("name")
    String mName;

    @Autowired
    int age;

    @BindView(R.id.name_tv)
    TextView mNameTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotation);
        AutowiredUtils.inject(this);
        BindViewUtils.InjectView(this);

        mNameTv.setText(mName + "..." + age);
    }
}
