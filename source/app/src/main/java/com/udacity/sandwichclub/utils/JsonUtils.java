package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String LOG_TAG = JsonUtils.class.getSimpleName();

    public static Sandwich parseSandwichJson(String json) {

        List<String> alsoKnownAsJson = new ArrayList<>();
        List<String> ingredientsJson = new ArrayList<>();
        Sandwich mSandwich = new Sandwich();

        if (json!= null) {
            try {
                JSONObject sandwichDetailsJsonObject = new JSONObject(json);

                JSONObject nameJsonObject = sandwichDetailsJsonObject.getJSONObject("name");

                String mainNameJson = nameJsonObject.getString("mainName");
                mSandwich.setMainName(mainNameJson);
                Log.d(LOG_TAG, "Main Name = " + mainNameJson);

                JSONArray sandwichAkaJsonArray = nameJsonObject.getJSONArray("alsoKnownAs");
                if (sandwichAkaJsonArray != null) {
                    int len = sandwichAkaJsonArray.length();
                    for (int i = 0; i < len; i++) {
                        alsoKnownAsJson.add(sandwichAkaJsonArray.get(i).toString());
                    }
                }
                mSandwich.setAlsoKnownAs(alsoKnownAsJson);
                Log.d(LOG_TAG, "Also Known as = " + sandwichAkaJsonArray);

                String placesOfOriginJson = sandwichDetailsJsonObject.getString("placeOfOrigin");
                mSandwich.setPlaceOfOrigin(placesOfOriginJson);
                Log.d(LOG_TAG, "Places of Origin = " + placesOfOriginJson);

                String descriptionJson = sandwichDetailsJsonObject.getString("description");
                mSandwich.setDescription(descriptionJson);
                Log.d(LOG_TAG, "Description = " + descriptionJson);

                String imageJson = sandwichDetailsJsonObject.getString("image");
                mSandwich.setImage(imageJson);
                Log.d(LOG_TAG, "Image URL = " + imageJson);

                JSONArray ingredientsJsonArray = sandwichDetailsJsonObject.getJSONArray("ingredients");
                if (ingredientsJsonArray != null) {
                    int len = ingredientsJsonArray.length();
                    for (int i = 0; i < len; i++) {
                        ingredientsJson.add(ingredientsJsonArray.get(i).toString());
                    }
                }
                mSandwich.setIngredients(ingredientsJson);
                Log.d(LOG_TAG, "Ingredients = " + ingredientsJson);


            } catch (JSONException e) {
                Log.e(LOG_TAG, "ERROR: Parsing JSON => " + e.getMessage());
                return null;
            }
        }
        return mSandwich;
    }

}
