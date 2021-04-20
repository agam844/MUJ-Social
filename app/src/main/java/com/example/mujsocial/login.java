package com.example.mujsocial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    Button login;
    EditText em;
    EditText pas;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.floginID);
        em = findViewById(R.id.emailID2);
        pas = findViewById(R.id.passwordID2);
        TextView change = findViewById(R.id.changeL);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String checke = em.getText().toString();
                String checkp = pas.getText().toString();


                loginUser(checke, checkp);
            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(login.this, "Succesful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(login.this ,  Signup.class);
                startActivity(intent);
            }
        });

    }


    private void loginUser (String checke, String checkp){
        auth = FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(checke, checkp).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(login.this, "Succesful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(login.this ,  mainpage.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}