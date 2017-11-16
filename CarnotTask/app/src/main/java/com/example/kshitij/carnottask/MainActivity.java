package com.example.kshitij.carnottask;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kshitij.carnottask.Utility.Constant;
import com.example.kshitij.carnottask.model.DatabaseModelComments;
import com.example.kshitij.carnottask.model.DatabaseModelPhotos;
import com.example.kshitij.carnottask.model.DatabaseModelTodos;
import com.example.kshitij.carnottask.model.DatabaseModelPosts;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    int hour;
    int minute;
    int seconds;
    String currentTime = "";
    TextView btn1txtStart, btn1txtEnd, btn1txtSaveStart, btn1txtSaveEnd;
    TextView btn2txtStart, btn2txtEnd, btn2txtSaveStart, btn2txtSaveEnd;
    TextView btn3txtStart, btn3txtEnd, btn3txtSaveStart, btn3txtSaveEnd;
    TextView btn4txtStart, btn4txtEnd, btn4txtSaveStart, btn4txtSaveEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActiveAndroid.initialize(this);
        btn1txtStart = (TextView) findViewById(R.id.btn1txtStart);
        btn1txtEnd = (TextView) findViewById(R.id.btn1txtEnd);
        btn1txtSaveStart = (TextView) findViewById(R.id.btn1txtStartSave);
        btn1txtSaveEnd = (TextView) findViewById(R.id.btn1txtEndSave);
        btn2txtStart = (TextView) findViewById(R.id.btn2txtStart);
        btn2txtEnd = (TextView) findViewById(R.id.btn2txtEnd);
        btn2txtSaveStart = (TextView) findViewById(R.id.btn2txtStartSave);
        btn2txtSaveEnd = (TextView) findViewById(R.id.btn2txtEndSave);
        btn3txtStart = (TextView) findViewById(R.id.btn3txtStart);
        btn3txtEnd = (TextView) findViewById(R.id.btn3txtEnd);
        btn3txtSaveStart = (TextView) findViewById(R.id.btn3txtStartSave);
        btn3txtSaveEnd = (TextView) findViewById(R.id.btn3txtEndSave);
        btn4txtStart = (TextView) findViewById(R.id.btn4txtStart);
        btn4txtEnd = (TextView) findViewById(R.id.btn4txtEnd);
        btn4txtSaveStart = (TextView) findViewById(R.id.btn4txtStartSave);
        btn4txtSaveEnd = (TextView) findViewById(R.id.btn4txtEndSave);

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }

        return isAvailable;
    }

    public void btn1SuccessFetchComments(View v) {
        if (isNetworkAvailable()) {
            fetchComments();
        } else {
            Toast.makeText(this, "Network is unavailable!", Toast.LENGTH_LONG).show();
        }
    }

    public void btn2SuccessFetchPhotos(View v) {
        if (isNetworkAvailable()) {
            fetchPhotos();
        } else {
            Toast.makeText(this, "Network is unavailable!", Toast.LENGTH_LONG).show();
        }
    }

    public void btn3SuccessFetchTodos(View v) {
        if (isNetworkAvailable()) {
            fetchTodos();
        } else {
            Toast.makeText(this, "Network is unavailable!", Toast.LENGTH_LONG).show();
        }
    }

    public void btn4SuccessFetchPosts(View v) {
        if (isNetworkAvailable()) {
            fetchPosts();
        } else {
            Toast.makeText(this, "Network is unavailable!", Toast.LENGTH_LONG).show();
        }
    }

    public void fetchComments() {

        StringRequest request = new StringRequest(Request.Method.GET, Constant.Webservice.WEBSERVICE_COMMENTS,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // TODO Auto-generated method stub
                        Calendar c = Calendar.getInstance();

                        hour = c.get(Calendar.HOUR_OF_DAY);
                        minute = c.get(Calendar.MINUTE);
                        seconds = c.get(Calendar.SECOND);
                        currentTime = hour + ":" + minute + ":" + seconds;
                        btn1txtEnd.setText(currentTime);
                        try {
                            Calendar c1 = Calendar.getInstance();

                            hour = c1.get(Calendar.HOUR_OF_DAY);
                            minute = c1.get(Calendar.MINUTE);
                            seconds = c1.get(Calendar.SECOND);
                            currentTime = hour + ":" + minute + ":" + seconds;
                            btn1txtSaveStart.setText(currentTime);
                            JSONArray mainObject = new JSONArray(response);
                            for (int i = 0; i < mainObject.length(); i++) {
                                JSONObject data = mainObject.getJSONObject(i);
                                DatabaseModelComments url1 = new DatabaseModelComments();
                                url1.setPostID(data.getString("postId"));
                                url1.setName(data.getString("name"));
                                url1.setEmail(data.getString("email"));
                                url1.setBody(data.getString("body"));
                                url1.save();
                            }
                            Calendar c2 = Calendar.getInstance();

                            hour = c2.get(Calendar.HOUR_OF_DAY);
                            minute = c2.get(Calendar.MINUTE);
                            seconds = c2.get(Calendar.SECOND);
                            currentTime = hour + ":" + minute + ":" + seconds;
                            btn1txtSaveEnd.setText(currentTime);
                        } catch (JSONException e1) {

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                finish();
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(10000, 2, 2f));
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        Calendar c = Calendar.getInstance();

        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
        seconds = c.get(Calendar.SECOND);
        currentTime = hour + ":" + minute + ":" + seconds;
        btn1txtStart.setText(currentTime);
        requestQueue.add(request);
    }

    public void fetchPhotos() {
        StringRequest request = new StringRequest(Request.Method.GET, Constant.Webservice.WEBSERVICE_PHOTOS,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // TODO Auto-generated method stub
                        Calendar c = Calendar.getInstance();

                        hour = c.get(Calendar.HOUR_OF_DAY);
                        minute = c.get(Calendar.MINUTE);
                        seconds = c.get(Calendar.SECOND);
                        currentTime = hour + ":" + minute + ":" + seconds;
                        btn2txtEnd.setText(currentTime);
                        try {
                            Calendar c1 = Calendar.getInstance();

                            hour = c1.get(Calendar.HOUR_OF_DAY);
                            minute = c1.get(Calendar.MINUTE);
                            seconds = c1.get(Calendar.SECOND);
                            currentTime = hour + ":" + minute + ":" + seconds;
                            btn2txtSaveStart.setText(currentTime);
                            JSONArray mainObject = new JSONArray(response);
                            for (int i = 0; i < mainObject.length(); i++) {
                                JSONObject data = mainObject.getJSONObject(i);
                                DatabaseModelPhotos url2 = new DatabaseModelPhotos();
                                url2.setAlbumId(data.getString("albumId"));
                                url2.setTitle(data.getString("title"));
                                url2.setThumbnailUrl(data.getString("thumbnailUrl"));
                                url2.setUrl(data.getString("url"));
                                url2.save();
                            }
                            Calendar c2 = Calendar.getInstance();

                            hour = c2.get(Calendar.HOUR_OF_DAY);
                            minute = c2.get(Calendar.MINUTE);
                            seconds = c2.get(Calendar.SECOND);
                            currentTime = hour + ":" + minute + ":" + seconds;
                            btn2txtSaveEnd.setText(currentTime);
                        } catch (JSONException e1) {

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                finish();
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(10000, 2, 2f));
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        Calendar c = Calendar.getInstance();

        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
        seconds = c.get(Calendar.SECOND);
        currentTime = hour + ":" + minute + ":" + seconds;
        btn2txtStart.setText(currentTime);
        requestQueue.add(request);
    }

    public void fetchTodos() {
        StringRequest request = new StringRequest(Request.Method.GET, Constant.Webservice.WEBSERVICE_TODOS,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // TODO Auto-generated method stub
                        Calendar c = Calendar.getInstance();

                        hour = c.get(Calendar.HOUR_OF_DAY);
                        minute = c.get(Calendar.MINUTE);
                        seconds = c.get(Calendar.SECOND);
                        currentTime = hour + ":" + minute + ":" + seconds;
                        btn3txtEnd.setText(currentTime);
                        try {
                            Calendar c1 = Calendar.getInstance();

                            hour = c1.get(Calendar.HOUR_OF_DAY);
                            minute = c1.get(Calendar.MINUTE);
                            seconds = c1.get(Calendar.SECOND);
                            currentTime = hour + ":" + minute + ":" + seconds;
                            btn3txtSaveStart.setText(currentTime);
                            JSONArray mainObject = new JSONArray(response);
                            for (int i = 0; i < mainObject.length(); i++) {
                                JSONObject data = mainObject.getJSONObject(i);
                                DatabaseModelTodos url3 = new DatabaseModelTodos();
                                url3.setUserId(data.getString("userId"));
                                url3.setTitle(data.getString("title"));
                                url3.setCompleted(data.getString("completed"));
                                url3.save();
                            }
                            Calendar c2 = Calendar.getInstance();

                            hour = c2.get(Calendar.HOUR_OF_DAY);
                            minute = c2.get(Calendar.MINUTE);
                            seconds = c2.get(Calendar.SECOND);
                            currentTime = hour + ":" + minute + ":" + seconds;
                            btn3txtSaveEnd.setText(currentTime);
                        } catch (JSONException e1) {

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                finish();
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(10000, 2, 2f));
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        Calendar c = Calendar.getInstance();

        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
        seconds = c.get(Calendar.SECOND);
        currentTime = hour + ":" + minute + ":" + seconds;
        btn3txtStart.setText(currentTime);
        requestQueue.add(request);
    }

    public void fetchPosts() {
        StringRequest request = new StringRequest(Request.Method.GET, Constant.Webservice.WEBSERVICE_POSTS,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // TODO Auto-generated method stub
                        Calendar c = Calendar.getInstance();

                        hour = c.get(Calendar.HOUR_OF_DAY);
                        minute = c.get(Calendar.MINUTE);
                        seconds = c.get(Calendar.SECOND);
                        currentTime = hour + ":" + minute + ":" + seconds;
                        btn4txtEnd.setText(currentTime);
                        try {
                            Calendar c1 = Calendar.getInstance();

                            hour = c1.get(Calendar.HOUR_OF_DAY);
                            minute = c1.get(Calendar.MINUTE);
                            seconds = c1.get(Calendar.SECOND);
                            currentTime = hour + ":" + minute + ":" + seconds;
                            btn4txtSaveStart.setText(currentTime);
                            JSONArray mainObject = new JSONArray(response);
                            for (int i = 0; i < mainObject.length(); i++) {
                                JSONObject data = mainObject.getJSONObject(i);
                                DatabaseModelPosts url4 = new DatabaseModelPosts();
                                url4.setUserId(data.getString("userId"));
                                url4.setTitle(data.getString("title"));
                                url4.setBody(data.getString("body"));
                                url4.save();
                            }
                            Calendar c2 = Calendar.getInstance();

                            hour = c2.get(Calendar.HOUR_OF_DAY);
                            minute = c2.get(Calendar.MINUTE);
                            seconds = c2.get(Calendar.SECOND);
                            currentTime = hour + ":" + minute + ":" + seconds;
                            btn4txtSaveEnd.setText(currentTime);
                        } catch (JSONException e1) {

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                finish();
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(10000, 2, 2f));
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        Calendar c = Calendar.getInstance();

        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
        seconds = c.get(Calendar.SECOND);
        currentTime = hour + ":" + minute + ":" + seconds;
        btn4txtStart.setText(currentTime);
        requestQueue.add(request);
    }

    public void success(View v) {
        String[] url = {Constant.Webservice.WEBSERVICE_COMMENTS, Constant.Webservice.WEBSERVICE_PHOTOS, Constant.Webservice.WEBSERVICE_TODOS, Constant.Webservice.WEBSERVICE_POSTS};
        new DownloadFileFromURL().execute(url);
    }

    class DownloadFileFromURL extends AsyncTask<String, String, String> {
        String btn1StartTime, btn1EndTime, btn1SaveStartTime, btn1SaveEndTime;
        String btn2StartTime, btn2EndTime, btn2SaveStartTime, btn2SaveEndTime;
        String btn3StartTime, btn3EndTime, btn3SaveStartTime, btn3SaveEndTime;
        String btn4StartTime, btn4EndTime, btn4SaveStartTime, btn4SaveEndTime;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        /**
         * Downloading file in background thread
         */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            String line;
            String response;
            try {

                for (int i = 0; i < f_url.length; i++) {

                    URL url = new URL(f_url[i]);
                    URLConnection connection = url.openConnection();
                    connection.connect();
                    if (f_url[i].equals(Constant.Webservice.WEBSERVICE_COMMENTS)) {
                        publishProgress("btn1StartTime");
                    } else if (f_url[i].equals(Constant.Webservice.WEBSERVICE_PHOTOS)) {
                        publishProgress("btn2StartTime");
                    } else if (f_url[i].equals(Constant.Webservice.WEBSERVICE_TODOS)) {
                        publishProgress("btn3StartTime");
                    } else if (f_url[i].equals(Constant.Webservice.WEBSERVICE_POSTS)) {
                        publishProgress("btn4StartTime");
                    }
                    // input stream to read file - with 8k buffer
                    InputStream input = new BufferedInputStream(
                            url.openStream(), 8192);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line + '\n');

                    }
                    response = stringBuilder.toString();
                    if (f_url[i].equals(Constant.Webservice.WEBSERVICE_COMMENTS)) {
                        publishProgress("btn1SaveStartTime");
                        JSONArray jsonArray = new JSONArray(response);
                        for (int j = 0; j < jsonArray.length() - 1; j++) {
                            JSONObject data = jsonArray.getJSONObject(j);
                            DatabaseModelComments url1 = new DatabaseModelComments();

                            url1.setPostID(data.getString("postId"));
                            url1.setName(data.getString("name"));
                            url1.setEmail(data.getString("email"));
                            url1.setBody(data.getString("body"));
                            url1.save();

                        }

                        publishProgress("btn1SaveEndTime");
                    } else if (f_url[i].equals(Constant.Webservice.WEBSERVICE_PHOTOS)) {
                        publishProgress("btn2SaveStartTime");
                        JSONArray jsonArray = new JSONArray(response);
                        for (int j = 0; j < jsonArray.length(); j++) {
                            JSONObject data = jsonArray.getJSONObject(i);
                            DatabaseModelPhotos url2 = new DatabaseModelPhotos();
                            url2.setAlbumId(data.getString("albumId"));
                            url2.setTitle(data.getString("title"));
                            url2.setThumbnailUrl(data.getString("thumbnailUrl"));
                            url2.setUrl(data.getString("url"));
                            url2.save();
                        }
                        publishProgress("btn2SaveEndTime");
                    } else if (f_url[i].equals(Constant.Webservice.WEBSERVICE_TODOS)) {
                        publishProgress("btn3SaveStartTime");
                        JSONArray jsonArray = new JSONArray(response);
                        for (int j = 0; j < jsonArray.length(); j++) {
                            JSONObject data = jsonArray.getJSONObject(i);
                            DatabaseModelTodos url3 = new DatabaseModelTodos();
                            url3.setUserId(data.getString("userId"));
                            url3.setTitle(data.getString("title"));
                            url3.setCompleted(data.getString("completed"));
                            url3.save();
                        }
                        publishProgress("btn3SaveEndTime");
                    } else if (f_url[i].equals(Constant.Webservice.WEBSERVICE_POSTS)) {
                        publishProgress("btn4SaveStartTime");
                        JSONArray jsonArray = new JSONArray(response);
                        for (int j = 0; j < jsonArray.length(); j++) {
                            JSONObject data = jsonArray.getJSONObject(j);
                            DatabaseModelPosts url4 = new DatabaseModelPosts();
                            url4.setUserId(data.getString("userId"));
                            url4.setTitle(data.getString("title"));
                            url4.setBody(data.getString("body"));
                            url4.save();
                        }
                        publishProgress("btn4SaveEndTime");
                    }

                    System.out.println("Data::" + f_url[i]);

                    // Output stream to write file
                    if (f_url[i].equals(Constant.Webservice.WEBSERVICE_COMMENTS)) {
                        publishProgress("btn1EndTime");
                    } else if (f_url[i].equals(Constant.Webservice.WEBSERVICE_PHOTOS)) {
                        publishProgress("btn2EndTime");
                    } else if (f_url[i].equals(Constant.Webservice.WEBSERVICE_TODOS)) {
                        publishProgress("btn3EndTime");
                    } else if (f_url[i].equals(Constant.Webservice.WEBSERVICE_POSTS)) {
                        publishProgress("btn4EndTime");
                    }
                    byte data[] = new byte[1024];

                    long total = 0;

                    while ((count = input.read(data)) != -1) {
                        total += count;
                        // After this onProgressUpdate will be called


                    }

                    input.close();
                }
            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return null;
        }

        /**
         * Updating progress bar
         */
        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            if (progress[0] == "btn1StartTime") {
                Calendar c = Calendar.getInstance();
                hour = c.get(Calendar.HOUR);
                minute = c.get(Calendar.MINUTE);
                seconds = c.get(Calendar.SECOND);
                btn1StartTime = hour + ":" + minute + ":" + seconds;
                btn1txtStart.setText(btn1StartTime);
            } else if (progress[0] == "btn2StartTime") {
                Calendar c = Calendar.getInstance();
                hour = c.get(Calendar.HOUR);
                minute = c.get(Calendar.MINUTE);
                seconds = c.get(Calendar.SECOND);
                btn2StartTime = hour + ":" + minute + ":" + seconds;
                btn2txtStart.setText(btn2StartTime);
            } else if (progress[0] == "btn3StartTime") {
                Calendar c = Calendar.getInstance();
                hour = c.get(Calendar.HOUR);
                minute = c.get(Calendar.MINUTE);
                seconds = c.get(Calendar.SECOND);
                btn3StartTime = hour + ":" + minute + ":" + seconds;
                btn3txtStart.setText(btn2StartTime);
            } else if (progress[0] == "btn4StartTime") {
                Calendar c = Calendar.getInstance();
                hour = c.get(Calendar.HOUR);
                minute = c.get(Calendar.MINUTE);
                seconds = c.get(Calendar.SECOND);
                btn4StartTime = hour + ":" + minute + ":" + seconds;
                btn4txtStart.setText(btn2StartTime);
            } else if (progress[0] == "btn1EndTime") {
                Calendar c = Calendar.getInstance();
                hour = c.get(Calendar.HOUR);
                minute = c.get(Calendar.MINUTE);
                seconds = c.get(Calendar.SECOND);
                btn1EndTime = hour + ":" + minute + ":" + seconds;
                btn1txtEnd.setText(btn1EndTime);
            } else if (progress[0] == "btn2EndTime") {
                Calendar c = Calendar.getInstance();
                hour = c.get(Calendar.HOUR);
                minute = c.get(Calendar.MINUTE);
                seconds = c.get(Calendar.SECOND);
                btn2EndTime = hour + ":" + minute + ":" + seconds;
                btn2txtEnd.setText(btn2EndTime);
            } else if (progress[0] == "btn3EndTime") {
                Calendar c = Calendar.getInstance();
                hour = c.get(Calendar.HOUR);
                minute = c.get(Calendar.MINUTE);
                seconds = c.get(Calendar.SECOND);
                btn3EndTime = hour + ":" + minute + ":" + seconds;
                btn3txtEnd.setText(btn3EndTime);
            } else if (progress[0] == "btn4EndTime") {
                Calendar c = Calendar.getInstance();
                hour = c.get(Calendar.HOUR);
                minute = c.get(Calendar.MINUTE);
                seconds = c.get(Calendar.SECOND);
                btn4EndTime = hour + ":" + minute + ":" + seconds;
                btn4txtEnd.setText(btn4EndTime);
            } else if (progress[0] == "btn1SaveStartTime") {
                Calendar c = Calendar.getInstance();
                hour = c.get(Calendar.HOUR);
                minute = c.get(Calendar.MINUTE);
                seconds = c.get(Calendar.SECOND);
                btn1SaveStartTime = hour + ":" + minute + ":" + seconds;
                btn1txtSaveStart.setText(btn1SaveStartTime);
            } else if (progress[0] == "btn2SaveStartTime") {
                Calendar c = Calendar.getInstance();
                hour = c.get(Calendar.HOUR);
                minute = c.get(Calendar.MINUTE);
                seconds = c.get(Calendar.SECOND);
                btn2SaveStartTime = hour + ":" + minute + ":" + seconds;
                btn2txtSaveStart.setText(btn2SaveStartTime);
            } else if (progress[0] == "btn3SaveStartTime") {
                Calendar c = Calendar.getInstance();
                hour = c.get(Calendar.HOUR);
                minute = c.get(Calendar.MINUTE);
                seconds = c.get(Calendar.SECOND);
                btn3SaveStartTime = hour + ":" + minute + ":" + seconds;
                btn3txtSaveStart.setText(btn3SaveStartTime);
            } else if (progress[0] == "btn4SaveStartTime") {
                Calendar c = Calendar.getInstance();
                hour = c.get(Calendar.HOUR);
                minute = c.get(Calendar.MINUTE);
                seconds = c.get(Calendar.SECOND);
                btn4SaveStartTime = hour + ":" + minute + ":" + seconds;
                btn4txtSaveStart.setText(btn4SaveStartTime);
            } else if (progress[0] == "btn1SaveEndTime") {
                Calendar c = Calendar.getInstance();
                hour = c.get(Calendar.HOUR);
                minute = c.get(Calendar.MINUTE);
                seconds = c.get(Calendar.SECOND);
                btn1SaveEndTime = hour + ":" + minute + ":" + seconds;
                btn1txtSaveEnd.setText(btn1SaveEndTime);
            } else if (progress[0] == "btn2SaveEndTime") {
                Calendar c = Calendar.getInstance();
                hour = c.get(Calendar.HOUR);
                minute = c.get(Calendar.MINUTE);
                seconds = c.get(Calendar.SECOND);
                btn2SaveEndTime = hour + ":" + minute + ":" + seconds;
                btn2txtSaveEnd.setText(btn2SaveEndTime);
            } else if (progress[0] == "btn3SaveEndTime") {
                Calendar c = Calendar.getInstance();
                hour = c.get(Calendar.HOUR);
                minute = c.get(Calendar.MINUTE);
                seconds = c.get(Calendar.SECOND);
                btn3SaveEndTime = hour + ":" + minute + ":" + seconds;
                btn3txtSaveEnd.setText(btn3SaveEndTime);
            } else if (progress[0] == "btn4SaveEndTime") {
                Calendar c = Calendar.getInstance();
                hour = c.get(Calendar.HOUR);
                minute = c.get(Calendar.MINUTE);
                seconds = c.get(Calendar.SECOND);
                btn4SaveEndTime = hour + ":" + minute + ":" + seconds;
                btn4txtSaveEnd.setText(btn4SaveEndTime);
            }

        }

        /**
         * After completing background task Dismiss the progress dialog
         **/
        @Override
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after the file was downloaded

        }

    }

}
