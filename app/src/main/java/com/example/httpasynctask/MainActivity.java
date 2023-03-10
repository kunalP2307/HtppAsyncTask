package com.example.httpasynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class MainActivity extends AppCompatActivity implements AsyncCallBack{
    Button buttonSendReq;
    TextView textViewResult;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindComponents();
    }

    private void bindComponents() {
        this.buttonSendReq = findViewById(R.id.button_get_result);
        this.textViewResult = findViewById(R.id.text_view_result);
        this.progressBar = findViewById(R.id.progressBar);

        buttonSendReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewResult.setText("");
                new GetAsyncTask().setInstance(MainActivity.this, progressBar)
                        .execute();
            }
        });
    }

    @Override
    public void setResult(String result) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(result);
        String prettyJsonString = gson.toJson(je);
        textViewResult.setText(prettyJsonString);
    }
}