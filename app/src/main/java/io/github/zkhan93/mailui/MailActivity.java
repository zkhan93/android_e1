package io.github.zkhan93.mailui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import io.github.zkhan93.mailui.model.Mail;
import io.github.zkhan93.mailui.model.User;

public class MailActivity extends AppCompatActivity {
    TextView sender, subject, cc, priority, body;
    Mail mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sender = (TextView) findViewById(R.id.sender);
        subject = (TextView) findViewById(R.id.subject);
        cc = (TextView) findViewById(R.id.cc);
        priority = (TextView) findViewById(R.id.priority);
        body = (TextView) findViewById(R.id.body);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        if (intent.getParcelableExtra("mail") != null) {
            mail = (Mail) intent.getParcelableExtra("mail");
            syncView();
        }
    }

    private void syncView() {
        if (mail != null) {
            subject.setText(mail.getSubject());
            sender.setText(mail.getSender().getEmail());
            body.setText(mail.getBody());
            for(User u:mail.getCc()) {
                cc.setText(cc.getText()+", "+u.getEmail());
            }
            priority.setText(Util.getPriority(mail.getPriority()));
        }
    }
}
