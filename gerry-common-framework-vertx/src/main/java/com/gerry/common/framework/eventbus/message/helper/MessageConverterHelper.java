package com.gerry.common.framework.eventbus.message.helper;

import com.gerry.common.framework.eventbus.message.FastJsonMessage;

/**
 * 消息转换帮助类
 * 
 *
 * @author gerry
 * @version 1.0, 2016年7月8日下午6:24:43
 * @since com.gerry.link 1.0
 */
public class MessageConverterHelper {

	public static FastJsonMessage converter(Object[] args) {
		return new FastJsonMessage(args);
	}

}
