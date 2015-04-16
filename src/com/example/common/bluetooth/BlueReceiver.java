package com.example.common.bluetooth;

import java.util.Vector;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BlueReceiver extends BroadcastReceiver {
	BluetoothDevice bluetoothDevice;
	BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if (BluetoothDevice.ACTION_FOUND.equals(action)) {
			// ���ֵ�Զ�������豸
			bluetoothDevice = intent
					.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

		}
	}

	// ������
	public void turnOnBluetooth() {
		bluetoothAdapter.enable();
	}

	// �ر�����
	public void turnOffBluetooth() {
		bluetoothAdapter.disable();
	}

	// �޸�Զ�������豸���豸��
	public void changeBluetoothDeviceName(String newName) {
		bluetoothAdapter.setName(newName);
	}

	// ���Զ�������豸���豸��Ϣ���豸����mac��ַ
	public Vector<String> getBluetoothDeviceInfo() {
		Vector<String> tempVector = new Vector<String>();
		String tempString = bluetoothDevice.getName();
		tempVector.add(tempString);
		tempString = bluetoothDevice.getAddress();
		tempVector.add(tempString);
		return tempVector;
	}
}
