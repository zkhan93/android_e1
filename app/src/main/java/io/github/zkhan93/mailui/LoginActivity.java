package io.github.zkhan93.mailui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import io.github.zkhan93.loginpage.util.Constants;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameView, passwordView;
    private CheckBox rememberMeView;
    private TextView errorView;
    private RequestQueue reqQueue;
    private Response.Listener<JSONObject> loginResponseListener;
    private Response.ErrorListener loginErrorlistErrorListener;
    public static String TAG = "LoginActivity";

    public LoginActivity() {
        loginResponseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "response:" + response);
                try {
                    if (response.optBoolean("Authentication")) {
                        //login success
                        saveRememberMeState();
                        startMainActivity();
                    } else {
                        //login failed
                        setError(response.getString("error"));
                    }
                } catch (Exception ex) {
                    setError("Invalid response from server");
                    Log.d(TAG, "error:" + ex.getLocalizedMessage());
                }
            }
        };
        loginErrorlistErrorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "error:" + error.getLocalizedMessage());
                setError("Connection error, Try again!!");
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        usernameView = (EditText) findViewById(R.id.username);
        passwordView = (EditText) findViewById(R.id.password);
        errorView = (TextView) findViewById(R.id.error_view);
        rememberMeView = (CheckBox) findViewById(R.id.remember_me);
    }

    /**
     * overriding onStart() method to check for rememberMe state if the user is already login then take him to the main activity
     */
    @Override
    protected void onStart() {
        super.onStart();
        if (getRememberState()) {
            startMainActivity();
        }
    }


    /**
     * called when login button is clicked
     * send username and password to server
     *
     * @param view
     */
    public void login(View view) {
        setError(null);
        String username = getUserName();
        String password = getPassword();
        try {
            JSONObject params = new JSONObject();
            params.put("username", username);
            params.put("password", password);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Constants.URL.LOGIN, params, loginResponseListener, loginErrorlistErrorListener);
            getReqQueue().add(request);
        } catch (JSONException ex) {
            Log.d(TAG, "" + ex.getLocalizedMessage());
        }
    }

    /**
     * save remember me state in SharedPreferences for next time
     */
    private void saveRememberMeState() {
        if (rememberMeView != null) {
            SharedPreferences spf = getSharedPreferences(getString(R.string.pref_filename), Context.MODE_PRIVATE);
            spf.edit().putBoolean(getString(R.string.pref_key_remember_me), rememberMeView.isChecked()).apply();
        }
    }

    /**
     * get is the user has checked remember me while logging in the last time
     *
     * @return
     */
    private boolean getRememberState() {
        SharedPreferences spf = getSharedPreferences(getString(R.string.pref_filename), Context.MODE_PRIVATE);
        return spf.getBoolean(getString(R.string.pref_key_remember_me), false);
    }

    /**
     * stats the main activity
     */
    private void startMainActivity() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    /**
     * extract username from the EditText View
     *
     * @return
     */
    private String getUserName() {
        if (usernameView != null) {
            return usernameView.getText().toString().trim();
        }
        return null;
    }

    /**
     * extract password from EditText View
     *
     * @return
     */
    private String getPassword() {
        if (passwordView != null)
            return passwordView.getText().toString();
        return null;
    }


    private RequestQueue getReqQueue() {
        if (reqQueue == null)
            reqQueue = Volley.newRequestQueue(getApplicationContext());
        return reqQueue;
    }

    /**
     * set error and show the view is error message is not null else hide the error view
     *
     * @param error
     */
    private void setError(String error) {
        if (errorView != null) {
            if (error == null)
                errorView.setVisibility(View.GONE);
            else {
                errorView.setText(error);
                errorView.setVisibility(View.VISIBLE);
            }
        }
    }
}
