package com.barbosa.vitor.disruptor;

import com.lmax.disruptor.EventHandler;

public class StringEventHandler implements EventHandler<StringEvent>{

	@Override
	public void onEvent(StringEvent event, long sequence, boolean endOfBatch) throws Exception {
		System.out.println(event);
	}

}
