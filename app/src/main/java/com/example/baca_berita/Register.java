package com.example.baca_berita;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {
    private EditText etusername,etpassword;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        etusername = findViewById(R.id.et_username);
        etpassword = findViewById(R.id.et_password);

        TextView register = findViewById(R.id.login);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent GoRegister = new Intent(Register.this, Login.class);
                finish();
                startActivity(GoRegister);
            }
        });
        Button regis = findViewById(R.id.btnregister);
        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=etusername.getText().toString();
                String password=etpassword.getText().toString();
                if(username.equals("")){
                    Toast.makeText(Register.this,"Silahkan isi username anda",
                            Toast.LENGTH_SHORT).show();
                }else   if(password.equals("")) {
                    Toast.makeText(Register.this, "Silahkan isi password anda ",
                            Toast.LENGTH_SHORT).show();
                }else {


                    mAuth.createUserWithEmailAndPassword(username, password)
                            .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Toast.makeText(Register.this, "Registrasi Sukses ",
                                                Toast.LENGTH_SHORT).show();

                                    } else {
                                        // If sign in fails, display a message to the user.

                                        Toast.makeText(Register.this, "Registrasi Gagal",
                                                Toast.LENGTH_SHORT).show();

                                    }

                                }
                            });
                }

            }
        });

    }
    @Override
    public void onBackPressed(){
        Intent GoRegister = new Intent(Register.this, Login.class);
        finish();
        startActivity(GoRegister);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }
}