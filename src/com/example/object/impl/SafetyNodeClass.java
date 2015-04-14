package com.example.object.impl;

import android.R.string;

import com.example.object.NodeClass;

public class SafetyNodeClass extends NodeClass{
	private string location;

	void setLocation(string location) {
		this.location = location;
	}

	string getLocation() {
		return this.location;
	}

}
