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
			// 发现的远程蓝牙设备
			bluetoothDevice = intent
					.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

		}
	}

	// 打开蓝牙
	public void turnOnBluetooth() {
		bluetoothAdapter.enable();
	}

	// 关闭蓝牙
	public void turnOffBluetooth() {
		bluetoothAdapter.disable();
	}

	// 修改远程蓝牙设备的设备名
	public void changeBluetoothDeviceName(String newName) {
		bluetoothAdapter.setName(newName);
	}

	// 获得远程蓝牙设备的设备信息－设备名和mac地址
	public Vector<String> getBluetoothDeviceInfo() {
		Vector<String> tempVector = new Vector<String>();
		String tempString = bluetoothDevice.getName();
		tempVector.add(tempString);
		tempString = bluetoothDevice.getAddress();
		tempVector.add(tempString);
		return tempVector;
	}
}
