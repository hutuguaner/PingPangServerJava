package com.pingpang;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MyReceiveSocketThread extends Thread {

	private Socket client;

	public MyReceiveSocketThread(Socket client) {
		super();
		this.client = client;
	}

	private BufferedReader reader;
	private BufferedWriter writer;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
			writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			MyReaderThread myReaderThread = new MyReaderThread(reader);
			MyWriterThread myWriterThread = new MyWriterThread(writer);
			myReaderThread.start();
			myWriterThread.start();
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
