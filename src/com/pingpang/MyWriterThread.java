package com.pingpang;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;

public class MyWriterThread extends Thread{
	
	private BufferedWriter writer;

	public MyWriterThread(BufferedWriter writer) {
		super();
		this.writer = writer;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		Random random = new Random();
		while(true) {
			String msg = "hello client,i am server "+random.nextInt()+"\n";
			try {
				writer.write(msg);
				writer.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
