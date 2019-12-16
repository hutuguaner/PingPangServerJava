package com.pingpang;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int port = 555333;
		ServerSocket serverSocket = new ServerSocket(port);
		
		Socket clientSocket = serverSocket.accept();
		MyReceiveClientThread myReceiveClientThread = new MyReceiveClientThread(clientSocket);
		myReceiveClientThread.start();
		
	}
	
	
	private static class MyReceiveClientThread extends Thread{
		
		Socket clientSocket;

		public MyReceiveClientThread(Socket clientSocket) {
			super();
			this.clientSocket = clientSocket;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
		}
	}

}
