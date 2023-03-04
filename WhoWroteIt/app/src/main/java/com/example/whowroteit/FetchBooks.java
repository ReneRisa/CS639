package com.example.whowroteit;

import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;
/* Create a Java class in your app called FetchBook, that extends AsyncTask. The generic type parameters for the class will be <String, Void, String>.
(String because the query is a string, Void because there is no progress indicator, and String because the JSON response is a string.)*/
public class FetchBooks extends AsyncTask<String, Void, String> {
    /*To display the results in the TextView objects in MainActivity, you must have access to those text views inside the AsyncTask.
    Create WeakReference member variables for references to the two text views that show the results.
    Note: As you learned in the previous practical, you use WeakReference objects for these text views (rather than actual TextView objects) to avoid leaking context from the Activity.
    The weak references prevent memory leaks by allowing the object held by that reference to be garbage-collected if necessary.*/
    private WeakReference<TextView> mTitleText;
    private WeakReference<TextView> mAuthorText;
/*Create a constructor for the FetchBook class that includes the TextView views from MainActivity, and initialize the member variables in that constructor.*/
    FetchBooks(TextView titleText, TextView authorText){
        this.mTitleText = new WeakReference<>(titleText);
        this.mAuthorText = new WeakReference<>(authorText);
    }
    /* Implement the required method, doInBackground(). To do this, place your cursor on the red underlined text, press Alt+Enter (Option+Enter on a Mac) and select Implement methods. Choose doInBackground() and click OK.
Make sure the parameters and return types are correct. (The method takes a variable list of String objects and returns a String.)*/
    /*In FetchBook, modify the doInBackground() method to call the NetworkUtils.getBookInfo() method, passing in the search term that you obtained from the params argument passed in by the system.
    (The search term is the first value in the strings array.) Return the result of this method. (This line replaces the null return.)*/
    @Override
    protected String doInBackground(String... strings) {
        return NetworkUtils.getBookInfo(strings[0]);
    }
/*Select Code > Override methods, or press Ctrl+O. Select the onPostExecute() method to insert the method definition into the class. The onPostExecute() method takes a String as a parameter and returns void.*/
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
