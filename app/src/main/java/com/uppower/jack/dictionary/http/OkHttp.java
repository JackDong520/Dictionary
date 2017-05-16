package com.uppower.jack.dictionary.http;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 72408 on 2017/3/16.
 */

public class OkHttp {

    private static OkHttpClient client;

    static {
        client = new OkHttpClient();
    }

    public String sendGetRequest(String url, List<Object> objects, String apiKey) throws IOException {

        if (objects == null) {
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            return response.body().string();
        } else {
            return null;
        }
    }

    public String sendPostRequest(String url, String word) throws IOException {

        FormBody body = new FormBody.Builder().add("word", "take").build();

        Request request = new Request.Builder().url(url).post(body).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    @Test
    public void test() {
        String url = "http://localhost:8080/getWord";
        try {
            System.out.println(new OkHttp().sendPostRequest(url,"take"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}










