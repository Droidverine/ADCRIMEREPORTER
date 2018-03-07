package com.droidverine.adcrimereporter.Acitivities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.droidverine.adcrimereporter.Extras.Connmanager;
import com.droidverine.adcrimereporter.R;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{
 Button btnmiss,btnothers;
    FirebaseAuth mFirebaseAuth;
    ProgressDialog progressDialog;
    GoogleApiClient mGoogleApiClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnmiss=(Button)findViewById(R.id.btnmissingfeed);
        btnothers=(Button)findViewById(R.id.btnothersfeed);


        Log.d("Login", "Home mGoogleApiClient after");

        // Initialize Firebase Auth
        mFirebaseAuth = FirebaseAuth.getInstance();

        btnmiss.setOnClickListener(this);
        btnothers.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_info, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.action_signout)
        {

            if (new Connmanager(HomeActivity.this).checkNetworkConnection())
            {


                new AlertDialog.Builder(this)
                        .setMessage("Are you sure you want to sign out?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                progressDialog = new ProgressDialog(HomeActivity.this);
                                progressDialog.setMessage("Signing out");
                                progressDialog.setIndeterminate(true);
                                progressDialog.setCancelable(false);
                                progressDialog.show();
                                mFirebaseAuth.signOut();
                                startActivity(new Intent(HomeActivity.this,SigninActivity.class));

                                //   mUsername = ANONYMOUS;
//            startActivity(new Intent(this, Login.class));
//            finish();
                                //    signOutCall();

                            }
                        })
                        .setNegativeButton("No", null)
                        .show();


            }
            else
            {

            }
            return true;
        }


        else
        {
            //  startActivity(new Intent(HomeActivity.this, Sponsors.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnmissingfeed:
                startActivity(new Intent(HomeActivity.this,MissingActivity.class));
                break;
            case R.id.btnothersfeed:
                startActivity(new Intent(HomeActivity.this,OthersActivity.class));
                break;
        }

    }
}
