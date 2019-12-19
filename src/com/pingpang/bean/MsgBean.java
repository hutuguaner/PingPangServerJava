package com.pingpang.bean;

import java.util.List;

public class MsgBean {

	private int type;
	private List<ClientBean> from;
	private List<ClientBean> to;
	private String content;

	public MsgBean() {
		super();
	}

	public List<ClientBean> getFrom() {
		return from;
	}

	public void setFrom(List<ClientBean> from) {
		this.from = from;
	}

	public List<ClientBean> getTo() {
		return to;
	}

	public void setTo(List<ClientBean> to) {
		this.to = to;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	 

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	 

}
