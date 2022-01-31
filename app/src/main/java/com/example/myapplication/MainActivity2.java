package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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


    EditText PartTimeEmployment,DeviceWithoutPhone,PreviousMentalTreatment,Disabled,RegularAccessInternet,LiveWithFamily,StudyGap,Income,ReadWithoutCurriculum,LongConcentration,
            Anxiety,Depression,ObsessiveThinking,MoodSwings,PanicAttacks,CompulsiveBehavior,Tiredness,MetalHealthStatus;
    Button predict;
    TextView result;
    Spinner EducationStatus,Age,Gender;
    //    String url = "https://refatapp.herokuapp.com/predict";
    String url = "http://10.13.222.161:8000/treatment";

    String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Intent intent = getIntent();
        setContentView(R.layout.activity_main2);

        EducationStatus = findViewById(R.id.edt1);
        ArrayAdapter<String> myadapter=new ArrayAdapter<String>(MainActivity2.this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.EducationStatus));
        EducationStatus.setAdapter(myadapter);
        Age = findViewById(R.id.edt18);
        ArrayAdapter<String>myadapter1=new ArrayAdapter<String>(MainActivity2.this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.Age));
        Age.setAdapter(myadapter1);

        Gender = findViewById(R.id.edt19);
        ArrayAdapter<String>myadapter2=new ArrayAdapter<String>(MainActivity2.this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.Gender));
        Gender.setAdapter(myadapter2);

        PartTimeEmployment = findViewById(R.id.edt);
        MetalHealthStatus=findViewById(R.id.edt20);

        DeviceWithoutPhone = findViewById(R.id.edt2);
        PreviousMentalTreatment = findViewById(R.id.edt3);
        Disabled = findViewById(R.id.edt4);
        RegularAccessInternet = findViewById(R.id.edt5);
        LiveWithFamily = findViewById(R.id.edt6);
        StudyGap = findViewById(R.id.edt7);
        Income = findViewById(R.id.edt8);
        ReadWithoutCurriculum = findViewById(R.id.edt9);
        LongConcentration = findViewById(R.id.edt10);
        Anxiety = findViewById(R.id.edt11);
        Depression = findViewById(R.id.edt12);
        ObsessiveThinking = findViewById(R.id.edt13);
        MoodSwings = findViewById(R.id.edt14);
        PanicAttacks = findViewById(R.id.edt15);
        CompulsiveBehavior = findViewById(R.id.edt16);
        Tiredness = findViewById(R.id.edt17);



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
                                result.setText("YES");
//                                        Toast.makeText(MainActivity2.this, "refatrtwrtytryertyetyety", Toast.LENGTH_LONG).show();
//                                        Log.d("myTag", "correct response");
                            }else{
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

                params.put("PartTimeEmployment",PartTimeEmployment.getText().toString());
                params.put("MetalHealthStatus",MetalHealthStatus.getText().toString());
                params.put("EducationStatus",EducationStatus.getSelectedItem().toString());
                params.put("DeviceWithoutPhone",DeviceWithoutPhone.getText().toString());
                params.put("PreviousMentalTreatment",PreviousMentalTreatment.getText().toString());
                params.put("Disabled",Disabled.getText().toString());
                params.put("RegularAccessInternet",RegularAccessInternet.getText().toString());
                params.put("LiveWithFamily",LiveWithFamily.getText().toString());
                params.put("StudyGap",StudyGap.getText().toString());
                params.put("Income",Income.getText().toString());
                params.put("ReadWithoutCurriculum",ReadWithoutCurriculum.getText().toString());
                params.put("LongConcentration",LongConcentration.getText().toString());
                params.put("Anxiety",Anxiety.getText().toString());
                params.put("Depression",Depression.getText().toString());
                params.put("ObsessiveThinking",ObsessiveThinking.getText().toString());
                params.put("MoodSwings",MoodSwings.getText().toString());
                params.put("PanicAttacks",PanicAttacks.getText().toString());
                params.put("CompulsiveBehavior",CompulsiveBehavior.getText().toString());
                params.put("Tiredness",Tiredness.getText().toString());
                params.put("Age",Age.getSelectedItem().toString());
                params.put("Gender",Gender.getSelectedItem().toString());

                return params;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(MainActivity2.this);
        queue.add(stringRequest);
    }
}

