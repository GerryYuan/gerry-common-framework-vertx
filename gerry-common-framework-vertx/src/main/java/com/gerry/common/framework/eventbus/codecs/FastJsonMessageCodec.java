package com.gerry.common.framework.eventbus.codecs;

import io.netty.util.CharsetUtil;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

import com.gerry.common.framework.eventbus.message.FastJsonMessage;
import com.gerry.common.framework.vertx.fastjson.VertxFastJsonMessageConverter;

/**
 * eventbus消息转码器
 * 
 *
 * @author gerry
 * @version  1.0, 2016年7月8日下午12:21:04
 * @since   com.gerry.link 1.0
 */
public class FastJsonMessageCodec implements MessageCodec<FastJsonMessage, FastJsonMessage> {

	@Override
	public void encodeToWire(Buffer buffer, FastJsonMessage fastjson) {
		String message = VertxFastJsonMessageConverter.converter(fastjson);
		byte[] encoded = message.getBytes(CharsetUtil.UTF_8);
		buffer.appendInt(encoded.length);
		Buffer buff = Buffer.buffer(encoded);
		buffer.appendBuffer(buff);
	}

	@Override
	public FastJsonMessage decodeFromWire(int pos, Buffer buffer) {
		int length = buffer.getInt(pos);
		pos += 4;
		byte[] encoded = buffer.getBytes(pos, pos + length);
		String message = new String(encoded, CharsetUtil.UTF_8);
		return VertxFastJsonMessageConverter.converter(message, FastJsonMessage.class);
	}

	@Override
	public FastJsonMessage transform(FastJsonMessage fastjson) {
		return fastjson;
	}

	@Override
	public String name() {
		return "fastjson";
	}

	@Override
	public byte systemCodecID() {
		return -1;
	}

}
