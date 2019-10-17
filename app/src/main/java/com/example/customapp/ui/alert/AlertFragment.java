package com.example.customapp.ui.alert;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.customapp.R;

public class AlertFragment extends Fragment {

    private AlertViewModel alertViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        alertViewModel =
                ViewModelProviders.of(this).get(AlertViewModel.class);
        View root = inflater.inflate(R.layout.fragment_alert, container, false);
//        final TextView textView = root.findViewById(R.id.text_dashboard);
//        alertViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }
}