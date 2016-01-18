package io.github.zkhan93.mailui.server.dao;

public interface DbContants {
	String DB_USERNAME="root";
	String DB_PASSWORD="1234";
	String DB_HOST="localhost";
	int DB_PORT=3306;
	String DB_URL="jdbc:mysql://"+DB_HOST+":"+DB_PORT+"/";
	String DB_NAME="cba3_mail_ui_db";
	String DB_DRIVER="com.mysql.jdbc.Driver";
	
	/**
	 * #run this in mysql database
	 * 
	 * create database bca1_login_page_db;
	 * use bca1_login_page_db;
	 * create table login(username varchar(50),password varchar(50));
	 * insert into login values('admin','test_pass');
	 * 
	 */
}
