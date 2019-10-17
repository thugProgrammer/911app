package com.example.customapp.ui.details;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.customapp.R;
import com.example.customapp.async.AsyncCreator;
import com.example.customapp.async.AsyncReader;
import com.example.customapp.async.AsyncUpdater;
import com.example.customapp.ui.shareModel.ShareViewModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class DetailsFragment extends Fragment {

    private DetailsViewModel detailsViewModel;
    private EditText editId;
    private EditText editName;
    private EditText editAddress;
    private EditText editPhone;
    private EditText editEmail;
    private EditText editAge;
    private ShareViewModel model;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        detailsViewModel = ViewModelProviders.of(this).get(DetailsViewModel.class);
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

        Button btnUpdate = getActivity().findViewById(R.id.button_update);
        Button btnCreate = getActivity().findViewById(R.id.button_create);
        btnUpdate.setOnClickListener(btnUpdateOnclickListener);
        btnCreate.setOnClickListener(btnCreateOnclickListener);

        editId = getActivity().findViewById(R.id.edit_id);
        editName = getActivity().findViewById(R.id.edit_name);
        editAddress = getActivity().findViewById(R.id.edit_address);
        editPhone = getActivity().findViewById(R.id.edit_phone);
        editEmail = getActivity().findViewById(R.id.edit_email);
        editAge = getActivity().findViewById(R.id.edit_age);



        model = ViewModelProviders.of(getActivity()).get(ShareViewModel.class);

        try {
            File file = new File(getActivity().getApplicationContext().getFilesDir(), "test");
            if (file.exists()){
                FileInputStream fileInputStream = getActivity().openFileInput("test");
                AsyncReader asyncReader = new AsyncReader(getEdits());
                asyncReader.execute(fileInputStream);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    //Check if value is not empty
    public boolean isNotEmpty(EditText e){

        if (!e.getText().toString().isEmpty()){
            return true;
        }
        else{
//            v.append(" Name/email/rating is empty");
            return false;
        }
    }

    public ArrayList<String> getDetails(){
        //Edits

        if (isNotEmpty(editName) && isNotEmpty(editAddress) && isNotEmpty(editPhone) && isNotEmpty(editEmail) && isNotEmpty(editAge)){
            //Details array
            ArrayList<String> details =  new ArrayList<>();

            //Add each edit to details
            details.add(getText(editName));
            details.add(getText(editAddress));
            details.add(getText(editPhone));
            details.add(getText(editEmail));
            details.add(getText(editAge));

            return details;
        } else{
            return null;
        }



    }

    public ArrayList<EditText> getEdits(){
        ArrayList<EditText> edits = new ArrayList<>();
        edits.add(editName);
        edits.add(editAddress);
        edits.add(editPhone);
        edits.add(editEmail);
        edits.add(editAge);
        return edits;
    }


    public String getText(EditText e){
        return e.getText().toString();
    }


    private OnClickListener btnCreateOnclickListener = new OnClickListener(){

        @Override
        public void onClick(View v) {

            try {
                File file = new File(getActivity().getApplicationContext().getFilesDir(), "id");
                AsyncCreator asyncCreator = new AsyncCreator(editId);
                if (file.exists()){
                    asyncCreator.setFlag("INPUT");
                    asyncCreator.setActivity(getActivity());
                    FileInputStream fileInputStream = getActivity().openFileInput("id");
                    asyncCreator.execute(fileInputStream);
                }else{
                    FileOutputStream fileOutputStream = getActivity().openFileOutput("id", Context.MODE_PRIVATE);
                    asyncCreator.setFlag("OUTPUT");
                    asyncCreator.execute(fileOutputStream);

                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }

    };

    private OnClickListener btnUpdateOnclickListener = new OnClickListener(){

        @Override
        public void onClick(View v) {

            ArrayList<String> details = getDetails();
//            model.setDetails(getText(editName), getText(editAddress), getText(editPhone), getText(editEmail), getText(editAge));

            try {
                FileOutputStream outputStream = getActivity().openFileOutput("test", Context.MODE_PRIVATE);
                AsyncUpdater asyncUpdater = new AsyncUpdater(details);
                asyncUpdater.execute(outputStream);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }

    };
}