package com.example.object.impl;

import android.R.string;

import com.example.object.NodeClass;

public class TrappedNodeClass extends NodeClass{
	private boolean highLightBool;
	private int skipNum;
	private string highLightID;
	
	void setHighLightBool(boolean highLightBool){
		this.highLightBool = highLightBool;
	}
	
	boolean getHighLightBool(){
		return this.highLightBool;
	}
	
	void setSkipNum(int skipNum){
		this.skipNum = skipNum;
	}
	
	int getSkipNum(){
		return this.skipNum;
	}
	
	void setHighLightID(string highLightID){
		this.highLightID = highLightID;
	}
	
	string getHighLightID(){
		return this.highLightID;
	}
}
