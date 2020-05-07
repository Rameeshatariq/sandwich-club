package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private Sandwich sandwich;
    ImageView ingredientsIv;
    TextView ingredientsTv;
    TextView descriptionTv;
    TextView placeoforiginTv;
    TextView alsoknownasTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ingredientsIv = findViewById(R.id.image_iv);
        ingredientsTv = findViewById(R.id.ingredients_tv);
        descriptionTv = findViewById(R.id.description_tv);
        placeoforiginTv = findViewById(R.id.origin_tv);
        alsoknownasTv = findViewById(R.id.also_known_tv);


        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {

        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        if(!sandwich.getIngredients().isEmpty()){
            ingredientsTv.setText(TextUtils.join("\n", sandwich.getIngredients()));
        }
        else{
            ingredientsTv.setText("-");
        }
        if(!sandwich.getDescription().isEmpty()){
            descriptionTv.setText(String.valueOf(sandwich.getDescription()));
        }else{
            descriptionTv.setText("-");
        }
        if(!sandwich.getAlsoKnownAs().isEmpty()){
            alsoknownasTv.setText(TextUtils.join("\n", sandwich.getAlsoKnownAs()));
        }else{
            alsoknownasTv.setText("-");
        }
        if(!sandwich.getPlaceOfOrigin().isEmpty()){
            placeoforiginTv.setText(String.valueOf(sandwich.getPlaceOfOrigin()));
        }
        else{
            placeoforiginTv.setText("-");
        }

    }
}
