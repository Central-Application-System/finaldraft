package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void CheckConnection(View view) {
        new DatabaseQueryTask().execute();
    }

    private class DatabaseQueryTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            try {
                if (ConnectionClass.con == null) {
                    new ConnectionClass().getConnection();
                }
                if (ConnectionClass.con != null) {
                    Statement stmt = ConnectionClass.con.createStatement();
                    String sql = "SELECT * FROM subjects";
                    ResultSet rs = stmt.executeQuery(sql);

                    StringBuilder result = new StringBuilder();
                    while (rs.next()) {
                        result.append(rs.getString("subjects")).append("\n");
                    }

                    rs.close();
                    stmt.close();
                    return result.toString();
                } else {
                    return "Query execution failed";
                }
            } catch (Exception e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            Log.e("ASK", "--------------------------");
            if (result != null && !result.isEmpty()) {
                Log.e("ASK", result);
                Toast.makeText(getApplicationContext(), "Query executed successfully", Toast.LENGTH_SHORT).show();
            } else {
                Log.e("ASK", "Query execution failed");
                Toast.makeText(getApplicationContext(), "Query execution failed", Toast.LENGTH_SHORT).show();
            }
            Log.e("ASK", "---------");
        }
    }
}