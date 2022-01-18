package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    EditText Age_of_Subject,Time_class,Rating_class,Medium_class,spent_study,spent_fitness,spent_sleep,spent_social,platform_media,spent_tv,no_meals,weight,Stress,Time_utilized,find_yourself,miss;
    Button predict;
    TextView result;
//    String url = "https://refatapp.herokuapp.com/predict";
    String url = " http://192.168.42.89:8000/predict";

    String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Age_of_Subject = findViewById(R.id.edit);
        Time_class = findViewById(R.id.edit1);
        Rating_class = findViewById(R.id.edit2);
        Medium_class = findViewById(R.id.edit3);
        spent_study = findViewById(R.id.edit4);
        spent_fitness = findViewById(R.id.edit5);
        spent_sleep = findViewById(R.id.edit6);
        spent_social = findViewById(R.id.edit7);
        platform_media = findViewById(R.id.edit8);
        spent_tv = findViewById(R.id.edit9);
        no_meals = findViewById(R.id.edit10);
        weight = findViewById(R.id.edit11);
        Stress = findViewById(R.id.edit12);
        Time_utilized = findViewById(R.id.edit13);
        find_yourself = findViewById(R.id.edit14);
        miss = findViewById(R.id.edit15);
        predict = findViewById(R.id.button1);
        result = findViewById(R.id.result);

        predict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hit_api();
            }
        });
    }
    void hit_api (){

        // hit the API -> Volley
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                        Log.d("myTag", "enter int response");
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String data = jsonObject.getString("Health_status");
                                    if(data.equals("1")){
                                        result.setText("yes");
//                                        Toast.makeText(MainActivity.this, "refatrtwrtytryertyetyety", Toast.LENGTH_LONG).show();
//                                        Log.d("myTag", "correct response");
                                    }else{
                                        result.setText("no");
//                                        Toast.makeText(MainActivity.this, "refrtyertyetrytryrtyrtey", Toast.LENGTH_LONG).show();
//                                        Log.d("myTag", "wrong response");
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Aff"+ error, Toast.LENGTH_LONG).show();
                        //Toast.makeText(MainActivity.this, "refat", Toast.LENGTH_LONG).show();

                    }
                }){

            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String,String>();
                params.put("Age_of_Subject",Age_of_Subject.getText().toString());
                params.put("Time_class",Time_class.getText().toString());
                params.put("Rating_class",Rating_class.getText().toString());
                params.put("Medium_class",Medium_class.getText().toString());
                params.put("spent_study",spent_study.getText().toString());
                params.put("spent_fitness",spent_fitness.getText().toString());
                params.put("spent_sleep",spent_sleep.getText().toString());
                params.put("spent_social",spent_social.getText().toString());
                params.put("platform_media",platform_media.getText().toString());
                params.put("spent_tv",spent_tv.getText().toString());
                params.put("no_meals",no_meals.getText().toString());
                params.put("weight",weight.getText().toString());
                params.put("Stress",Stress.getText().toString());
                params.put("Time_utilized",Time_utilized.getText().toString());
                params.put("find_yourself",find_yourself.getText().toString());
                params.put("miss",miss.getText().toString());
                return params;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(stringRequest);
    }
}
