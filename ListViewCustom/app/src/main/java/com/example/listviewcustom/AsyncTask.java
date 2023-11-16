package com.example.listviewcustom;

import android.content.Context;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

public class AsyncTask extends android.os.AsyncTask<Void,Void,Void> {
    AsyncAdapter adapter;
    private ListView listView;
    private Context context;
    ArrayList<Contact> list;
    private String link = "https://www.carqueryapi.com/api/0.3/?callback=?&cmd=getModels&make=Mercedes-Benz";
    private String jsonStr = null;

    public AsyncTask(Context context, ListView listView) {
        this.context = context;
        this.list = new ArrayList<>();
        this.listView = listView;
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        adapter = new AsyncAdapter(context, list);
        listView.setAdapter(adapter);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL(link);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line = "";
            while ((line = br.readLine())!=null) {
                sb.append(line);
            }
            jsonStr = sb.toString();
            JSONObject contacts = new JSONObject(jsonStr);
            JSONArray jsonArray = contacts.getJSONArray("Models");
            for (int i = 0; i<jsonArray.length();i++) {
                JSONObject c = jsonArray.getJSONObject(i);
                String name = c.getString("model_name");
                String id = c.getString("model_make_id");

                Contact contact = new Contact();
                contact.setName(name);
                contact.setId(id);
                list.add(contact);
            }
            urlConnection.disconnect();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
