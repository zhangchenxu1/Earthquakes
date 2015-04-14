package com.example.messageoperator;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Vector;

import android.content.Context;

import com.example.common.messagetool.Info;
import com.example.common.messagetool.Message;
import com.example.common.messagetool.MessageTool;

public class MessageOperator {
	private final int MAX_INFO_NUM= 10 ;
	private Context mContext;
	private MessageTool messageTool = new MessageTool() ;
	private SdReaderWriter sdReaderWriter = new SdReaderWriter(mContext) ;
	private Vector<Info> vecInfo ;
	private Message message ;
	
	void receive(){
		//调用蓝牙的接收
		String argStrInfo = null ;
		try {
			message = messageTool.decode(argStrInfo) ;
			vecInfo = message.getInfos() ;
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
	//	vecInfo.addAll(message.infos);
	}

	void handle(){
		vecInfo.addAll(sdReaderWriter.readFromSd()) ;
		calPriority(vecInfo);
		sortByPriority(vecInfo) ;
		sdReaderWriter.writeToSd(vecInfo);
		vecInfo.subList(MAX_INFO_NUM+1,vecInfo.size()-1).clear() ;
	}
	
	void send(){
		try {
			String strInfo = messageTool.encode(message) ;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//FIXME 调用蓝牙的发送
	}
	
	//按优先级排序
	void sortByPriority(Vector<Info> paraVecInfo){
		Collections.sort(paraVecInfo,new Comparator<Info>() {
			@Override
			public int compare(Info lhs, Info rhs) {
				return rhs.getPriority() - lhs.getPriority() ;
			}
		});
	}
	
	//对vector里的每条信息计算其优先级
	void calPriority(Vector<Info> paraVecInfo){
		Info info = null ;
		Iterator<Info> iter = paraVecInfo.iterator() ;
		while(iter.hasNext()){
			info = iter.next() ;
			//TODO 计算info的优先级
			
			
		}
	}
	
}
