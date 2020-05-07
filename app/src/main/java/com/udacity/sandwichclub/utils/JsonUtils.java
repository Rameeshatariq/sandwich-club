package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        if(json == null){
            return null;
        }
        try {
                JSONObject jsonSandwich = new JSONObject(json);
                JSONArray ingredientsJson = jsonSandwich.getJSONArray("ingredients");
                JSONArray alsoKnownAsJson = jsonSandwich.getJSONObject("name").getJSONArray("alsoKnownAs");

                String name = jsonSandwich.getJSONObject("name").getString("mainName");
                String image = jsonSandwich.getString("image");

                String description = jsonSandwich.getString("description");
                String placeOfOrigin = jsonSandwich.getString("placeOfOrigin");

                List<String> ingredients = new ArrayList<>();
                for (int i=0; i<ingredientsJson.length(); i++){
                    ingredients.add(ingredientsJson.getString(i));
                }

                List<String> alsoKnownAs = new ArrayList<>();
                for (int i=0; i<alsoKnownAsJson.length(); i++){
                    alsoKnownAs.add(alsoKnownAsJson.getString(i));
                }

                return new Sandwich(name, alsoKnownAs, placeOfOrigin, description, image, ingredients);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new Sandwich();
    }
}
