package com.example.common.messagetool;

import java.util.Vector;

public class Message {
	private int nid, ndis;
	private Vector<Info> infos;

	public Message(int nid, int ndis) {
		this.infos = new Vector<Info>();
		this.nid = nid;
		this.ndis = ndis;
	}

	public int getNid() {
		return nid;
	}

	public void setNid(int nid) {
		this.nid = nid;
	}

	public int getNdis() {
		return ndis;
	}

	public void setNdis(int ndis) {
		this.ndis = ndis;
	}

	public Vector<Info> getInfos() {
		return infos;
	}

	public void setInfos(Vector<Info> infos) {
		this.infos = infos;
	}
	
}
