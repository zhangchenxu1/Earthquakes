package com.example.object;

import android.R.string;

public class NodeClass {
	private string ID;
	private string Data;
	private int Emergy;
	private int Kind;

	void setID(string ID) {
		this.ID = ID;
	}

	string getID() {
		return this.ID;
	}

	string recieveData(string data) {
		return this.Data;
	}

	void handleData(string data) {

	}

	string sendData(string data) {
		return this.Data;
	}

	int getEmergy() {
		return this.Emergy;
	}

	void setKind(int kind) {
		this.Kind = kind;
	}

	int getKind() {
		return this.Kind;
	}

}
