package io.github.zkhan93.contactpage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.github.zkhan93.contactpage.adapter.ContactAdapter;
import io.github.zkhan93.contactpage.model.Contact;
import io.github.zkhan93.contactpage.util.Constants;
import io.github.zkhan93.contactpage.util.Util;

public class ContactActivity extends AppCompatActivity {
    private boolean back_pressed = false;
    private RecyclerView contactList;
    private ContactAdapter contactAdapter;
    private Response.Listener<JSONObject> contactResponseListener;
    private Response.ErrorListener contactErrorlistErrorListener;
    public static String TAG = "ContactActivity";

    public ContactActivity() {
        contactResponseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "response:" + response);
                try {
                    if (response.optBoolean(Constants.JSON_KEYS.AUTHENTICATION)) {
                        //login success
                        List<Contact> contacts=new ArrayList<>();
                        JSONArray jcontacts=response.optJSONArray(Constants.JSON_KEYS.CONTACTS);
                        int len=jcontacts.length();
                        JSONObject jcontact;
                        Contact contact;
                        for(int i=0;i<len;i++){
                            jcontact=jcontacts.getJSONObject(i);
                            contact=new Contact();
                            contact.setId(jcontact.optLong(Constants.JSON_KEYS.Contacts.ID));
                            contact.setName(jcontact.optString(Constants.JSON_KEYS.Contacts.NAME));
                            contact.setNumber(jcontact.optString(Constants.JSON_KEYS.Contacts.NUMBER));
                            contacts.add(contact);
                        }
                        contactAdapter.addAll(contacts);
                    } else {
                        //login failed
                        Log.d(TAG,"not authenticated");
                    }
                } catch (Exception ex) {
                    Log.d(TAG, "error:" + ex.getLocalizedMessage());
                }
            }
        };
        contactErrorlistErrorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "error:" + error.getLocalizedMessage());
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        contactList = (RecyclerView) findViewById(R.id.contactList);
        contactList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        contactAdapter = new ContactAdapter(null);
        contactList.setAdapter(contactAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * menu option for loggin out
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                logout();
                return true;
            case R.id.action_refresh:
                getContacts();
                return true;
            default:
                return false;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        getContacts();
    }

    /**
     * clear the data stored(remember me state) and start the login activity
     */
    private void logout() {
        getSharedPreferences(getString(R.string.pref_filename), Context.MODE_PRIVATE).edit().clear().apply();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }

    private RequestQueue reqQueue;

    /**
     * Override onBackPressed to show exit message
     */
    @Override
    public void onBackPressed() {
        if (!back_pressed) {
            back_pressed = true;
            Toast.makeText(getApplicationContext(), "press again to exit.", Toast.LENGTH_SHORT).show();
        } else
            finish();
    }

    public void getContacts() {
        JSONObject responseBody=new JSONObject();
        try {
            responseBody.put(Constants.JSON_KEYS.USERNAME, Util.getUsername(getApplicationContext()));
            responseBody.put(Constants.JSON_KEYS.PASSWORD, Util.getPassword(getApplicationContext()));
        }catch(JSONException jex){
            Log.d(TAG,""+jex.getLocalizedMessage());
        }
        Request request =new JsonObjectRequest(Request.Method.POST, Constants.URL.GET_CONTACTS,responseBody,contactResponseListener,contactErrorlistErrorListener);
        getReqQueue().add(request);
    }

    private RequestQueue getReqQueue() {
        if (reqQueue == null)
            reqQueue = Volley.newRequestQueue(getApplicationContext());
        return reqQueue;
    }
}
