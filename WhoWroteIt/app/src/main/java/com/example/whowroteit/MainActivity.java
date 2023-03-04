package com.example.whowroteit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
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

    }

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
            new FetchBooks(mTitleText, mAuthorText).execute(queryString);
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
}