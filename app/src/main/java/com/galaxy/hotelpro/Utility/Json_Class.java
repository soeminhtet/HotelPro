package com.galaxy.hotelpro.Utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Json_Class {

    public static String getJson(String URL){
        URL url = null;
        URLConnection urlConnection = null;
        String result = null;
        int responseCode = 0;
        try {
            url = new URL(URL);
            urlConnection = (HttpURLConnection) url.openConnection();
            responseCode =  ((HttpURLConnection) urlConnection).getResponseCode();
            urlConnection.connect();
            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line = null;
            while((line = br.readLine()) != null){
                stringBuilder.append(line);
            }
            if(stringBuilder.length() == 0)
            {
                return null;
            }
            result = stringBuilder.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String postJson(){
        String resut="";

        URL url;
        HttpURLConnection connect;

        try
        {
            url = new URL("http://192.168.0.21/api/Students");

            JSONObject params = new JSONObject();
            params.put("name", "abc");
            params.put("address", "abc@gmail.com");
            params.put("phno","099999");

            connect = (HttpURLConnection)url.openConnection();
            connect.setRequestMethod("POST");
            connect.setDoInput(true);
            connect.setDoOutput(true);

            connect.setRequestProperty("Accept", "*/*");
            connect.setRequestProperty("Accept-Encoding", "gzip,deflate,sdch");
            connect.setRequestProperty("Accept-Language", "en-US,en;q=0.8");
            connect.setRequestProperty("Connection", "keep-alive");
            connect.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connect.setRequestProperty("X-Requested-With", "XMLHttpRequest");

            OutputStreamWriter osw = new OutputStreamWriter(connect.getOutputStream());
            osw.write(params.toString());
            osw.flush();
            osw.close();

            Scanner in = new Scanner(connect.getInputStream());
            while (in.hasNextLine()) { System.out.println(in.nextLine()); }
        }
        catch (Exception e) { e.printStackTrace(); }

        return  resut;
    }

}

