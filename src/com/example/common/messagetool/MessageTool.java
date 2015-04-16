package com.example.common.messagetool;

import java.io.UnsupportedEncodingException;

/*
 * InfoToolClass���������ṩ���ֶ������ݴ���ķ���
 * deCodeString�����������Խ��յ���Զ���豸�����д�������Ϣ���򴢴�
 * enCodeString����������������Ҫת�������ݰ�һ���Ĺ�����б��룬��󽫱�õ����ݴ��������������޸��豸��
 * sortString������ͨ��ʹ���������ȼ��㷨������Ҫת������Ϣ�������У�ȷ��ÿ����Ϣ�����ȼ��������վ���ת����Щ���ݳ�ȥ
 * 
 * */

public class MessageTool {
	private byte[] res = new byte[2];// �����ֽ�
	private int bytepos = 0;// ��ǰbyteλ
	private int bitpos = 0;// ��ǰbitλ
	private int idsize = 5;
	private int dissize = 4;
	private int infosize = idsize;
	private int numsize = 8;

	// ��ȡ��������
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

	// д����������
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

	// ��id
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

	// дid
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

	// ������ؽڵ�����
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

	// д����ؽڵ�����
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

	// ����
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

	// ����
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