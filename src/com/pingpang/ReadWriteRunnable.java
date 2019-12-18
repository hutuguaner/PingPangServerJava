package com.pingpang;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

import com.pingpang.bean.MsgBean;

public class ReadWriteRunnable implements Runnable {

	private Socket client;

	public ReadWriteRunnable(Socket client) {
		super();
		this.client = client;
		msgQueue = new LinkedList<MsgBean>();
	}

	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;

	private Queue<MsgBean> msgQueue;

	public void addMsgThatWaitToSend(MsgBean msg) {
		msgQueue.offer(msg);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			String readline = null;
			while (true) {
				readline = bufferedReader.readLine();
				if (readline != null && !readline.trim().equals("") && !readline.trim().equals("null")) {
					System.out.println("服务器收到数据： " + readline);
					
				}

				String msg = "hello client,i am server\n";
				bufferedWriter.write(msg);
				bufferedWriter.flush();
				Thread.sleep(500);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
