package com.example.whowroteitloader;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private EditText mBookInput;
    private TextView mTitleText;
    private TextView mAuthorText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBookInput = (EditText)findViewById(R.id.bookInput);
        mTitleText = (TextView)findViewById(R.id.titleText);
        mAuthorText = (TextView)findViewById(R.id.authorText);
        /*Run your app. You should have the same functionality as before, but now in a loader! However, when you rotate the device, the view data is lost.
        That's because when the activity is created (or recreated), the activity doesn't know that a loader is running.
        To reconnect to the loader, you need an initLoader() method in the onCreate() of MainActivity.*/

        /*
        if(getSupportLoaderManager().getLoader(0)!=null){
            getSupportLoaderManager().initLoader(0,null,this);
        }*/
        //getSupportLoaderManager() is deprecated, it can be replaced with LoaderManager.getInstance(this)
        if(LoaderManager.getInstance(this).getLoader(0) != null){
            LoaderManager.getInstance(this).initLoader(0,null,this);
        }

    }
    /**
     * onClick handler for the "Search Books" button.
     *
     * @param view The view (Button) that was clicked.
     */
    public void searchBooks(View view) {
        String queryString = mBookInput.getText().toString();
        //Hide the keyboard
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if(inputManager != null){
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
        /*Whenever your app uses the network, it needs to handle the possibility that a network connection is unavailable. Before attempting to connect to the network, your app should check the state of the network connection. In addition, it should not try to query the Books API if the user has not entered a query string.

In the searchBooks() method, use the ConnectivityManager and NetworkInfo classes to check the network connection.*/
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }
        //In MainActivity, add this line to the end of the searchBooks() method to launch the background task with the execute() method and the query string.
        //Add a test around the call to the FetchBook task and TextView updates to ensure that the network connection exists, that the network is connected, and that a query string is available.
        if(networkInfo != null && networkInfo.isConnected() && queryString.length() != 0){
            //new com.example.whowroteitloader.FetchBooks(mTitleText, mAuthorText).execute(queryString);
            Bundle queryBundle = new Bundle();
            queryBundle.putString("queryString", queryString);
            //getSupportLoaderManager() is deprecated, it can be replaced with LoaderManager.getInstance(this)
            //getSupportLoaderManager().restartLoader(0, queryBundle, this);
            LoaderManager.getInstance(this).restartLoader(0,queryBundle,this);
            //update the TextView
            mAuthorText.setText("");
            mTitleText.setText(R.string.loading);

        } else { //Add an else block to that test. In the else block, update the UI with a no_search_term error message if there is no term to search for, and a no_network error message otherwise.
            if (queryString.length() == 0) {
                mAuthorText.setText("");
                mTitleText.setText(R.string.no_search_term);
            } else {
                mAuthorText.setText("");
                mTitleText.setText(R.string.no_network);
            }
        }

    }

    //onCreateLoader() is called when you instantiate your loader.
    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        String queryString = "";

        if (args != null) {
            queryString = args.getString("queryString");
        }
        return new BookLoader(this, queryString);
    }
//onLoadFinished() is called when the loader's task finishes. This is where you add the code to update your UI with the results.
    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try {
            //Inside the try block, use the classes JSONObject and JSONArray to obtain the JSON array of items from the result string.
            // // Convert the response into a JSON object.
            JSONObject jsonObject = new JSONObject(data);
            //// Get the JSONArray of book items.
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
                mTitleText.setText(title);
                mAuthorText.setText(authors);
            } else {
                // If none are found, update the UI to
                // show failed results.
                mTitleText.setText(R.string.no_results);
                mAuthorText.setText("");
            }
        } catch (JSONException e) {
            //e.printStackTrace();
            // If onPostExecute does not receive a proper JSON string,
            // update the UI to show failed results.
            mTitleText.setText(R.string.no_results);
            mAuthorText.setText("");
        }
    }
//onLoaderReset() cleans up any remaining resources.
    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}