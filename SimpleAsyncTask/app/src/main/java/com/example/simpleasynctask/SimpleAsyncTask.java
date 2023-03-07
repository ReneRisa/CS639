package com.example.simpleasynctask;

import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void, Integer, String> {
    private WeakReference<TextView> mTextView;
    private WeakReference<ProgressBar> progressBar;

    private CountDownTimer mCountDownTimer;

    int i = 0;

    SimpleAsyncTask(TextView tv, ProgressBar pb){
        mTextView = new WeakReference<>(tv);
        progressBar = new WeakReference<>(pb);
    }
    @Override
    protected String doInBackground(Void... voids) {
        Random r = new Random();
        int n = r.nextInt(11);
         int s = n * 500;

         try {
             publishProgress(s);
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

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        mTextView.get().setText( "Napping...... " + values[0].toString() + " milliseconds!");
        progressBar.get().setProgress(i);
        mCountDownTimer = new CountDownTimer(values[0], 1000 ) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.v("Log_tag", "Tick of Progress"+ i + millisUntilFinished);
                i++;
                progressBar.get().setProgress((int)i*100/(values[0]/1000));
            }

            @Override
            public void onFinish() {
                i++;
                progressBar.get().setProgress(100);
            }
        };
        mCountDownTimer.start();
    }
}
