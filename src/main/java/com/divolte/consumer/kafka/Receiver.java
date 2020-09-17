package com.divolte.consumer.kafka;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

import com.divolte.consumer.dto.DefaultEventRecord;

public class Receiver {

	  private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

	  private CountDownLatch latch = new CountDownLatch(1);

	  public CountDownLatch getLatch() {
	    return latch;
	  }

	  @KafkaListener(topics = "${kafka.topic}")
	  public void receive(DefaultEventRecord record) {
	    LOGGER.info("received record='{}'", record.toString());
	    latch.countDown();
	  }
	}