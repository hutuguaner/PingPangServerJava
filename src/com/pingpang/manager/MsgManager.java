package com.pingpang.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pingpang.ReadWriteRunnable;
import com.pingpang.bean.MsgBean;

public class MsgManager {

	private static MsgManager instance = null;

	public static synchronized MsgManager getInstance() {
		if (instance == null)
			instance = new MsgManager();
		return instance;
	}

	private MsgManager() {
		clientMap = new HashMap<String, ReadWriteRunnable>();
	}

	private Map<String, ReadWriteRunnable> clientMap;

	public void addClientToMap(ReadWriteRunnable client, String token) {
		clientMap.put(token, client);
	}

	public void addMsgToClient(String token, MsgBean msg) {
		ReadWriteRunnable readWriteRunnable = clientMap.get(token);
		if (readWriteRunnable == null) {
			return;
		}
		readWriteRunnable.addMsgThatWaitToSend(msg);
	}

	public void addMsgToClients(List<String> tokens, MsgBean msg) {
		if (tokens == null)
			return;
		for (String token : tokens) {
			addMsgToClient(token, msg);
		}
	}

}
