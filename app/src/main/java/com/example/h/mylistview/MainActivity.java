package com.example.h.mylistview;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.h.mylistview.toutv.Lineup;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    ListView list;
    MyAdapter adapter;
    Lineup films;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
type = intent.getStringExtra("typeemission");

        list = (ListView)findViewById(R.id.listView);
        RunApi runApi = new RunApi();
        runApi.execute();
    }

    public class RunApi extends AsyncTask<String, Object, Lineup>{

        @Override
        protected Lineup doInBackground(String... params) {
            WebApi api = new WebApi(type);
            try{
                films = api.run();
            }catch (IOException e){}
            return films;
        }

        @Override
        protected void onPostExecute(Lineup lineup) {
            super.onPostExecute(lineup);
            adapter = new MyAdapter();
            list.setAdapter(adapter);
        }
    }

    public class MyAdapter extends BaseAdapter {

        LayoutInflater inflater;

        public MyAdapter() {
            inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return films.LineupItems.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v==null){
                v = inflater.inflate(R.layout.myrows, parent, false);
            }


            TextView tv = (TextView)v.findViewById(R.id.row_text);
            ImageView iv = (ImageView)v.findViewById(R.id.row_image);

            String title = films.LineupItems.get(position).Title;
            String url = films.LineupItems.get(position).ImageUrl;

            tv.setText(title);
            Picasso.with(getApplicationContext()).load(url).into(iv);

            if (position%2==0){
                v.setBackgroundColor(0xFF00FF00);
            }
            else{
                v.setBackgroundColor(0xFFFFFFFF);
            }

            return v;
        }
    }
}
