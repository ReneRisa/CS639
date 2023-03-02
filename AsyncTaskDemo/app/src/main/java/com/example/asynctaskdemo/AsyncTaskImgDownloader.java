package com.example.asynctaskdemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
// AsyncTask<String, Integer, Bitmap> - String is the url passed from execute,
// Integer is the status of the operation (here loading or loaded image) and
// Bitmap is the retrieved image

public class AsyncTaskImgDownloader extends AsyncTask<String, Integer, Bitmap>{

    // Constant for the logs
    public static final String ASYNCTASKIMAGEDOWNLOAD = "ASYNCTASKIMAGEDOWNLOAD";

    // Status of the download of the image - LOADING = 0 and LOADED = 1
    public static final int LOADING = 0;
    public static final int LOADED = 1;
    private Activity myActivity;

    public AsyncTaskImgDownloader(Activity activity) {
        myActivity = activity;
    }

    @Override
    //get the bitmap from the url and return it
    // The long operation is executed in the background. The string (url) is the input parameter.
    // The image is first loading and then it is loaded. The image is saved in a bitmap
    protected Bitmap doInBackground(String... imgURL) {
        publishProgress(LOADING);
        try {
            URL url = new URL(imgURL[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            if(con.getResponseCode() != 200){
                throw new Exception("Failed to connect");
            }else{
                Log.i(ASYNCTASKIMAGEDOWNLOAD, "conecction OK");
            }

            InputStream is = con.getInputStream();
            publishProgress(LOADED);
            Bitmap bmap = BitmapFactory.decodeStream(is);
            is.close();
            return bmap;
        }catch (Exception e){
            Log.e(ASYNCTASKIMAGEDOWNLOAD, "Failed to load image", e);
            Log.e(ASYNCTASKIMAGEDOWNLOAD, e.getMessage());
        }
        return null;
    }

    @Override
    //change the src of the imageview to the bitmap
    //interacts with the UI threads
    // After the execution of the long operation to download the image we display the image
    // We change the content of the image of the layout with the one from the web
    // The image is retrieved using findViewById
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        ImageView iv = (ImageView) myActivity.findViewById(R.id.imageRemote);
        if (iv == null) {
            Log.i(ASYNCTASKIMAGEDOWNLOAD, "iv null " + iv);
        }
        if (bitmap == null) {
            Log.i(ASYNCTASKIMAGEDOWNLOAD, "bitmap null " + bitmap);
        }
        if (iv != null && bitmap != null) {
            Log.i(ASYNCTASKIMAGEDOWNLOAD, "Bitmap found");
            iv.setImageBitmap(bitmap);
        } else {
            Log.i(ASYNCTASKIMAGEDOWNLOAD, "Problem with bitmap");
        }
    }

    @Override
    //change the status of the textview from loading to loaded
    //interacts with the UI threads
    // When the image is loading, a textview with the text loading is present otherwise we display This is the image!
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        TextView tv = (TextView) myActivity.findViewById(R.id.textview_loading);
        if(values[0] == LOADING){
            tv.setText("Loading..........");
        }else{
            tv.setText("These are a random image!");
        }
    }
}
