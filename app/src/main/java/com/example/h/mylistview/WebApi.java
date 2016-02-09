package com.example.h.mylistview;

import android.app.DownloadManager;

import com.example.h.mylistview.toutv.Lineup;
import com.example.h.mylistview.toutv.Root;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by h on 28/01/16.
 */
public class WebApi {

    public String _url;
    public String _type;

    public WebApi(String type){
        _url = "http://nasv3d.iro.umontreal.ca/toutv/section-populaires.json";
        _type = type;
    }

    public Lineup run() throws IOException{
        // get json
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(_url).build();
        Response response = client.newCall(request).execute();
        String json = response.body().string();

        // parse json
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<Root> jsonAdapter = moshi.adapter(Root.class);
        Root root = jsonAdapter.fromJson(json);

        // get type
        Lineup lineup;
        for(int i=0;i<root.Lineups.size();i++){
            if (root.Lineups.get(i).Title.equals(_type)){
                lineup = root.Lineups.get(i);
                return lineup;
            }
        }
        return null;
    }

}
