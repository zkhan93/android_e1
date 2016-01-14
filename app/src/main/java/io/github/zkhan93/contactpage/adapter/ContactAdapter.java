package io.github.zkhan93.contactpage.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import io.github.zkhan93.contactpage.R;
import io.github.zkhan93.contactpage.model.Contact;

/**
 * Created by Zeeshan Khan on 1/9/2016.
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    List<Contact> contacts;

    public ContactAdapter(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public void onBindViewHolder(ContactAdapter.ViewHolder holder, int position) {
        Contact contact = contacts.get(position);
        holder.setName(contact.getName());
        holder.setNumber(contact.getNumber());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item,parent,false));
    }

    @Override
    public int getItemCount() {
        if (contacts == null)
            return 0;
        else
            return contacts.size();
    }

    public void addAll(List<Contact> contacts) {
        if (this.contacts != null) {
            this.contacts.clear();
            this.contacts.addAll(contacts);
        } else
            this.contacts = contacts;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name, number;

        public ViewHolder(View parent) {
            super(parent);
            name = (TextView) parent.findViewById(R.id.contactName);
            number = (TextView) parent.findViewById(R.id.contactNumber);
        }

        public void setName(String name) {
            if (this.name != null)
                this.name.setText(name);
        }

        public void setNumber(String number) {
            if (this.number != null)
                this.number.setText(number);
        }
    }

}
