package io.github.zkhan93.mailui.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by 1036870 on 1/20/2016.
 */
public class Mail implements Parcelable {
    private int id;
    private String subject, body;
    private User sender, receiver;
    private long time;
    private List<User> cc;
    private List<User> bcc;
    private int priority;

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public List<User> getCc() {
        return cc;
    }

    public void setCc(List<User> cc) {
        this.cc = cc;
    }

    public List<User> getBcc() {
        return bcc;
    }

    public void setBcc(List<User> bcc) {
        this.bcc = bcc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Mail(Parcel in) {
        id = in.readInt();
        subject = in.readString();
        body = in.readString();
        sender = in.readParcelable(User.class.getClassLoader());
        receiver = in.readParcelable(User.class.getClassLoader());
        time = in.readLong();
        in.readTypedList(cc, User.CREATOR);
        in.readTypedList(bcc, User.CREATOR);
        priority = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(subject);
        parcel.writeString(body);
        parcel.writeParcelable(sender, i);
        parcel.writeParcelable(receiver, i);
        parcel.writeLong(time);
        parcel.writeTypedList(cc);
        parcel.writeTypedList(bcc);
        parcel.writeInt(priority);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Mail> CREATOR
            = new Parcelable.Creator<Mail>() {
        public Mail createFromParcel(Parcel in) {
            return new Mail(in);
        }

        public Mail[] newArray(int size) {
            return new Mail[size];
        }
    };

}
