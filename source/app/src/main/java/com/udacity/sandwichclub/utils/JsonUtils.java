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

    private static final String NAME = "name";
    private static final String MAIN_NAME = "mainName";
    private static final String ALSO_KNOWN_AS = "alsoKnownAs";
    private static final String PLACE_OF_ORIGIN = "placeOfOrigin";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE = "image";
    private static final String INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {

        JSONObject sandwichDetailsJsonObject;
        String mainNameJson;
        List<String> alsoKnownAsJson = new ArrayList<>();
        String placeOfOriginJson;
        String descriptionJson;
        String imageJson;
        List<String> ingredientsJson = new ArrayList<>();

        Sandwich mSandwich = new Sandwich();

        if (json!= null) {
            try {
                sandwichDetailsJsonObject = new JSONObject(json);

                JSONObject nameJsonObject = sandwichDetailsJsonObject.getJSONObject(NAME);

                mainNameJson = nameJsonObject.getString(MAIN_NAME);
                mSandwich.setMainName(mainNameJson);
                Log.d(LOG_TAG, "Main Name = " + mainNameJson);

                JSONArray sandwichAkaJsonArray = nameJsonObject.getJSONArray(ALSO_KNOWN_AS);
                if (sandwichAkaJsonArray != null) {
                    int len = sandwichAkaJsonArray.length();
                    for (int i = 0; i < len; i++) {
                        alsoKnownAsJson.add((String) sandwichAkaJsonArray.get(i));
                    }
                }
                mSandwich.setAlsoKnownAs(alsoKnownAsJson);
                Log.d(LOG_TAG, "Also Known as = " + sandwichAkaJsonArray);

                placeOfOriginJson = sandwichDetailsJsonObject.getString(PLACE_OF_ORIGIN);
                mSandwich.setPlaceOfOrigin(placeOfOriginJson);
                Log.d(LOG_TAG, "Place of Origin = " + placeOfOriginJson);

                descriptionJson = sandwichDetailsJsonObject.getString(DESCRIPTION);
                mSandwich.setDescription(descriptionJson);
                Log.d(LOG_TAG, "Description = " + descriptionJson);

                imageJson = sandwichDetailsJsonObject.getString(IMAGE);
                mSandwich.setImage(imageJson);
                Log.d(LOG_TAG, "Image URL = " + imageJson);

                JSONArray ingredientsJsonArray = sandwichDetailsJsonObject.getJSONArray(INGREDIENTS);
                if (ingredientsJsonArray != null) {
                    int len = ingredientsJsonArray.length();
                    for (int i = 0; i < len; i++) {
                        ingredientsJson.add((String) ingredientsJsonArray.get(i));
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
