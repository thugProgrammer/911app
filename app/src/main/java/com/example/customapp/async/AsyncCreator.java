package com.example.customapp.async;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class AsyncCreator extends AsyncTask<Object, Void, Integer> {

    private String flag;
    @SuppressLint("StaticFieldLeak")
    private Activity activity;
    private Integer count = 0;
    @SuppressLint("StaticFieldLeak")
    private EditText id;


    public AsyncCreator(EditText valueId){
        id = valueId;
    }


    @Override
    protected Integer doInBackground(Object... stream) {
        if (flag.equals("INPUT")){
            int by;
            String union = "";

            FileInputStream inputStream = (FileInputStream) stream[0];

            try {
                while ((by = inputStream.read()) != -1){
                    char c = (char) by;
                    if (Character.toString(c).equals("\n")){
                        count = Integer.parseInt(union);
                        count += 1;
                        Log.i("Increement", count.toString());
                        union = "";
                    }else{
                        union += Character.toString(c);

                    }
                }
                FileOutputStream outputStream = activity.openFileOutput("id", Context.MODE_PRIVATE);
                String finalCount = count + "\n";
                writeToFile(outputStream, finalCount);
                return count;
            } catch (
                    IOException e) {
                e.printStackTrace();
            }

        }else{
            FileOutputStream outputStream = (FileOutputStream) stream[0];
            String initialCount = "1" + "\n";
            writeToFile(outputStream, initialCount);

        }
        return 1;

    }

    @Override
    protected void onPostExecute(Integer count) {
        super.onPostExecute(count);
        id.setText(Integer.toString(count));

    }

    public void writeToFile(FileOutputStream outputStream, String value){
        try {
            outputStream.write(value.getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setFlag(String valueFlag){
        flag = valueFlag;
    }
    public void setActivity(Activity valueActivity){
        activity = valueActivity;
    }
    public Integer getCount() {
        return count;
    }
}
