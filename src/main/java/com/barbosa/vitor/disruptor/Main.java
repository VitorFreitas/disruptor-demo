package com.barbosa.vitor.disruptor;

import java.nio.ByteBuffer;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

public class Main {

	private static final int BUFFER_SIZE = 1024;

	public static void main(String[] args) throws Exception {
	        Executor executor = Executors.newCachedThreadPool();

	        Disruptor<StringEvent> disruptor = new Disruptor<>(StringEvent::new, BUFFER_SIZE, executor);

	        disruptor.handleEventsWith(new StringEventHandler());

	        disruptor.start();

	        RingBuffer<StringEvent> ringBuffer = disruptor.getRingBuffer();

	        StringEventProducer producer = new StringEventProducer(ringBuffer);
	        
	        for (long l = 0; true; l++){
	            ringBuffer.publishEvent((event, sequence, buffer) -> event.set(buffer), UUID.randomUUID().toString());
	            Thread.sleep(1000);
	        }
	    }
}
