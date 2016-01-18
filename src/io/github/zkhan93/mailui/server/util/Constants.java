package io.github.zkhan93.mailui.server.util;

public interface Constants {
	interface JSON_KEYS{
        String USERNAME="username";
        String ERROR="error";
        String PASSWORD="password";
        String AUTHENTICATION="authentication";
        String CONTACTS="contacts";
        String MSG="msg";
        interface Contacts{
            String ID="id";
            String NAME="name";
            String NUMBER="number";
        }
        interface PARAMS_KEYS{
        	String USERNAME="username";
        	String PASSWORD="password";
        }
    }
}
