package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
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

import com.android.volley.AuthFailureError;
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

public class MainActivity2 extends AppCompatActivity {


    EditText Income;
    Button predict;
    TextView result;
    Spinner EducationStatus,Age,Gender,PartTimeEmployment,DeviceWithoutPhone,PreviousMentalTreatment,Disabled,RegularAccessInternet,
    LiveWithFamily,StudyGap,ReadWithoutCurriculum,LongConcentration,Anxiety,Depression,ObsessiveThinking,MoodSwings,PanicAttacks,
            CompulsiveBehavior,Tiredness;
    String url = "https://artificial-psychiatrist.herokuapp.com/treatment";
    //String url = "http://10.13.222.161:8000/treatment";

    String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Intent intent = getIntent();
        setContentView(R.layout.activity_main2);

        //Toolbar


        Toolbar toolbar= findViewById(R.id.tb);
        setSupportActionBar(toolbar);


        //Change the status bar background color
        Window w = MainActivity2.this.getWindow();
        w.setStatusBarColor(ContextCompat.getColor(MainActivity2.this, R.color.white));


        //To change the status bar Text and icon color
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);


        EducationStatus = findViewById(R.id.edt1);
        ArrayAdapter<String> myadapter=new ArrayAdapter<String>(MainActivity2.this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.EducationStatus));
        EducationStatus.setAdapter(myadapter);

        Age = findViewById(R.id.edt18);
        myadapter=new ArrayAdapter<String>(MainActivity2.this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.Age));
        Age.setAdapter(myadapter);

        Gender = findViewById(R.id.edt19);
        myadapter=new ArrayAdapter<String>(MainActivity2.this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.Gender));
        Gender.setAdapter(myadapter);

        PartTimeEmployment = findViewById(R.id.edt);
        myadapter=new ArrayAdapter<String>(MainActivity2.this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.common));
        PartTimeEmployment.setAdapter(myadapter);

