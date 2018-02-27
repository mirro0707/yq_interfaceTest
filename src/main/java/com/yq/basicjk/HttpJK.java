package com.yq.basicjk;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpJK {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://bbs.51testing.com");
//        URLConnection urlConnection = url.openConnection();
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setDoInput(true);
        InputStream is = httpURLConnection.getInputStream();
//        InputStreamReader reader = new InputStreamReader(is, "UTF-8");
        Reader reader = new InputStreamReader(is, "UTF-8");
        int c = -1;
        while ((c=reader.read())!=-1){
//            System.out.println((char)c);
            System.out.print((char)c);
        }

    }
}
