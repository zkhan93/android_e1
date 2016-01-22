package io.github.zkhan93.mailui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.github.zkhan93.mailui.R;
import io.github.zkhan93.mailui.model.Mail;
import io.github.zkhan93.mailui.util.Util;

/**
 * Created by 1036870 on 1/20/2016.
 */
public class MailAdapter extends RecyclerView.Adapter<MailAdapter.ViewHolder> {

    private List<Mail> mails;

    public MailAdapter(List<Mail> mails) {
        this.mails = mails;
    }

    public void addAll(List<Mail> mails) {
        if (this.mails == null)
            this.mails = new ArrayList<Mail>();
        this.mails.clear();
        this.mails.addAll(mails);
        this.notifyDataSetChanged();

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setMail(mails.get(position));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mail_item, parent));
    }

    @Override
    public int getItemCount() {
        return mails == null ? 0 : mails.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView subject, sender, time;
        private Mail mail;

        ViewHolder(View parent) {
            super(parent);
            sender = (TextView) parent.findViewById(R.id.sender);
            subject = (TextView) parent.findViewById(R.id.subject);
            time = (TextView) parent.findViewById(R.id.time);
            parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getAdapterPosition();
                }
            });
        }

        public void setMail(Mail mail) {
            this.mail = mail;
            if (subject != null)
                subject.setText(mail.getSubject());
            if (sender != null && mail.getSender() != null)
                sender.setText(mail.getSender().getEmail());
            if (time != null)
                time.setText(Util.getTimeStr(mail.getTime()));
        }
    }
}
