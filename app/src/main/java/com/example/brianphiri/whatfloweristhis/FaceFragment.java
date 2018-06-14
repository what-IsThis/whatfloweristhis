package com.example.brianphiri.whatfloweristhis;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.brianphiri.whatfloweristhis.utils.Classifier;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.constraint.Constraints.TAG;

public class FaceFragment extends Fragment {

    private Classifier classifier;
    @BindView(R.id.ib_faceFragment)
    ImageButton camera;
    @BindView(R.id.iv_faceFragment)
    ImageView imageView;
    @BindView(R.id.tv_faceFragment_title)
    TextView title;
    @BindView(R.id.tv_faceFragment_description)
    TextView description;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_face, container, false);
        ButterKnife.bind(this, view);

        try {
            classifier = new Classifier(getActivity());
        } catch (IOException e) {
            Log.e(TAG, "Failed to initialize an image classifier.");
            Log.e(TAG, e.getMessage());
        }

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera();
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        imageView.setImageBitmap(bitmap);
        if(classifier == null || getActivity() == null) {
            Log.e(TAG, "Uninitialized Classifier or invalid context.");
            return;
        }
        HashMap<String,Float> pred = classifier.classify(bitmap);

        title.setText(pred.keySet().toArray()[0].toString());
    }

    private void openCamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
    }

}
