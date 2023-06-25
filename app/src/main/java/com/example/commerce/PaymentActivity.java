package com.example.commerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
/*import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;*/

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PaymentActivity extends AppCompatActivity{
    TextView Total;
    Button payBtn;
    double amount=0.0;
    String name="";
    String img_url="";
    FirebaseFirestore Store;
    FirebaseFirestore Auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        amount=getIntent().getDoubleExtra("amount",0.0);
        name=getIntent().getStringExtra("name");
        img_url=getIntent().getStringExtra("img_url");
        Store=FirebaseFirestore.getInstance();
        Auth=FirebaseFirestore.getInstance();
        Total=findViewById(R.id.sub_total);
        payBtn=findViewById(R.id.pay_btn);
        Total.setText(amount+""+"€");
        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PaymentActivity.this, "Payment Successfull", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(PaymentActivity.this,HomeActivity.class);
                                startActivity(intent);
                                finish();
                            }
        });
    }
}