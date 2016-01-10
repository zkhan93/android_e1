package io.github.zkhan93.loginpage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private boolean back_pressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * menu option for loggin out
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                logout();
                return true;
            default:
                return false;
        }
    }

    /**
     * clear the data stored(remember me state) and start the login activity
     */
    private void logout() {
        getSharedPreferences(getString(R.string.pref_filename), Context.MODE_PRIVATE).edit().clear().apply();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }


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
}
