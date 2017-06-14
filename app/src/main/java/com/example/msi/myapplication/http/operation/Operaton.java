package com.example.msi.myapplication.http.operation;
import com.example.msi.myapplication.http.ConnNet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;




public class Operaton
{

	public String login(String url,String username,String password)
	{
		String result = null;
		ConnNet connNet=new ConnNet();
		// NameValuePair: apache的一个项目HttpClient，里面的一个类，用于FORM数据
		List<NameValuePair> params=new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("username", username));
		params.add(new BasicNameValuePair("password", password));
		try {
			HttpEntity entity=new UrlEncodedFormEntity(params, HTTP.UTF_8);
			HttpPost httpPost=connNet.gethttPost(url);
			System.out.println(httpPost.toString());
			httpPost.setEntity(entity);
			HttpClient client=new DefaultHttpClient();
			HttpResponse httpResponse=client.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode()==HttpStatus.SC_OK)
			{
				result=EntityUtils.toString(httpResponse.getEntity(), "utf-8");
			} else {
				result="登录失败";
			}
		} catch (UnsupportedEncodingException e) {
			result="登录失败";
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			result="登录失败";
			e.printStackTrace();
		} catch (ParseException e) {
			result="登录失败";
			e.printStackTrace();
		} catch (IOException e) {
			result="登录失败";
			e.printStackTrace();
		}
		return result;
	}
	public String checkusername(String url,String username) {
		String result=null;
		ConnNet connNet=new ConnNet();
		List<NameValuePair> params=new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("username", username));
		try {
			HttpEntity entity=new UrlEncodedFormEntity(params, HTTP.UTF_8);
			HttpPost httpPost=connNet.gethttPost(url);
			System.out.println(httpPost.toString());
			httpPost.setEntity(entity);
			HttpClient client=new DefaultHttpClient();
			HttpResponse httpResponse=client.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode()==HttpStatus.SC_OK) {
				result=EntityUtils.toString(httpResponse.getEntity(), "utf-8");
				System.out.println("resu"+result);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "解码错误";
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			return "客户端端口错误";
		} catch (ParseException e) {
			e.printStackTrace();
			return "解析错误";
		} catch (IOException e) {
			e.printStackTrace();
			return "IO错误";
		}
		return result;
	}

	public String UpData(String uripath,String jsonString) {
		String result = null;
		List<NameValuePair> list=new ArrayList<NameValuePair>();
		NameValuePair nvp=new BasicNameValuePair("jsonstring", jsonString);
		list.add(nvp);
		ConnNet connNet=new ConnNet();
		HttpPost httpPost=connNet.gethttPost(uripath);
		try {
			HttpEntity entity = new UrlEncodedFormEntity(list, HTTP.UTF_8);
			//此句必须加上否则传到客户端的中文将是乱码
			httpPost.setEntity(entity);
			HttpClient client=new DefaultHttpClient();
			HttpResponse httpResponse=client.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode()==200) {
				result=EntityUtils.toString(httpResponse.getEntity(), "utf-8");
				System.out.println("resu"+result);
			} else {
				result="注册失败";
			}
		} catch (UnsupportedEncodingException e) {
			result="注册失败";
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			result="注册失败";
			e.printStackTrace();
		} catch (ParseException e) {
			result="注册失败";
			e.printStackTrace();
		} catch (IOException e) {
			result="注册失败";
			e.printStackTrace();
		}
		return result;
	}

}