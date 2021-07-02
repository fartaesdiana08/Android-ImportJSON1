package com.example.importjson1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnAdd;
    ListView listView;
    final int REQUEST_CODE =100;

    //pt adapter
    List<Timbru> listaTimbre = new ArrayList<Timbru>();
    ArrayAdapter adapter;

    //pt adapter
    Button json;
    private static final String linkURL="https://jsonkeeper.com/b/FSLK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        btnAdd = findViewById(R.id.btnAdd);
        json=findViewById(R.id.btnJSON);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AdaugaTimbruActivity.class);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });

        //punem in listview cu adapter

        adapter = new ArrayAdapter<Timbru>(getApplicationContext(), android.R.layout.simple_list_item_1, listaTimbre);
        listView.setAdapter(adapter);

        //pt json
        json.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimbruData fetchData = new TimbruData(){
                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                        listaTimbre.addAll(timbreJSON);
                        adapter.notifyDataSetChanged();
                    }
                };
                try {
                    fetchData.execute(new URL(linkURL));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            final Timbru timbru = (Timbru) data.getSerializableExtra(AdaugaTimbruActivity.ADD_TIMBRU);

            if (timbru != null) {
                listaTimbre.add(timbru);
                Toast.makeText(getApplicationContext(), timbru.toString(), Toast.LENGTH_LONG).show();
                ArrayAdapter<Timbru> adapter1 = new ArrayAdapter<Timbru>(getApplicationContext(), android.R.layout.simple_list_item_1, listaTimbre);
                listView.setAdapter(adapter1);
            }
        }
    }

    //pentru JSON
    public class TimbruData extends AsyncTask<URL,Void,Void>
    {
        @Override
        protected Void doInBackground(URL... urls) {
            HttpURLConnection conn = null;

            try {
                conn = (HttpURLConnection) urls[0].openConnection();
                conn.setRequestMethod("GET");
                InputStream ist = conn.getInputStream();

                InputStreamReader isr = new InputStreamReader(ist);
                BufferedReader br = new BufferedReader(isr);
                String linie = "";
                String sbuf = "";
                while ((linie = br.readLine()) != null) {
                    sbuf += linie + "\n";
                }
                loadJSONObject(sbuf);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (conn != null)
                    conn.disconnect();
            }
            return null;
        }
        List<Timbru> timbreJSON = new ArrayList<>();
        public void loadJSONObject(String jsonStr) {
            if(jsonStr != null){
                try {
                    JSONObject jsonObject = new JSONObject(jsonStr);
                    JSONArray timbre = jsonObject.getJSONArray("timbre");

                    for(int i=0; i<timbre.length(); i++){
                        JSONObject timbru = timbre.getJSONObject(i);
                        Timbru timbruJSON = new Timbru(
                                timbru.getString("nume"),
                                Integer.parseInt(timbru.getString("calitate")),
                                Integer.parseInt(timbru.getString("pret")),
                                timbru.getString("categorie"),
                                Integer.parseInt(timbru.getString("luna")),
                                timbru.getString("culoare")
                        );
                        timbreJSON.add(timbruJSON);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}