package com.example.photogallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.photogallery.databinding.ActivityPhotoGalleryBinding;

public class PhotoGalleryActivity extends AppCompatActivity {
    ActivityPhotoGalleryBinding binding;


    protected Fragment createFragment() {
        return PhotoGalleryFragment.newInstance();
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, PhotoGalleryActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPhotoGalleryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(savedInstanceState == null) {
            FragmentTransaction ft = getSupportFragmentManager()
                    .beginTransaction();
            ft.add(R.id.fragment_container,createFragment()).commit();
        }
    }
}