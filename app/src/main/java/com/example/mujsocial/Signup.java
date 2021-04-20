package com.example.mujsocial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Signup extends AppCompatActivity {

    EditText user;
    EditText em;
    EditText pas;
    private FirebaseAuth auth;
    private DatabaseReference mRootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Button signup = findViewById(R.id.fsignupID);
        TextView change = findViewById(R.id.switchpageID);
        user = findViewById(R.id.usernameID);
        em = findViewById(R.id.emailID);
        pas = findViewById(R.id.passwordID);
        auth = FirebaseAuth.getInstance();
        mRootRef = FirebaseDatabase.getInstance().getReference();


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String checke = em.getText().toString();
                String checkp = pas.getText().toString();
                String checku = user.getText().toString();

                if (TextUtils.isEmpty(checke) || TextUtils.isEmpty(checkp) || TextUtils.isEmpty(checku)) {
                    Toast.makeText(Signup.this, "Empty email or password or Username", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean check1 = checke.endsWith("@muj.manipal.edu");
                    if (check1) {
                        registerUser(checke, checkp, checku);
                    } else {
                        Toast.makeText(Signup.this, "Registration Failed 1", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Signup.this ,  login.class);
                startActivity(intent);
            }
        });

        ActionCodeSettings actionCodeSettings = ActionCodeSettings.newBuilder()
                .setUrl("https://www.example.com/finishSignUp?cartId=1234")
                // This must be true
                .setHandleCodeInApp(true)
                .setIOSBundleId("com.example.ios")
                .setAndroidPackageName(
                        "com.example.android",
                        true, /* installIfNotAvailable */
                        "12"    /* minimumVersion */)
                .build();
        auth.sendSignInLinkToEmail(String.valueOf(em), actionCodeSettings)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("email", "Email Sent");
                        }
                    }
                });
    }
    private void registerUser (String checke, String checkp, String checku){
        auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(checke, checkp).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("username", checku);
                    map.put("email", checke);
                    map.put("Id", auth.getCurrentUser().getUid());
                    map.put("bio", "");
                    map.put("imageurl", "default");

                    mRootRef.child("Users").child(auth.getCurrentUser().getUid()).setValue(map);


                    //askToast.makeText(startpage.this, "Succesful 1", Toast.LENGTH_SHORT).show();
                    auth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Signup.this, "Succesful 2", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Signup.this ,  mainpage.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                } else {
                    Toast.makeText(Signup.this, "Registration Failed 2", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}