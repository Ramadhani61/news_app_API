package com.example.baca_berita;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText etusername, etpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        etusername=findViewById(R.id.user);
        etpassword=findViewById(R.id.pass);

        TextView register = findViewById(R.id.Register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent GoRegister = new Intent(Login.this, Register.class);
                finish();
                startActivity(GoRegister);
            }
        });

        Button login=findViewById(R.id.fab_login);
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String username=etusername.getText().toString();
                String password=etpassword.getText().toString();
                if(username.equals("")){
                    Toast.makeText(Login.this,"Silahkan Masukkan username anda",Toast.LENGTH_SHORT).show();
                }else if(password.equals("")){
                    Toast.makeText(Login.this,"Silahkan Masukkan Password anda",Toast.LENGTH_SHORT).show();
                }else{
                    mAuth.signInWithEmailAndPassword(username, password)
                            .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Toast.makeText(Login.this, "Login berhasil",
                                                Toast.LENGTH_SHORT).show();
                                        Intent home= new Intent(Login.this, MainActivity.class);
                                        startActivity(home);
                                        finish();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(Login.this, "Login Gagal",
                                                Toast.LENGTH_SHORT).show();

                                    }

                                    // ...
                                }
                            });
                }
            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }
    }

