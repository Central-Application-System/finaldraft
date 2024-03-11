package com.example.myapplication;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionClass {
    public static Connection con;
    public static Connection getConnection() {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            String ip = "192.168.8.156";
            String ConnURL = "jdbc:jtds:sqlserver://" + ip + ";instance=MSSQLSERVER01;databasename=clapps;";
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            return DriverManager.getConnection(ConnURL);
        } catch (Exception e) {
            Log.e("ASK", "EXCEPTION" + e.getMessage());
            return null;
        }
    }
}