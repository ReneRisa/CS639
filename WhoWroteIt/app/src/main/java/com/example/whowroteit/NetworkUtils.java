package com.example.whowroteit;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
/*Create a new Java class in your app called NetworkUtils. The NetworkUtils class does not extend from any other class.
For logging, create a LOG_TAG variable with the name of the class:*/
public class NetworkUtils {
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();
    //Create the following member constants at the top of the the NetworkUtils class, below the LOG_TAG constant:
    // Base URL for Books API.
    private static final String BOOK_BASE_URL =  "https://www.googleapis.com/books/v1/volumes?";
    // Parameter for the search string.
    private static final String QUERY_PARAM = "q";
    // Parameter that limits search results.
    private static final String MAX_RESULTS = "maxResults";
    // Parameter to filter by print type.
    private static final String PRINT_TYPE = "printType";

    //Create a static method named getBookInfo(). The getBookInfo() method takes the search term as a String parameter and returns the JSON String response from the API you examined earlier.
    static String getBookInfo(String queryString){
        //Create the following local variables in the getBookInfo() method. You will need these variables for connecting to the internet, reading the incoming data, and holding the response string.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String bookJSONString = null;
        /*Add a skeleton try/catch/finally block in getBookInfo(), after the local variables and before the return statement.
In the try block, you'll build the URI and issue the query. In the catch block, you'll handle problems with the request. In the finally block, you'll close the network connection after you finish receiving the JSON data.*/
        try{
            /*As you saw in the request on the Books API web page, all of the requests begin with the same URI. To specify the type of resource, append query parameters to that base URI. It is common practice to separate all of these query parameters into constants, and combine them using an Uri.Builder so they can be reused for different URIs. The Uri class has a convenient method, Uri.buildUpon(), that returns a URI.Builder that you can use.

For this app, you limit the number and type of results returned to increase the query speed. To restrict the query, you will only look for books that are printed.

In the getBookInfo() method, build your request URI in the try block:*/
            Uri builtURI = Uri.parse(BOOK_BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, queryString)
                    .appendQueryParameter(MAX_RESULTS, "10")
                    .appendQueryParameter(PRINT_TYPE, "books")
                    .build();
            //Also inside the try block, convert your URI to a URL object:
            URL requestURL = new URL(builtURI.toString());
            /* Make the request
This API request uses the HttpURLConnection class in combination with an InputStream, BufferedReader, and a StringBuffer to obtain the JSON response from the web. If at any point the process fails and InputStream or StringBuffer are empty, the request returns null,
signifying that the query failed.
In the try block of the getBookInfo() method, open the URL connection and make the request:*/
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            //Also inside the try block, set up the response from the connection using an InputStream, a BufferedReader and a StringBuilder.
            // Get the InputStream. it gets the json strings
            InputStream inputStream = urlConnection.getInputStream();

            // Create a buffered reader from that input stream.
            reader = new BufferedReader(new InputStreamReader(inputStream));

            // Use a StringBuilder to hold the incoming response.
            StringBuilder builder = new StringBuilder();
//Read the input line-by-line into the string while there is still input:
            String line;
            //The while loop adds the incoming line to the builder string in two steps: one step for the line of response data, and one step to add the new line character ("\n").
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                // Since it's JSON, adding a newline isn't necessary (it won't
                // affect parsing) but it does make debugging a *lot* easier
                // if you print out the completed buffer for debugging.
                builder.append("\n");
            }
            //At the end of the input, check the string to see if there is existing response content. Return null if the response is empty.
            if(builder.length() == 0){
                // Stream was empty. No point in parsing.
                return null;
            }
            //Convert the StringBuilder object to a String and store it in the bookJSONString variable.
            bookJSONString = builder.toString();
        }catch (IOException e){
            e.printStackTrace();
            //In the finally block, close both the connection and the BufferedReader:
        }finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        /*Note: Each time the connection fails for any reason, this code returns null. This means that the onPostExecute() in the FetchBook class has to check its input parameter for a null string and let the user know about the failure.

This error handling strategy is simplistic, because the user has no idea why the connection failed. A better solution for a production app is to handle each point of failure differently so that the user gets helpful feedback.*/
        //Just before the final return, print the value of the bookJSONString variable to the log.
        Log.d(LOG_TAG, bookJSONString);
        //At the end of the getBookInfo() method, return the value of bookJSONString.
        return bookJSONString;
    }
}
