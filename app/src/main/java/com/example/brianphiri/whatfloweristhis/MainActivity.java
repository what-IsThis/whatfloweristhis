package com.example.brianphiri.whatfloweristhis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private FaceFragment faceFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        faceFragment = new FaceFragment();
        if(null == savedInstanceState){
            getFragmentManager().beginTransaction().replace(R.id.container, faceFragment, "Main").commit();
        }
    }
}
