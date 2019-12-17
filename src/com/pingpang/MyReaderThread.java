package com.pingpang;

import java.io.BufferedReader;
import java.io.IOException;

public class MyReaderThread extends Thread{
	
	private BufferedReader reader;

	public MyReaderThread(BufferedReader reader) {
		super();
		this.reader = reader;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		while(true) {
			String readline = null;
			try {
				readline = reader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(readline!=null&&!readline.trim().equals("")&&!readline.trim().equals("null")) {}
			System.out.println(" 服务器收到的数据： "+readline);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
