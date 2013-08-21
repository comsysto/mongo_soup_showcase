package com.comsysto.movie.repository.mongodb.config;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

/**
 * User: fd
 * Date: 8/9/13
 * Time: 10:42 AM
 */
public class Credentials {

    private final JSONObject credFile;

    public Credentials() throws IOException {
        this.credFile = getCredFile();
    }

    public String getCredential(String param, String addon) {
        String upper_addon = addon.toUpperCase();
        JSONObject addonJSON = getAddon(credFile, upper_addon);
        return addonJSON.get(param.toUpperCase()).toString();
    }

    private JSONObject getAddon(JSONObject credFile, String addonName) {
        return (JSONObject) credFile.get(addonName);
    }

    public String getMongoSoupDbName() {

        String mongoSoupUrl = getCredential("mongosoup_url", "mongosoup");

        String[] splits = mongoSoupUrl.split("/");

        return splits[splits.length-1];
    }

    private JSONObject getCredFile() throws IOException {
        try {
            String credFilePath = System.getenv("CRED_FILE");
            FileReader fr = new FileReader(credFilePath);
            JSONParser parserJSON = new JSONParser();
            return (JSONObject) parserJSON.parse(fr);

        } catch (ParseException parseException) {
            throw new IOException(parseException);
        }
    }

}
