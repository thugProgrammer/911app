package com.example.customapp.ui.details;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.customapp.R;
import com.example.customapp.async.AsyncWriter;
import com.example.customapp.ui.shareModel.ShareViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DetailsFragment extends Fragment {

    private DetailsViewModel detailsViewModel;
    private EditText editName;
    private EditText editAddress;
    private EditText editPhone;
    private EditText editEmail;
    private EditText editAge;
    private ShareViewModel model;
    private DatabaseReference mDatabase;
    private FirebaseUser user;
    private String uid;
    private ShareViewModel shareViewModel;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shareViewModel = ViewModelProviders.of(getActivity()).get(ShareViewModel.class);
        View root = inflater.inflate(R.layout.fragment_details, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        detailsViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initializeComponents();
    }


    public void initializeComponents(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        user = FirebaseAuth.getInstance().getCurrentUser();
        editName = getActivity().findViewById(R.id.edit_name);
        editAddress = getActivity().findViewById(R.id.edit_address);
        editPhone = getActivity().findViewById(R.id.edit_phone);
        editEmail = getActivity().findViewById(R.id.edit_email);
        editAge = getActivity().findViewById(R.id.edit_age);
        Button btnUpdate = getActivity().findViewById(R.id.button_update);
        btnUpdate.setOnClickListener(btnUpdateListener);

        assert user != null;
        uid = user.getUid();


        mDatabase.child("Users").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                String name = (String) Objects.requireNonNull(map.get("name"));
                String address = (String) Objects.requireNonNull(map.get("address"));
                String phone = (String) Objects.requireNonNull(map.get("phone"));
                String email = (String) Objects.requireNonNull(map.get("email"));
                String age = (String) Objects.requireNonNull(map.get("age"));
                editName.setText(name); editAddress.setText(address); editPhone.setText(phone);
                editEmail.setText(email); editAge.setText(age);
                shareViewModel.setDetails(getEditText("name"), getEditText("address"), getEditText("phone"), getEditText("email"), getEditText("age"));



            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }


    private String getEditText(String flag){
        switch (flag){
            case "name" : return editName.getText().toString();
            case "phone" : return editPhone.getText().toString();
            case "address" : return editAddress.getText().toString();
            case "email" : return editEmail.getText().toString();
            case "age" : return editAge.getText().toString();
        }
        return null;
    }



    private View.OnClickListener btnUpdateListener =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {



            HashMap<String, Object> hashMap = new HashMap<>();

            hashMap.put("name", getEditText("name"));
            hashMap.put("address", getEditText("address"));
            hashMap.put("phone", getEditText("phone"));
            hashMap.put("email", getEditText("email"));
            hashMap.put("age", getEditText("age"));


            AsyncWriter asyncWriter = new AsyncWriter(uid, hashMap, "UPDATE");
            asyncWriter.execute(mDatabase);
        }
    };




}