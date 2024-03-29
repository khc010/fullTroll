package edu.ucsd.troll.app;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.ArrayList;
import org.apache.http.NameValuePair;
import java.util.HashMap;
import org.apache.http.message.BasicNameValuePair;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * Created by shalomabitan on 5/22/14.
 */
public class ProfileActivity extends Activity {

    //init the progress bar
    private ProgressDialog pDialog;

    // URL to get menu JSON
    private static String url = "http://troll.everythingcoed.com/user/login";

    // JSON Node names
    private static final String TAG_CONTACTS = "menu";
    private static final String TAG_ID = "id";
    private static final String TAG_TITLE = "title";
    private static final String TAG_DESCRIPTION = "description";
    private static final String TAG_CATEGORY = "category";
    private static final String TAG_RATING = "rating";
    private static final String TAG_SIZES = "sizes";
    private static final String TAG_SIZE = "size";
    private static final String TAG_PRICE = "price";
    //private static final String TAG_PHONE_OFFICE = "office";

    // menu JSONArray
    JSONArray menu = null;

    // Hashmap for ListView
    ArrayList<HashMap<String, String>> favoriteList;

    List<NameValuePair> params = new ArrayList<NameValuePair>();

    // Alert Dialog Manager
    AlertDialogManager alert = new AlertDialogManager();

    LoginManager login;
    //login button
    Button loginButton;

    //Edittext
    EditText usernameTextBox, passwordTextBox;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //login manager
        login = new LoginManager(getApplicationContext());

        if(!login.isLoggedIn()){


            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(i);
            finish();


        }else{
            setContentView(R.layout.profile_layout);
            //this is just for testing and debugging
            //login.logoutUser();

            TextView usernameTextView = (TextView) findViewById(R.id.user_username);

            TextView emailTextView = (TextView) findViewById(R.id.user_email);

            TextView firstNameTextView = (TextView) findViewById(R.id.user_first_name);

            TextView lastNameTextView = (TextView) findViewById(R.id.user_last_name);


            HashMap<String, String> user = login.getUserDetails();

            // name
            String username = user.get(SessionManager.KEY_USERNAME);
            // email
            String email = user.get(SessionManager.KEY_EMAIL);
            //first name
            String lastname = user.get(SessionManager.KEY_LASTNAME);
            //last name
            String firstname = user.get(SessionManager.KEY_FIRSTNAME);


            // displaying user data
            usernameTextView.setText(Html.fromHtml("Username: <b>" + username + "</b>"));

            emailTextView.setText(Html.fromHtml("Email: <b>" + email + "</b>"));

            firstNameTextView.setText(Html.fromHtml("Firstname: <b>" + firstname + "</b>"));

            lastNameTextView.setText(Html.fromHtml("LastName: <b>" + lastname + "</b>"));

            new GetFavorites().execute();


//            HashMap<String, String> favoritesMap = login.getUserFavorites();
//
//            String favorites = favoritesMap.get(SessionManager.KEY_FAVORITES);
//
//            JSONArray jObject = new JSONArray(favorites);
//            for (int i = 0; i < jObject.length(); i++,start++) {
//                JSONObject menuObject = jObject.getJSONObject(i);
//
//                String name= menuObject.getString("name");
//                String email= menuObject.getString("email");
//                String image= menuObject.getString("image");
//            }



        }


    }








    /**
     * Async task class to get json by making HTTP call
     * */
    private class GetFavorites extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance

            HashMap<String, String> favoritesMap = login.getUserFavorites();

            String favorites = favoritesMap.get(SessionManager.KEY_FAVORITES);


            Log.d("Response: ", "> " + favorites);

            if (favorites != null) {
                try {
                    JSONObject jsonObj = new JSONObject(favorites);

                    Log.d("Response: ", "=> " + jsonObj);

                    // Getting JSON Array node
                    menu = jsonObj.getJSONArray(TAG_CONTACTS);

                    // looping through All Contacts
                    for (int i = 0; i < menu.length(); i++) {
                        JSONObject c = menu.getJSONObject(i);

                        String id = c.getString(TAG_ID);
                        Log.d("ID: ", "=> " + id);
                        String title = c.getString(TAG_TITLE);
                        Log.d("TITLE: ", "=> " + title);
                        String email = c.getString(TAG_DESCRIPTION);
                        Log.d("DESCRIPTION: ", "=> " + email);
                        String category = c.getString(TAG_CATEGORY);
                        Log.d("CATEGORY: ", "=> " + category);
                        String rating = c.getString(TAG_RATING);
                        Log.d("RATING: ", "=> " + rating);

                        // Phone node is JSON Object
                        //JSONArray sizes = c.getJSONArray(TAG_SIZES);
                        // Log.d("SIZES: ", "=> " + sizes);
                        //String size = sizes.getString(TAG_SIZE);
                        // Log.d("SIZE: ", "=> " + size);
                        //String price = sizes.getString(TAG_PRICE);
                        //Log.d("PRICE: ", "=> " + price);
                        //String office = phone.getString(TAG_PHONE_OFFICE);

                        // tmp hashmap for single contact
                        HashMap<String, String> singleFavorite = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        //contact.put(TAG_ID, id);
                        singleFavorite.put(TAG_TITLE, title);
                        singleFavorite.put(TAG_DESCRIPTION, category);
                        singleFavorite.put(TAG_SIZE, rating);

                        // adding contact to contact list
                        favoriteList.add(singleFavorite);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            /**
             * Updating parsed JSON data into ListView
             * */
//            ListAdapter adapter = new SimpleAdapter(
//                    MainActivity.this, favoriteList,
//                    R.layout.profile_layout, new String[] {TAG_TITLE, TAG_DESCRIPTION,
//                    TAG_SIZE}, new int[] { R.id.title,
//                    R.id.rating, R.id.category });
//
//            setListAdapter(adapter);
        }

    }



}
