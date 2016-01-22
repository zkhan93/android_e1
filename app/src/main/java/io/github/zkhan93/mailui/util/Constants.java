package io.github.zkhan93.mailui.util;

/**
 * Created by Zeeshan Khan on 1/8/2016.
 */
public interface Constants {
    interface URL {
        int PORT = 8080;
        String HOST = "192.168.2.8";
        String BASE = "http://" + HOST + ":" + PORT + "/MailUI/";
        String LOGIN = BASE + "login";
        String GET_MAILS = BASE + "getMails";

    }

    interface PREF_KEYS {
        String USERNAME = "username";
        String PASSWORD = "password";
        String EMAIL = "email";
    }

    interface JSON_KEYS {
        String USERNAME = "username";
        String PASSWORD = "password";
        String AUTHENTICATION = "authentication";
        String CONTACTS = "contacts";
        String MAILS = "mails";

        interface Contacts {
            String ID = "id";
            String NAME = "name";
            String NUMBER = "number";
        }

        interface MAIL {
            String SENDER = "sender";
            String RECEIVER = "receiver";
            String ID = "id";
            String SUBJECT = "subject";
            String BODY = "body";

            String PRIORITY = "priority";
        }

        interface User {
            String ID = "id";
            String EMAIL = "email";
            String USERNAME = "username";
        }
        interface Priority{
            int HIGH=1;
            int LOW=2;
        }
    }
}
