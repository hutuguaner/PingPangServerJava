package com.pingpang;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;



public class Main {


	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		System.out.println("app start ......");

		hehe();


	}

	private static void hehe() throws Exception {
		ServerSocket serverSocket = new ServerSocket(8091);
		while(true) {
			Socket socket = serverSocket.accept();
			MyReceiveSocketThread myReceiveSocketThread = new MyReceiveSocketThread(socket);
			myReceiveSocketThread.start();
		}

	}
	
	
	

}
