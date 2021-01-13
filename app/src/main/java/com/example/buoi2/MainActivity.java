package com.example.buoi2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    public static final String duongdan="http://172.20.10.14/0/student.php";
    TextView textView;
    Button button;
    EditText name, score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        name = findViewById(R.id.Name);
        score = findViewById(R.id.score);
    }

    public void btn(View view) {
        String Name, Score;
        Name = name.getText().toString();
        Score = score.getText().toString();
     new B1Lab2Async(Name, Score).execute();
    }

    public class B1Lab2Async extends AsyncTask<Void, Void, Void>{
        String path = MainActivity.duongdan;
        private String result="";
        private String name;
        private String score;
        public B1Lab2Async(String name, String score){
            this.path = path;
            this.name = name;
            this.score = score;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            path +="?name="+this.name+"&score="+this.score;
//            path +="?name=nghiep&score=123";
            try {
                URL url = new URL(path);
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(url.openConnection().getInputStream()));
                String line="";
                StringBuilder sb = new StringBuilder();
                while ((line =br.readLine())!=null){
                    sb.append(line);
                }
                result = sb.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            textView.setText(result);
        }
    }
}