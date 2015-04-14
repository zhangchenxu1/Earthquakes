package com.example.messageoperator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import com.example.common.messagetool.Info;

public class SdReaderWriter {
	private final int VAR_NUM = 1 ;
	private Context mContext;
	public SdReaderWriter(Context mContext) {
		this.mContext = mContext ;
	}
	
	public void writeToSd(Vector<Info> vecInfo) {
		File fDataFile = new File(Environment.getExternalStorageDirectory(),"EarthquakeData") ;
	//	Toast.makeText(mContext, flDataFile.getAbsolutePath(),Toast.LENGTH_LONG).show() ;
		try {
			if(!fDataFile.exists()){
			//	Toast.makeText(mContext, "FIle doesn't exist!",Toast.LENGTH_LONG).show() ;
				if(fDataFile.createNewFile())
					Toast.makeText(mContext, "Created!",Toast.LENGTH_LONG).show() ;
				else {
					Toast.makeText(mContext, "Can't creat!",Toast.LENGTH_LONG).show() ;
				}
			}
			FileWriter fWriter = new FileWriter(fDataFile,true) ;
			Iterator<Info> iter = vecInfo.iterator();
			while(iter.hasNext())
				fWriter.write(infoToString(iter.next()));
			fWriter.flush();
		//	Toast.makeText(mContext, "Write done!", Toast.LENGTH_LONG) ;
			fWriter.close(); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		//	Toast.makeText(mContext, "Can't open "+flDataFile.getAbsolutePath(),Toast.LENGTH_LONG).show() ;
		//	System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		//	System.exit(0);
		}
		
	}
	public Vector<Info> readFromSd(){
		File fDataFile = new File(Environment.getExternalStorageDirectory(),
				"EarthquakeData");
		String strInfo ;
		Vector<Info> vecInfo = new Vector<Info>() ;
		FileReader fReader;
		try {
			fReader = new FileReader(fDataFile);
			BufferedReader bfReader = new BufferedReader(fReader);
			while( (strInfo = bfReader.readLine() )!=null )
				vecInfo.add(strToInfo(strInfo)) ;
			bfReader.close();
			fReader.close(); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//FIXME Info属性确定后，这里都需要进行更改
	private Info  strToInfo(String str) {
		String[] vars = new String[VAR_NUM] ;
		str.split("\t") ;
		return new Info(Integer.valueOf(vars[0])) ;
	}
	
	//FIXME Info属性确定后，这里都需要进行更改
	private String infoToString(Info info){
		StringBuffer bufStrInfo = new StringBuffer();
		bufStrInfo.append(info.getId()+"\t") ;
		return bufStrInfo.toString() ;
	}
}
