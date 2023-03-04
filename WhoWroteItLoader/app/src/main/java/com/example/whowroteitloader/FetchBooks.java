package com.example.whowroteitloader;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
/*Select Code > Override methods, or press Ctrl+O. Select the onPostExecute() method to insert the method definition into the class.
The onPostExecute() method takes a String as a parameter and returns void.*/
    /*Parse the JSON string
Now that you have a JSON response to your query, you must parse the results to extract the information you want to display in your app's UI. Java has classes in its core API help you parse and handle JSON-type data. This process, as well as updating the UI, happen in the onPostExecute() method of your FetchBook class.

There is a chance that the doInBackground() method won't return the expected JSON string. For example, the try/catch might fail and throw an exception, the network might time out, or other unhandled errors might occur. In those cases, the JSON parsing will fail and will throw an exception. To handle this case, do the JSON parsing in a try/catch block, and handle the case where incorrect or incomplete data is returned.

In the FetchBook class, in the onPostExecute() method, add a try/catch block below the call to super.*/
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            //Inside the try block, use the classes JSONObject and JSONArray to obtain the JSON array of items from the result string.
            JSONObject jsonObject = new JSONObject(s);
            JSONArray itemsArray = jsonObject.getJSONArray("items");
            //Initialize the variables used for the parsing loop.
            int i = 0;
            String title = null;
            String authors = null;
            /*Iterate through the itemsArray array, checking each book for title and author information. With each loop, test to see if both an author and a title are found, and if so, exit the loop.
            This way, only entries with both a title and author will be displayed.*/
            // Look for results in the items array, exiting
            // when both the title and author
            // are found or when all items have been checked.
            while (i < itemsArray.length() &&
                    (authors == null && title == null)) {
                // Get the current item information.
                JSONObject book = itemsArray.getJSONObject(i);
                JSONObject volumeInfo = book.getJSONObject("volumeInfo");

                // Try to get the author and title from the current item,
                // catch if either field is empty and move on.
                try {
                    title = volumeInfo.getString("title");
                    authors = volumeInfo.getString("authors");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // Move to the next item.
                i++;
            }
            //The loop ends at the first match in the response. More responses might be available, but this app only displays the first one.
            // If both are found, display the result.
            if (title != null && authors != null) {
                mTitleText.get().setText(title);
                mAuthorText.get().setText(authors);
            } else {
                // If none are found, update the UI to
                // show failed results.
                mTitleText.get().setText(R.string.no_results);
                mAuthorText.get().setText("");
            }
        } catch (JSONException e) {
            //e.printStackTrace();
            // If onPostExecute does not receive a proper JSON string,
            // update the UI to show failed results.
            mTitleText.get().setText(R.string.no_results);
            mAuthorText.get().setText("");
        }
    }
}