//        MetalHealthStatus=findViewById(R.id.edt20);
//        myadapter=new ArrayAdapter<String>(MainActivity2.this,
//                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.common));
//        MetalHealthStatus.setAdapter(myadapter);

        DeviceWithoutPhone = findViewById(R.id.edt2);
        myadapter=new ArrayAdapter<String>(MainActivity2.this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.common));
        DeviceWithoutPhone.setAdapter(myadapter);

        PreviousMentalTreatment = findViewById(R.id.edt3);
        myadapter=new ArrayAdapter<String>(MainActivity2.this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.common));
        PreviousMentalTreatment.setAdapter(myadapter);

        Disabled = findViewById(R.id.edt4);
        myadapter=new ArrayAdapter<String>(MainActivity2.this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.common));
        Disabled.setAdapter(myadapter);

        RegularAccessInternet = findViewById(R.id.edt5);
        myadapter=new ArrayAdapter<String>(MainActivity2.this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.common));
        RegularAccessInternet.setAdapter(myadapter);

        LiveWithFamily = findViewById(R.id.edt6);
        myadapter=new ArrayAdapter<String>(MainActivity2.this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.common));
        LiveWithFamily.setAdapter(myadapter);

        StudyGap = findViewById(R.id.edt7);
        myadapter=new ArrayAdapter<String>(MainActivity2.this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.common));
        StudyGap.setAdapter(myadapter);

        ReadWithoutCurriculum = findViewById(R.id.edt9);
        myadapter=new ArrayAdapter<String>(MainActivity2.this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.common));
        ReadWithoutCurriculum.setAdapter(myadapter);

        LongConcentration = findViewById(R.id.edt10);
        myadapter=new ArrayAdapter<String>(MainActivity2.this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.common));
        LongConcentration.setAdapter(myadapter);

        Anxiety = findViewById(R.id.edt11);
        myadapter=new ArrayAdapter<String>(MainActivity2.this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.common));
        Anxiety.setAdapter(myadapter);

        Depression = findViewById(R.id.edt12);
        myadapter=new ArrayAdapter<String>(MainActivity2.this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.common));
        Depression.setAdapter(myadapter);

        ObsessiveThinking = findViewById(R.id.edt13);
        myadapter=new ArrayAdapter<String>(MainActivity2.this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.common));
        ObsessiveThinking.setAdapter(myadapter);

        MoodSwings = findViewById(R.id.edt14);
        myadapter=new ArrayAdapter<String>(MainActivity2.this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.common));
        MoodSwings.setAdapter(myadapter);

        PanicAttacks = findViewById(R.id.edt15);
        myadapter=new ArrayAdapter<String>(MainActivity2.this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.common));
        PanicAttacks.setAdapter(myadapter);

        CompulsiveBehavior = findViewById(R.id.edt16);
        myadapter=new ArrayAdapter<String>(MainActivity2.this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.common));
        CompulsiveBehavior.setAdapter(myadapter);

        Tiredness = findViewById(R.id.edt17);
        myadapter=new ArrayAdapter<String>(MainActivity2.this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.common));
        Tiredness.setAdapter(myadapter);

        Income = findViewById(R.id.edt8);
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
//                        Toast.makeText(MainActivity2.this, response, Toast.LENGTH_SHORT).show();
                        Log.d("myTag", "enter int response");
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String data = jsonObject.getString("treatment");
                            if(data.equals("1")){
                                result.setTextColor(Color.RED);
                                result.setText("YES");
//                                        Toast.makeText(MainActivity2.this, "refatrtwrtytryertyetyety", Toast.LENGTH_LONG).show();
//                                        Log.d("myTag", "correct response");
                            }else{
                                result.setTextColor(Color.GREEN);
                                result.setText("NO");
//                                        Toast.makeText(MainActivity2.this, "refrtyertyetrytryrtyrtey", Toast.LENGTH_LONG).show();
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
                        Toast.makeText(MainActivity2.this, "Aff"+ error, Toast.LENGTH_LONG).show();
                        //Toast.makeText(MainActivity2.this, "refat", Toast.LENGTH_LONG).show();

                    }
                }){

            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String,String>();

                params.put("PartTimeEmployment",PartTimeEmployment.getSelectedItem().toString());
//                params.put("MetalHealthStatus",MetalHealthStatus.getSelectedItem().toString());
                params.put("EducationStatus",EducationStatus.getSelectedItem().toString());
                params.put("DeviceWithoutPhone",DeviceWithoutPhone.getSelectedItem().toString());
                params.put("PreviousMentalTreatment",PreviousMentalTreatment.getSelectedItem().toString());
                params.put("Disabled",Disabled.getSelectedItem().toString());
                params.put("RegularAccessInternet",RegularAccessInternet.getSelectedItem().toString());
                params.put("LiveWithFamily",LiveWithFamily.getSelectedItem().toString());
                params.put("StudyGap",StudyGap.getSelectedItem().toString());
                params.put("Income",Income.getText().toString());
                params.put("ReadWithoutCurriculum",ReadWithoutCurriculum.getSelectedItem().toString());
                params.put("LongConcentration",LongConcentration.getSelectedItem().toString());
                params.put("Anxiety",Anxiety.getSelectedItem().toString());
                params.put("Depression",Depression.getSelectedItem().toString());
                params.put("ObsessiveThinking",ObsessiveThinking.getSelectedItem().toString());
                params.put("MoodSwings",MoodSwings.getSelectedItem().toString());
                params.put("PanicAttacks",PanicAttacks.getSelectedItem().toString());
                params.put("CompulsiveBehavior",CompulsiveBehavior.getSelectedItem().toString());
                params.put("Tiredness",Tiredness.getSelectedItem().toString());
                params.put("Age",Age.getSelectedItem().toString());
                params.put("Gender",Gender.getSelectedItem().toString());

                return params;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(MainActivity2.this);
        queue.add(stringRequest);
    }
}

