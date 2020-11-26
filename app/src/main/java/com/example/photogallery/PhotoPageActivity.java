package com.example.photogallery;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.photogallery.databinding.ActivityHelpBinding;
import com.example.photogallery.databinding.ActivityPhotoPageBinding;

public class PhotoPageActivity extends AppCompatActivity {

    ActivityPhotoPageBinding binding;

    public static Intent newIntent(Context context, Uri photoPageUri) {
        Intent i = new Intent(context, PhotoPageActivity.class);
        i.setData(photoPageUri);
        return i;
    }
    protected Fragment createFragment() {
        return PhotoPageFragment.newInstance(getIntent().getData());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPhotoPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(savedInstanceState == null) {
            FragmentTransaction ft = getSupportFragmentManager()
                    .beginTransaction();
            ft.add(R.id.page_fragment_container,createFragment()).commit();
        }
    }
}