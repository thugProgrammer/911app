package com.example.customapp.async;

import android.content.Context;
import android.os.AsyncTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;




public class AsyncUpdater extends AsyncTask<FileOutputStream, Void, Object> {

    private ArrayList<String> details;

    public AsyncUpdater(ArrayList<String> valueDetails){
        details = valueDetails;
    }

    @Override
    protected Object doInBackground(FileOutputStream... outputStream) {

        try {

            for (String d : details){
                String line = d + "\n";
                outputStream[0].write(line.getBytes());
            }
            outputStream[0].close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



}
