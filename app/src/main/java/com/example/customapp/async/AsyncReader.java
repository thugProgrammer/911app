package com.example.customapp.async;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class AsyncReader extends AsyncTask<FileInputStream, Void , ArrayList<String>> {


    private ArrayList<EditText> edits;

    public AsyncReader(ArrayList<EditText> valueEdits){
        edits = valueEdits;
    }

    @Override
    protected ArrayList<String> doInBackground(FileInputStream... inputStream) {
        int by;
        String union = "";
        ArrayList<String> details = new ArrayList<>();
        try {
            while ((by = inputStream[0].read()) != -1){
                char c = (char) by;

                if (Character.toString(c).equals("\n")){
                    Log.i("Caracter", union);
                    details.add(union);
                    union = "";

                }else{
                    union += Character.toString(c);

                }
            }

            inputStream[0].close();
        } catch (
        IOException e) {
            e.printStackTrace();
        }


        return details;
    }


    //Get the string returned from doInBackground(Integer... integers)
    @Override
    protected void onPostExecute(ArrayList<String> details) {
        super.onPostExecute(details);

        for (int i = 0; i < details.size(); i++){
             edits.get(i).setText(details.get(i));
        }
    }



}
