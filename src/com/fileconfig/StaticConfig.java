package com.fileconfig;

import java.io.*;
import java.util.*;

/**
 * @Project: ThinkpageAPI
 * @Author: hectorhua
 * @Date: 2014
 */

public class StaticConfig {

	public StaticConfig(String filename){
		this._filename = filename;
		parse();
	}
	

	public void parse(){
		File file = new File(_filename);
	    InputStream in = null;
	   
	    try {
	      in = new FileInputStream(file);
	      _prop.load(in);
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	}
	

	public void parse(String filename){
		File file = new File(filename);
	    InputStream in = null;
	   
	    try {
	      in = new FileInputStream(file);
	      _prop.load(in);
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	}
	

	public String getProperty(String key) throws Exception{
		return _prop.getProperty(key);
	}
	
	
	private String _filename;
	private Properties _prop = new Properties();
}
