package com.example.msi.myapplication.http;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.os.SystemClock.sleep;

/**
 * Created by CMJ on 2017/6/11.
 */

public class HttpUtil {

    public static String getData(String relativePath) throws Exception{
        try {
            URL url=new URL(HttpPath.getConnectPath(relativePath));
            HttpURLConnection conn=(HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");

            if(conn.getResponseCode()==200){
                System.out.println("------------------链接成功-----------------");
                InputStream ips=conn.getInputStream();
                byte[] data=read(ips);
                String str=new String(data);
//                String str="没有读取服务器数据哦!！";
                return str;
            }else{
                System.out.println("------------------链接失败-----------------");
                return "网络错误 ：conn.getResponseCode() =";
            }
        } catch (MalformedURLException e) {
            return "HttpService.getGamesData()发生异常！ "+e.getMessage();
        }

    }

    /*
    * 读取流中的数据
    * */
    public static byte[] read(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream=new ByteArrayOutputStream();
        byte[] buffer=new byte[1024];
        int len=0;
        while((len=inStream.read(buffer))!=-1){
            outStream.write(buffer,0,len);
        }
        inStream.close();
        return outStream.toByteArray();
    }

}
