package edu.sabanciuniv.operatingsystemsexample;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class NewsRepo {



    public void getAllData(ExecutorService srv, Handler uiHandler) {
        srv.execute(() -> {
            try {
                URL url = new URL("http://10.3.0.14:8080/newsapp/getall");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                String jsonString = buffer.toString();

                JSONObject jsonObject = new JSONObject(jsonString);
                JSONArray itemsArray = jsonObject.getJSONArray("items");
                List<News> data = new ArrayList<>();

                for (int i = 0; i < itemsArray.length(); i++) {
                    JSONObject itemObject = itemsArray.getJSONObject(i);
                    News opSys = new News(
                            itemObject.getInt("id"),
                            itemObject.getString("title"),
                            itemObject.getString("text"),
                            itemObject.getString("date"),
                            itemObject.getString("image"),
                            itemObject.getString("categoryName")
                    );
                    data.add(opSys);
                }

                Message msg = new Message();
                msg.obj = data;
                uiHandler.sendMessage(msg);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }

    public void getAllCatagory(ExecutorService srv, Handler uiHandler) {
        srv.execute(() -> {
            try {
                URL url = new URL("http://10.3.0.14:8080/newsapp/getallnewscategories");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                String jsonString = buffer.toString();

                JSONObject jsonObject = new JSONObject(jsonString);
                JSONArray itemsArray = jsonObject.getJSONArray("items");
                List<Catagories> data = new ArrayList<>();

                for (int i = 0; i < itemsArray.length(); i++) {
                    JSONObject itemObject = itemsArray.getJSONObject(i);
                    Catagories catagories = new Catagories(
                            itemObject.getInt("id"),
                            itemObject.getString("name")
                    );
                    data.add(catagories);
                }

                Message msg = new Message();
                msg.obj = data;
                uiHandler.sendMessage(msg);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }

    public void getDataByCatagoryId(ExecutorService srv, Handler uiHandler,int id){


        srv.execute(()->{
            try {
                URL url = new URL("http://10.3.0.14:8080/newsapp/getbycategoryid/" + String.valueOf(id));
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                String jsonString = buffer.toString();

                JSONObject jsonObject = new JSONObject(jsonString);
                JSONArray itemsArray = jsonObject.getJSONArray("items");
                List<News> data = new ArrayList<>();

                for (int i = 0; i < itemsArray.length(); i++) {
                    JSONObject itemObject = itemsArray.getJSONObject(i);
                    News opSys = new News(
                            itemObject.getInt("id"),
                            itemObject.getString("title"),
                            itemObject.getString("text"),
                            itemObject.getString("date"),
                            itemObject.getString("image"),
                            itemObject.getString("categoryName")
                    );
                    data.add(opSys);
                }

                Message msg = new Message();
                msg.obj = data;
                uiHandler.sendMessage(msg);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });

    }





    public void getDataById(ExecutorService srv, Handler uiHandler,int id){


        srv.execute(()->{
            try {
                URL url = new URL("http://10.3.0.14:8080/newsapp/getnewsbyid/" + String.valueOf(id));
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                String jsonString = buffer.toString();

                JSONObject jsonObject = new JSONObject(jsonString);
                JSONArray itemsArray = jsonObject.getJSONArray("items");
                List<News> data = new ArrayList<>();

                for (int i = 0; i < itemsArray.length(); i++) {
                    JSONObject itemObject = itemsArray.getJSONObject(i);
                    News opSys = new News(
                            itemObject.getInt("id"),
                            itemObject.getString("title"),
                            itemObject.getString("text"),
                            itemObject.getString("date"),
                            itemObject.getString("image"),
                            itemObject.getString("categoryName")
                    );
                    data.add(opSys);
                }

                Message msg = new Message();
                msg.obj = data;
                uiHandler.sendMessage(msg);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });

    }

    public void downloadImage(ExecutorService srv, Handler uiHandler,String path){
        srv.execute(()->{
            try {
                URL url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();

                Bitmap bitmap =  BitmapFactory.decodeStream(conn.getInputStream());

                Message msg = new Message();
                msg.obj = bitmap;
                uiHandler.sendMessage(msg);


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });


    }

    public void getComments(ExecutorService srv, Handler uiHandler,int id){


        srv.execute(()->{
            try {
                URL url = new URL("http://10.3.0.14:8080/newsapp/getcommentsbynewsid/" + String.valueOf(id));
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                String jsonString = buffer.toString();

                JSONObject jsonObject = new JSONObject(jsonString);
                JSONArray itemsArray = jsonObject.getJSONArray("items");
                List<Comments> data = new ArrayList<>();

                for (int i = 0; i < itemsArray.length(); i++) {
                    JSONObject itemObject = itemsArray.getJSONObject(i);
                    Comments comments = new Comments(
                            itemObject.getInt("id"),
                            itemObject.getString("news_id"),
                            itemObject.getString("text"),
                            itemObject.getString("name")
                    );
                    data.add(comments);
                }

                Message msg = new Message();
                msg.obj = data;
                uiHandler.sendMessage(msg);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });

    }

    public void postComment(ExecutorService srv, Handler uiHandler, String name, String text, String news_id){

        srv.execute(()->{
            try {
                URL url = new URL("http://10.3.0.14:8080/newsapp/savecomment");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setDoInput(true);
                conn.setDoOutput(true);

                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/JSON");

                JSONObject outputData = new JSONObject();

                outputData.put("name", name);
                outputData.put("text", text);
                outputData.put("news_id", news_id);

                BufferedOutputStream writer = new BufferedOutputStream(conn.getOutputStream());

                writer.write(outputData.toString().getBytes(StandardCharsets.UTF_8));
                writer.flush();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder buffer  = new StringBuilder();

                String line = "";

                while((line=reader.readLine()) != null){

                    buffer.append(line);
                }

                JSONObject retVal = new JSONObject(buffer.toString());

                /*
                PostComment data = new PostComment(retVal.getString("name"), retVal.getString("text"), retVal.getString("news_id"));
                */
                Message msg = new Message();
                msg.obj = "SUCCESS";
                uiHandler.sendMessage(msg);

                conn.disconnect();



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });

    }




}
