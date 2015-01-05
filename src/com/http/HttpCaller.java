package com.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

import com.logger.NssLogger;

/**
 * @Project: ThinkpageAPI
 * @Author: hectorhua
 * @Date: 2014
 */

public class HttpCaller {
	private String proxyHost;
	private String proxyPort;
	private boolean isProxyEnabled;
	
	public HttpCaller(String proxyHost, String proxyPort, boolean isProxyEnabled){
		this.proxyHost = proxyHost;
		this.proxyPort = proxyPort;
		this.isProxyEnabled = isProxyEnabled;
		/*System.setProperty("https.proxyHost", proxyHost); 
		System.setProperty("https.proxyPort", proxyPort);
		System.setProperty("https.proxySet", "true");*/
	}
	
	public String getURLContent(String urlStr, String encode) throws Exception{
		
		if(isProxyEnabled){
			Properties prop = System.getProperties();      
			prop.setProperty("https.proxyHost", proxyHost);     
			prop.setProperty("https.proxyPort", proxyPort);
		}
		
		StringBuilder contentBuf = new StringBuilder(1024*100); 
		/*InputStreamReader input;
		URL url = new URL(urlStr);  
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection(); 
		httpConn.setConnectTimeout(1000);
		httpConn.setReadTimeout(1000);*/
		
		try {  
            URL urlObject = new URL(urlStr);  
            URLConnection uc = urlObject.openConnection();  
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(), encode));  
            String inputLine = null;  
            while ( (inputLine = in.readLine()) != null) {  
            	contentBuf.append(inputLine);  
            }
            uc.setConnectTimeout(1000);
    		uc.setReadTimeout(1000);
            in.close();  
        } catch (MalformedURLException e) { 
        	NssLogger.error("******"+urlStr+" Fail to connect!******");
        	return "-1";   
        } catch (IOException e) {
        	NssLogger.error("******"+urlStr+" Fail to connect!******");
        	return "-1";  
        }  

		/*try{
			httpConn.connect();
			input = new InputStreamReader(httpConn.getInputStream(), encode);
		} catch(Exception e){
			return "-1";
		}
		BufferedReader bufReader = new BufferedReader(input); 
		String line = "";  
		while ((line = bufReader.readLine()) != null) {  
			contentBuf.append(line);  
		}  
		bufReader.close();
		httpConn.disconnect();*/
		return new String(contentBuf);
	}
}