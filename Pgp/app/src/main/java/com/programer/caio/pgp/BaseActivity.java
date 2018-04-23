package com.programer.caio.pgp;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class BaseActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;


    public String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }


}
