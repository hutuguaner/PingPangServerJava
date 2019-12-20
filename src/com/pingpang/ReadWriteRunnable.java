package com.pingpang;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.json.JSONArray;
import org.json.JSONObject;

import com.pingpang.bean.ClientBean;
import com.pingpang.bean.MsgBean;
import com.pingpang.manager.MsgManager;


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

	private String token;
	private String lng;
	private String lat;

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
				if (readline != null && !readline.trim().equals("") && !readline.trim().equals("null")
						&& com.alibaba.fastjson.JSON.isValid(readline)) {
					System.out.println("服务器收到数据： " + readline);
					MsgBean msg = com.alibaba.fastjson.JSON.parseObject(readline, MsgBean.class);
					token = msg.getFrom().get(0).getToken();
					lat = msg.getFrom().get(0).getLat();
					lng = msg.getFrom().get(0).getLng();
					MsgManager.getInstance().dispatchMsg(this,msg);

				} else {
					//System.out.println("服务器收到数据： " + readline);
				}

				MsgBean msg = msgQueue.poll();
				if (msg != null) {

					bufferedWriter.write(com.alibaba.fastjson.JSON.toJSONString(msg) + "\n");
					bufferedWriter.flush();
				} else {
					bufferedWriter.write("\n");
					bufferedWriter.flush();
				}

				Thread.sleep(500);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

}
