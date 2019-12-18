package com.pingpang;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Random;

public class WriteRunnable implements Runnable {

	private Socket clientSocket;

	public WriteRunnable(Socket clientSocket) {
		super();
		this.clientSocket = clientSocket;
	}

	private BufferedWriter bufferedWriter;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
			Random random = new Random();
			while (true) {
				String msg = "hello client,i am server!" + random.nextInt() + "\n";
				bufferedWriter.write(msg);
				bufferedWriter.flush();
				Thread.sleep(1000);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
