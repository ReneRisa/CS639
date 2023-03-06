package com.example.simpleasynctask;

import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void, Void, String> {
    private WeakReference<TextView> mTextView;

    SimpleAsyncTask(TextView tv){
        mTextView = new WeakReference<>(tv);
    }
    @Override
    protected String doInBackground(Void... voids) {
        Random r = new Random();
        int n = r.nextInt(11);
         int s = n * 200;
         try {
             Thread.sleep(s);
         }catch (InterruptedException e){
             e.printStackTrace();
         }
        return "Awake at last after sleeping for " + s + " milliseconds!";
    }
//The String parameter to this method is what you defined in the third parameter of your AsyncTask class definition, and what your doInBackground() method returns.
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        // we use get() because we are using a weakreference otherwise we would not use it.
        //Because mTextView is a weak reference, you have to deference it with the get() method to get the underlying TextView object, and to call setText() on it.
        mTextView.get().setText(s);
    }
}
