package com.example.commerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.commerce.domain.BestSell;
import com.example.commerce.domain.Feature;
import com.example.commerce.domain.Items;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class DetailActivity extends AppCompatActivity {
    private ImageView Image;
    private TextView ItemName;
    private TextView Price;
    private Button ItemRating;
    private TextView ItemRatDesc;
    private TextView ItemDesc;
    private Button AddToCart;
    private Button BuyBtn;
    private Toolbar Toolbar;
    FirebaseFirestore Store;
    FirebaseAuth Auth;

    Feature feature = null;
    BestSell bestSell = null;
    Items items=null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar=findViewById(R.id.detail_toolbar);
        setSupportActionBar(Toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Image=findViewById(R.id.item_img);
        ItemName=findViewById(R.id.item_name);
        Price=findViewById(R.id.item_price);
        ItemRating=findViewById(R.id.item_rating);
        ItemRatDesc=findViewById(R.id.item_rat_des);
        ItemDesc=findViewById(R.id.item_des);
        AddToCart=findViewById(R.id.item_add_cart);
        BuyBtn=findViewById(R.id.item_buy_now);
        Store=FirebaseFirestore.getInstance();
        Auth=FirebaseAuth.getInstance();
        final Object obj= getIntent().getSerializableExtra("detail");
        if( obj instanceof Feature){
            feature= (Feature) obj;
        }else if(obj instanceof BestSell) {
            bestSell = (BestSell) obj;
        }
        else if(obj instanceof Items) {
            items = (Items) obj;
        }
        if(feature!=null){
            Glide.with(getApplicationContext()).load(feature.getImg_url()).into(Image);
            ItemName.setText(feature.getName());
            Price.setText(feature.getPrice()+"€");
            ItemRating.setText(feature.getRating()+"");
            switch (feature.getRating()){
                case '1':ItemRatDesc.setText("I just hate it!");
                case '2':ItemRatDesc.setText("I don't like it!");
                case '3':ItemRatDesc.setText("It is awesome!");
                case '4':ItemRatDesc.setText("I just like it!");
                case '5':ItemRatDesc.setText("I just love it!");
            }
            /*ItemDesc.setText(feature.getDescription());*/
        }
        if(bestSell!=null){
            Glide.with(getApplicationContext()).load(bestSell.getImg_url()).into(Image);
            ItemName.setText(bestSell.getName());
            Price.setText(bestSell.getPrice()+"€");
            ItemRating.setText(bestSell.getRating()+"");
            switch (bestSell.getRating()){
                case '1':ItemRatDesc.setText("I just hate it!");
                case '2':ItemRatDesc.setText("I don't like it!");
                case '3':ItemRatDesc.setText("It is awesome!");
                case '4':ItemRatDesc.setText("I just like it!");
                case '5':ItemRatDesc.setText("I just love it!");
            }
            /*ItemDesc.setText(bestSell.getDescription());*/
        }

        if(items!=null){
            Glide.with(getApplicationContext()).load(items.getImg_url()).into(Image);
            ItemName.setText(items.getName());
            Price.setText(items.getPrice()+"€");
            ItemRating.setText(items.getRating()+"");
            switch (items.getRating()){
                case '1':ItemRatDesc.setText("I just hate it!");
                case '2':ItemRatDesc.setText("I don't like it!");
                case '3':ItemRatDesc.setText("It is awesome!");
                case '4':ItemRatDesc.setText("I just like it!");
                case '5':ItemRatDesc.setText("I just love it!");
            }
            /*ItemDesc.setText(items.getDescription());*/
        }
        AddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Store.collection("All");
                        Intent intent=new Intent(DetailActivity.this,HomeActivity.class);
                        startActivity(intent);
                Toast.makeText(DetailActivity.this, "Successfully added to cart.", Toast.LENGTH_SHORT).show();
            }
        });
        BuyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DetailActivity.this,AddressActivity.class);
                if(feature!=null){
                    intent.putExtra("item", feature);
                }
                if(bestSell!=null){
                    intent.putExtra("item", bestSell);
                }
                if(items!=null){
                    intent.putExtra("item", items);
                }
                startActivity(intent);
            }
        });





    }
}