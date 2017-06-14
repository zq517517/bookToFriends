package com.example.msi.myapplication.http;

/**
 * Created by CMJ on 2017/6/10.
 */

public class HttpPath {
    private static final String IP="http://172.26.224.192/";
    public static String getConnectPath(String relativePath){
        return IP + relativePath;
    }
}
