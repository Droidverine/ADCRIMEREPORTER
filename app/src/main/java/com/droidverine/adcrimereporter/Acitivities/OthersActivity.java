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
import com.droidverine.adcrimereporter.Adapters.OtherAdapter;
import com.droidverine.adcrimereporter.Models.MissingModel;
import com.droidverine.adcrimereporter.Models.OthersModel;
import com.droidverine.adcrimereporter.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OthersActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    OtherAdapter otherAdapter;
    List<OthersModel> othersModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_others);
        recyclerView =(RecyclerView)findViewById(R.id.recyclerothers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getOthers();
    }
    public void getOthers() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.POST,
                "http://myappapi.esy.es/AndroidCrimeReporter/othersretrive.php.php",
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        String name,url,address,otherinfo,contact,team,venue,foodcode;
                        JSONObject json = null;
                        OthersModel othersModel;
                        othersModelList=new ArrayList<>();
                        try {
                            Log.d("getting Missing", "done");


                            for(int i = 0; i<=response.length(); i++) {
                                othersModel=new OthersModel();

                                json = response.getJSONObject(i);
                                name = json.get("name").toString();
                                url=json.get("url").toString();
                              address=json.getString("address");
                                contact=json.getString("contact");
                              otherinfo=json.getString("otherinfo");
                                othersModel.setName(name);
                                othersModel.setAddress(address);
                                othersModel.setConatct(contact);
                                othersModel.setOtherinfo(otherinfo);
                                othersModel.setUrl(url);
                                othersModelList.add(othersModel);
                                otherAdapter=new OtherAdapter(getApplicationContext(),othersModelList);
                                otherAdapter.notifyDataSetChanged();
                                recyclerView.setAdapter(otherAdapter);

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
