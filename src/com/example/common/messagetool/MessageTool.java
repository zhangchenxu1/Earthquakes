package com.example.common.messagetool;

import java.io.UnsupportedEncodingException;

/*
 * InfoToolClass类是用来提供各种对于数据处理的方法
 * deCodeString方法是用来对接收到的远程设备名进行处理，将信息有序储存
 * enCodeString方法是用来将我们要转发的数据按一定的规则进行编码，最后将编好的数据传给蓝牙，用于修改设备名
 * sortString方法是通过使用数据优先级算法对我们要转发的信息进行排列，确定每个信息的优先级并且最终决定转发哪些数据出去
 * 
 * */

public class MessageTool {
	private byte[] res = new byte[2];// 数据字节
	private int bytepos = 0;// 当前byte位
	private int bitpos = 0;// 当前bit位
	private int idsize = 5;
	private int dissize = 4;
	private int infosize = idsize;
	private int numsize = 8;

	// 读取数据组数
	int readnum() {
		int ans = 0;
		for (int i = 0; i < numsize; i++) {
			if (bitpos >= 7) {
				bitpos = 0;
				bytepos++;
			}
			if ((res[bytepos] & (1 << bitpos)) != 0)
				ans |= (1 << i);
			bitpos++;
		}
		return ans;
	}

	// 写入数据组数
	void writenum(int num) {
		for (int i = 0; i < numsize; i++) {
			if (bitpos >= 7) {
				bitpos = 0;
				bytepos++;
			}
			if ((num & (1 << i)) != 0) {
				res[bytepos] |= (1 << bitpos);
			}
			bitpos++;
		}
	}

	// 读id
	int readid() {
		int ans = 0;
		for (int i = 0; i < idsize; i++) {
			if (bitpos >= 7) {
				bitpos = 0;
				bytepos++;
			}
			if ((res[bytepos] & (1 << bitpos)) != 0)
				ans |= (1 << i);
			bitpos++;
		}
		return ans;
	}

	// 写id
	void writeid(int id) {
		// System.out.println(bytepos+" "+bitpos);
		for (int i = 0; i < idsize; i++) {
			if (bitpos >= 7) {
				bitpos = 0;
				bytepos++;
			}
			if ((id & (1 << i)) != 0) {
				res[bytepos] |= (1 << bitpos);
			}
			bitpos++;
		}
	}

	// 读离高曝节点跳数
	int readdis() {
		int ans = 0;
		for (int i = 0; i < dissize; i++) {
			if (bitpos >= 7) {
				bitpos = 0;
				bytepos++;
			}
			if ((res[bytepos] & (1 << bitpos)) != 0)
				ans |= (1 << i);
			bitpos++;
		}
		return ans;
	}

	// 写离高曝节点跳数
	void writedis(int dis) {
		for (int i = 0; i < dissize; i++) {
			if (bitpos >= 7) {
				bitpos = 0;
				bytepos++;
			}
			if ((dis & (1 << i)) != 0) {
				res[bytepos] |= (1 << bitpos);
			}
			bitpos++;
		}
	}

	// 解码
	public Message decode(String mess) throws UnsupportedEncodingException {

		bytepos = 0;
		bitpos = 0;
		res = mess.getBytes("utf-8");
		int nid = readid();
		int ndis = readdis();
		Message ans = new Message(nid, ndis);
		int num = readnum();
		for (int i = 0; i < num; i++) {
			ans.getInfos().add(new Info(readid()));
		}
		return ans;
	}

	// 编码
	public String encode(Message mess) throws UnsupportedEncodingException {
		int num = mess.getInfos().size();
		System.out.println(num);
		bytepos = 0;
		bitpos = 0;
		System.out.println((num * infosize + idsize + dissize + 6) / 7);
		res = new byte[(num * infosize + idsize + dissize + numsize + 6) / 7];
		writeid(mess.getNid());
		writedis(mess.getNdis());
		writenum(num);
		for (int i = 0; i < num; i++) {
			writeid(mess.getInfos().get(i).getId());
		}
		return new String(res, "utf-8");
	}

}