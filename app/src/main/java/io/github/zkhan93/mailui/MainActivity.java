package io.github.zkhan93.mailui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import io.github.zkhan93.mailui.adapter.MailAdapter;
import io.github.zkhan93.mailui.model.Mail;
import io.github.zkhan93.mailui.model.MailItemClickCallbackListener;
import io.github.zkhan93.mailui.util.Constants;
import io.github.zkhan93.mailui.util.Util;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mailList;
    private MailAdapter mailAdapter;
    private RequestQueue reqQueue;
    private static String TAG = "MainActivity";
    private MailItemClickCallbackListener callbackListener=new MailItemClickCallbackListener() {
        @Override
        public void onClick(Mail mail) {
            Intent intent=new Intent(getApplicationContext(),MailActivity.class);
            intent.putExtra("mail",mail);
            startActivity(intent);
        }

        @Override
        public void onClick(View view) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mailList = (RecyclerView) findViewById(R.id.mail_list);
        mailAdapter = new MailAdapter();
        mailList.setAdapter(mailAdapter);
        getMails();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getMails() {
        JSONObject params = Util.getCredentials(getApplicationContext());
        JsonRequest request = new JsonObjectRequest(Request.Method.POST, Constants.URL.GET_MAILS, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jMails = response.getJSONArray(Constants.JSON_KEYS.MAILS);
                    if (jMails != null) {
                        List<Mail> mails = Util.getMails(jMails, Util.getSelf(getApplicationContext()));
                        mailAdapter.addAll(mails);
                    }
                } catch (Exception ex) {
                    Util.log(TAG, ex.getLocalizedMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        getReqQueue().add(request);
    }

    private RequestQueue getReqQueue() {
        if (reqQueue == null) {
            reqQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return reqQueue;
    }
}
