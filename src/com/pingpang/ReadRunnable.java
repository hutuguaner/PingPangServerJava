package com.pingpang;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReadRunnable implements Runnable{

	private Socket clientSocket;
	
	
	
	public ReadRunnable(Socket clientSocket) {
		super();
		this.clientSocket = clientSocket;
	}


	private BufferedReader bufferedReader;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String readline = null;
		while(true) {
			try {
				readline = bufferedReader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(readline!=null&&!readline.trim().equals("")&&!readline.trim().equals("null")) {
				System.out.println("服务器收到数据： "+readline);
			}
		}
		
	}

}
