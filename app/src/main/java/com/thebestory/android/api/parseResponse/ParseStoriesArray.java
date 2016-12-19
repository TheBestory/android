/*
 * The Bestory Project
 */

package com.thebestory.android.api.parseResponse;

import android.util.JsonReader;
import android.util.Log;

import com.thebestory.android.model.Story;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class ParseStoriesArray implements ParseResponse<ArrayList<Story>> {
    @Override
    public ArrayList<Story> parse(JsonReader response) throws IOException, ParseException {
        ArrayList<Story> stories = new ArrayList();

        response.beginArray();
        while (response.hasNext()) {
            stories.add(Story.parse(response));
        }
        response.endArray();
        return stories;
    }
}
