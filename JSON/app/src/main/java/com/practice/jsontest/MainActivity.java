package com.practice.jsontest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView) findViewById(R.id.textView);

        new TransTask().execute("https://data.boch.gov.tw/opendata/assetsCase/6.1.json");
    }
    class TransTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            StringBuilder sb = new StringBuilder();
            try {
                URL url = new URL(params[0]);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(url.openStream()));
                String line = in.readLine();
                while(line!=null){
                    Log.d("HTTP", line);
                    sb.append(line);
                    line = in.readLine();
                    // Log.d("Size",String.valueOf(line.length()));
                    //轉換區

                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d("sb",String.valueOf(sb));
            return sb.toString();

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("JSON", s);
            parseJSON(s);
        }
        private void parseJSON(String s) {
            result="";
            ArrayList<JSON> trans = new ArrayList<>();
            try {
                JSONArray array = new JSONArray(s);
                JSONArray CriteriaArray=array.getJSONObject(6).getJSONArray("judgeCriteria");
                for (int i=0; i<array.length(); i++){
                    JSONObject obj = array.getJSONObject(i);
                    String caseID = obj.getString("caseId");
                    String caseName = obj.getString("caseName");
                    String assetsClassifyCode= obj.getString("assetsClassifyCode");;
                   // String representImage=obj.getString("representImage");
                    String C=obj.getString("judgeCriteria");
                    try {String representImage=obj.getString("representImage");
                        Log.d("Image",representImage);

                    }catch (Exception e){
                        e.printStackTrace();
                        System.out.println((caseID+" noImage!"));
                    }


                    result+=caseID+"\t"+caseName+"\t"+assetsClassifyCode+"\t"+C+"\n";
                    Log.d("resultTest:",caseID+"/"+caseName+"/"+assetsClassifyCode+"/"+C);
                    JSON J = new JSON(caseID, caseName, assetsClassifyCode);
                    trans.add(J);
//                    for(int j=0;j<CriteriaArray.length();j++){
//                        String Critera=array.getJSONObject(i).getJSONArray("judgeCriteria").getJSONObject(j).getString("judgeCriteria");
//                        Log.d("Critera", Critera);
//                    }
                }
                textView.setText(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

    }

}