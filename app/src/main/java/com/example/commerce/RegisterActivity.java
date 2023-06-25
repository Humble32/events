package com.example.commerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private EditText Name;
    private EditText Email;
    private EditText Password;
    private Button RegBtn;
    //private Toolbar Toolbar;
    private FirebaseAuth Auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Name=findViewById(R.id.reg_name);
        Email=findViewById(R.id.reg_email);
        Password=findViewById(R.id.reg_password);
        RegBtn=findViewById(R.id.reg_btn);
/*      Toolbar=findViewById(R.id.reg_toolbar);
        setSupportActionBar(Toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/
        Auth=FirebaseAuth.getInstance();

        RegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= Name.getText().toString();
                String email= Email.getText().toString();
                String password= Password.getText().toString();
                if(!name.isEmpty() && !email.isEmpty() && !password.isEmpty()){
                    Auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                           if(task.isSuccessful()){
                               Toast.makeText(RegisterActivity.this,"Account successfully created",Toast.LENGTH_SHORT).show();
                               Intent intent=new Intent(RegisterActivity.this,HomeActivity.class);
                               startActivity(intent);
                           }else {
                               Toast.makeText(RegisterActivity.this,""+task.getException(),Toast.LENGTH_SHORT).show();

                           }
                        }
                    });
                }else {
                    Toast.makeText(RegisterActivity.this,"Please fill empty field",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void SignIn(View view) {
        Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(intent);
    }


}