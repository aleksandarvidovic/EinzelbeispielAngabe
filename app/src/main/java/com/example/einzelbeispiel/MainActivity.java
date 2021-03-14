package com.example.einzelbeispiel;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Network Test");
    }

    public void send (View view){

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                EditText editText = (EditText)findViewById(R.id.editTextNumber);
                String et = editText.getText().toString();
                try {
                    Client client = new Client(et);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    Server server = new Server();
                    TextView textView = (TextView)findViewById(R.id.textView3);
                    textView.setText(server.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}