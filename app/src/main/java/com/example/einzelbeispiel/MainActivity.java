package com.example.einzelbeispiel;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class MainActivity extends AppCompatActivity  {

    private EditText editText;
    private TextView textView;
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Network Test");

        editText = (EditText)findViewById(R.id.editTextNumber);
        textView = (TextView)findViewById(R.id.textView3);
        text = "";
    }

    public void send (View view) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                Socket socket = null;

                try {

                    socket = new Socket("se2-isys.aau.at", 53212);

                    PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
                    printWriter.println(editText.getText().toString());
                    printWriter.flush();

                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    text = bufferedReader.readLine();

                } catch (Exception e) {

                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }

                try {

                    socket.close();

                } catch (Exception e) {

                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        thread.start();

        try {

            thread.join();

        } catch (Exception e) {

            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                textView.setText(text);
            }
        });
    }

    public void calculate(View view){

        if(!(editText.getText().toString()).equals("11741375"))
            Toast.makeText(MainActivity.this, "Das ist falsche Matrikelnummer!", Toast.LENGTH_LONG).show();
        else{

            String num = editText.getText().toString();

            char[] array = new char[num.length()];

            for(int i = 0; i < num.length(); i++)
                array[i] = num.charAt(i);

            for(int i = 1; i < array.length; i += 2){

                if(array[i] == '1')
                    array[i] = 'a';
                else if(array[i] == '2')
                    array[i] = 'b';
                else if(array[i] == '3')
                    array[i] = 'c';
                else if(array[i] == '4')
                    array[i] = 'd';
                else if(array[i] == '5')
                    array[i] = 'e';
                else if(array[i] == '6')
                    array[i] = 'f';
                else if(array[i] == '7')
                    array[i] = 'g';
                else if(array[i] == '8')
                    array[i] = 'h';
                else if(array[i] == '9')
                    array[i] = 'i';
            }

            String asciiNum = new String(array);

            textView.setText(asciiNum);
        }
    }
}