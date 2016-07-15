package com.gerry.common.framework.eventbus.message;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FastJsonMessage implements Serializable{

	 /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2194255765918435108L;
	
	private Object[] args;// 发送消息
	
	public FastJsonMessage() {

	}
	
	public FastJsonMessage(Object[]  args) {
		this.args = args;
	}
	
}
