package com.barbosa.vitor.disruptor;

import java.nio.ByteBuffer;

import com.lmax.disruptor.RingBuffer;

public class StringEventProducer {

	private final RingBuffer<StringEvent> ring;

	public StringEventProducer(RingBuffer<StringEvent> ring) {
		this.ring = ring;
	}
	
	public void onData(String bb){
		long next = ring.next();
		try {
			StringEvent stringEvent = ring.get(next);
			stringEvent.set(String.valueOf(bb));
		} finally {
			ring.publish(next);
		}
	}
	
}
