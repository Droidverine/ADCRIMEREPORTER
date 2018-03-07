package com.droidverine.adcrimereporter.Acitivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.droidverine.adcrimereporter.Adapters.MissingAdapter;
import com.droidverine.adcrimereporter.Models.MissingModel;
import com.droidverine.adcrimereporter.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MissingActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MissingAdapter missingAdapter;
    List<MissingModel> missingModelList;
    MissingModel missingModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missing);
        recyclerView=(RecyclerView)findViewById(R.id.recyclermissing);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        missingAdapter=new MissingAdapter();

        getMissing();


    }
    public void getMissing() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.POST,
                "http://myappapi.esy.es/AndroidCrimeReporter/missingretrive.php",
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        String name,url,address,otherinfo,contact,team,venue,foodcode;
                        JSONObject json = null;
                        missingModelList=new ArrayList<>();
                        try {
                            Log.d("getting Missing", "done");


                            for(int i = 0; i<=response.length(); i++) {
                                missingModel=new MissingModel();

                                json = response.getJSONObject(i);
                                name = json.get("name").toString();
                                url=json.get("url").toString();
                                address=json.getString("address");
                                contact=json.getString("contact");
                                otherinfo=json.getString("otherinfo");
                                missingModel.setName(name);
                                missingModel.setAddress(address);
                                missingModel.setConatct(contact);
                                missingModel.setOtherinfo(otherinfo);
                                missingModel.setUrl(url);
                                missingModelList.add(missingModel);
                                Log.d("gheto",name+" url ="+url);
                                missingAdapter=new MissingAdapter(missingModelList,getApplicationContext());
                                missingAdapter.notifyDataSetChanged();
                                recyclerView.setAdapter(missingAdapter);

                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

// Do something when error occurred

                    }



                }

        );  // Add JsonArrayRequest to the RequestQueue
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(3000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonArrayRequest);




    }
}
