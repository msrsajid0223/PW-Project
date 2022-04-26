package com.example.collegeproject.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.collegeproject.Fragment.BlankFragment;
import com.example.collegeproject.R;

public class DataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        //replace fragment
        replaceFragment(new BlankFragment());

    }

    public void replaceFragment(Fragment activeFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        androidx.fragment.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.replce, activeFragment);
        transaction.commitAllowingStateLoss();
    }
}