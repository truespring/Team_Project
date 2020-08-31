package com.allWebtoon.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@WebServlet("/naverAPI")
public class NaverAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String clientId = "gtb_8Ij5V31vLTCJA7F3";
		String clientSecret = "8dYiJWFqmT"; 
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		String redirectURI = URLEncoder.encode("http://127.0.0.1:8089/naverAPI","UTF-8");
				
		StringBuffer apiURL = new StringBuffer();
		apiURL.append("https://nid.naver.com/oauth2.0/token?grant_type=authorization_code");
		apiURL.append("&client_id=" + clientId);
		apiURL.append("&client_secret=" + clientSecret);
		apiURL.append("&redirect_uri=" + redirectURI);
		apiURL.append("&code=" + code);
		apiURL.append("&state=" + state);
		String access_token = "";
		String refresh_token = ""; //나중에 이용합시다
				
		try { 
			  URL url = new URL(apiURL.toString());
		      HttpURLConnection con = (HttpURLConnection)url.openConnection();
		      con.setRequestMethod("GET");
		      int responseCode = con.getResponseCode();
		      BufferedReader br;
		      System.out.println("responseCode : "+responseCode);
		      
		      if(responseCode==200) {
		    	System.out.println("정상 호출");
		        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		      } else {  // 에러 발생
		    	System.out.println("에러 호출");
		        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		      }
		      
		      String inputLine;
		      StringBuffer res = new StringBuffer();
		      
		      while ((inputLine = br.readLine()) != null) {
		        res.append(inputLine);
		        System.out.println("inputLine : "+inputLine);
		      }
		      br.close();
		      if(responseCode==200) {
	    	  	System.out.println("res.toString() : "+res.toString());
	    		JSONParser parsing = new JSONParser();
	    		Object obj = parsing.parse(res.toString());
	    		JSONObject jsonObj = (JSONObject)obj;
	    			        
	    		access_token = (String)jsonObj.get("access_token");
	    		refresh_token = (String)jsonObj.get("refresh_token");
		      }
		    } catch (Exception e) {
		      System.out.println(e);
		    }
		if(access_token != null) { // access_token을 잘 받아왔다면
			try {
				String apiurl = "https://openapi.naver.com/v1/nid/me";
				URL url = new URL(apiurl);
				HttpURLConnection con = (HttpURLConnection)url.openConnection();
				System.out.println("con : "+con);
				con.setRequestMethod("POST");
				con.setRequestProperty("Authorization", "Bearer " + access_token);
				
				int responseCode = con.getResponseCode();
				System.out.println("responseCode : " + responseCode);
				
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				
				String line = "";
		        String result = "";
		        
		        while ((line = br.readLine()) != null) {
		            result += line;
		        }
		        System.out.println("response body : " + result);
		        
				JsonParser parser = new JsonParser();
				JsonElement element = parser.parse(result);
				
				JsonObject response1 = element.getAsJsonObject().get("response").getAsJsonObject();
				 
				System.out.println("properties : "+response1);
				
				
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
		}
		response.sendRedirect("/home");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
