package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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


    EditText Age_of_Subject,Time_class,spent_study,spent_fitness,spent_sleep,spent_social,spent_tv,meals_number;
    Button predict;
    TextView result;
    Spinner Rating_class,Medium_class,platform_media,weight,Stress,miss,Time_utilized,find_yourself;
    String url = "https://artificial-psychiatrist.herokuapp.com/predict";
    //String url = "http://10.13.222.161:8000/predict";
    //String url = " http://127.0.0.1:5000/predict";

    String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar

        Toolbar toolbar= findViewById(R.id.tb);
       //setSupportActionBar(toolbar);

       //Change the status bar background color
       Window w = MainActivity.this.getWindow();
//       w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//      w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
       w.setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.white));


      //To change the status bar Text and icon color
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);



        Age_of_Subject = findViewById(R.id.edit);
        Time_class = findViewById(R.id.edit1);
        Rating_class =(Spinner) findViewById(R.id.edit2);
        ArrayAdapter<String>myadapter=new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.Rating_class));
        Rating_class.setAdapter(myadapter);

        Medium_class = (Spinner)findViewById(R.id.edit3);
        ArrayAdapter<String>myadapter1=new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.Medium_class));
        Medium_class.setAdapter(myadapter1);

        platform_media = findViewById(R.id.edit8);
        ArrayAdapter<String>myadapter2=new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.platform_media));
        platform_media.setAdapter(myadapter2);

        weight = findViewById(R.id.edit11);
        ArrayAdapter<String>myadapter3=new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.weight));
        weight.setAdapter(myadapter3);

        Stress = findViewById(R.id.edit12);
        ArrayAdapter<String>myadapter4=new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.Stress));
        Stress.setAdapter(myadapter4);

        miss = findViewById(R.id.edit15);
        ArrayAdapter<String>myadapter5=new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.miss));
        miss.setAdapter(myadapter5);

        Time_utilized = findViewById(R.id.edit13);
        ArrayAdapter<String>myadapter6=new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.Time_utilized));
        Time_utilized.setAdapter(myadapter6);

        find_yourself = findViewById(R.id.edit14);
        ArrayAdapter<String>myadapter7=new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.find_yourself));
        find_yourself.setAdapter(myadapter7);

        spent_study = findViewById(R.id.edit4);
        spent_fitness = findViewById(R.id.edit5);
        spent_sleep = findViewById(R.id.edit6);
        spent_social = findViewById(R.id.edit7);
        spent_tv = findViewById(R.id.edit9);
        meals_number = findViewById(R.id.edit10);


        predict = findViewById(R.id.button);
        //result = findViewById(R.id.result);

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
//                                        Intent intent=new Intent(getApplicationContext(),MainActivity2.class);
//                                        startActivity(intent);
                                          openDialog("Artificial Psychiatrist says that you have mental sickness.",true);

                                    }else{
//                                        result.setTextColor(Color.GREEN);
//                                        Typeface boldTypeface=Typeface.defaultFromStyle(Typeface.BOLD);
//                                        result.setTypeface(boldTypeface);
//                                        result.setText("NO");
                                          openDialog("Artificial Psychiatrist says that you have no mental sickness.",false);
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
                params.put("Rating_class",Rating_class.getSelectedItem().toString());
                params.put("Medium_class",Medium_class.getSelectedItem().toString());
                params.put("spent_study",spent_study.getText().toString());
                params.put("spent_fitness",spent_fitness.getText().toString());
                params.put("spent_sleep",spent_sleep.getText().toString());
                params.put("spent_social",spent_social.getText().toString());
                params.put("platform_media",platform_media.getSelectedItem().toString());
                params.put("spent_tv",spent_tv.getText().toString());
                params.put("no_meals",meals_number.getText().toString());
                params.put("weight",weight.getSelectedItem().toString());
                params.put("Stress",Stress.getSelectedItem().toString());
                params.put("Time_utilized",Time_utilized.getSelectedItem().toString());
                params.put("find_yourself",find_yourself.getSelectedItem().toString());
                params.put("miss",miss.getSelectedItem().toString());
                return params;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(stringRequest);
    }
    public void openDialog(String string,boolean ck){
        dialog Dialog=new dialog(string,ck);
        Dialog.show(getSupportFragmentManager(),"dialog");
    }
}
