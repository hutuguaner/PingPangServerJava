package com.pingpang.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.pingpang.ReadWriteRunnable;
import com.pingpang.bean.ClientBean;
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

	// 消息分發
	public void dispatchMsg(ReadWriteRunnable client, MsgBean msg) {
		
		int type = msg.getType();
		if (type == 0) {
			// 连接，全部分发
			clientMap.put(msg.getFrom().get(0).getToken(), client);
			online(msg.getFrom().get(0));

		} else if (type == 1) {
			// 通信，指定分发
			List<ClientBean> tos = msg.getTo();
			for (ClientBean to : tos) {
				ReadWriteRunnable readWriteRunnable = clientMap.get(to.getToken());
				if (readWriteRunnable == null) {
					continue;
				}
				readWriteRunnable.addMsgThatWaitToSend(msg);
			}
		} else if (type == 2) {
			// 断开，全部分发
			clientMap.remove(msg.getFrom().get(0).getToken());
			offline(msg.getFrom().get(0));
		}
	}

	// 有人上綫
	private void online(ClientBean client) {
		// 通知所有人
		Iterator<String> iterator = clientMap.keySet().iterator();
		while (iterator.hasNext()) {
			String token = iterator.next();
			MsgBean notification = new MsgBean();
			notification.setType(0);
			notification.setFrom(Arrays.asList(client));
			if (!token.equals(client.getToken())) {
				clientMap.get(token).addMsgThatWaitToSend(notification);
			}
		}
		sendOtherClientsTo(client);

	}

	// 将所有人的数据发给刚上线人
	private void sendOtherClientsTo(ClientBean client) {
		Iterator<String> iterator = clientMap.keySet().iterator();
		List<ClientBean> otherClients = new ArrayList<ClientBean>();
		while (iterator.hasNext()) {
			String token = iterator.next();
			
			if (!token.equals(client.getToken())) {
				ReadWriteRunnable readWriteRunnable = clientMap.get(token);
				ClientBean clientBean = new ClientBean();
				clientBean.setLat(readWriteRunnable.getLat());
				clientBean.setLng(readWriteRunnable.getLng());
				clientBean.setToken(readWriteRunnable.getToken());
				otherClients.add(clientBean);
			}
		}
		MsgBean msg = new MsgBean();
		msg.setType(0);
		msg.setContent("");
		
		msg.setTo(Arrays.asList(client));
		msg.setFrom(otherClients);
		clientMap.get(client.getToken()).addMsgThatWaitToSend(msg);
	}

	// 有人下线
	private void offline(ClientBean client) {
		// 通知所有人
		Iterator<String> iterator = clientMap.keySet().iterator();
		while (iterator.hasNext()) {
			String token = iterator.next();
			MsgBean notification = new MsgBean();
			notification.setType(2);
			notification.setFrom(Arrays.asList(client));
			if (!token.equals(client.getToken())) {
				clientMap.get(token).addMsgThatWaitToSend(notification);
			}
		}

	}

}
