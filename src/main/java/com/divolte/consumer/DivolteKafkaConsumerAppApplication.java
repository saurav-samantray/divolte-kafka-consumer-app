package com.divolte.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.divolte.consumer.kafka.Receiver;

@SpringBootApplication
public class DivolteKafkaConsumerAppApplication {

	@Autowired
	Receiver receiver;
	
	public static void main(String[] args) {
		SpringApplication.run(DivolteKafkaConsumerAppApplication.class, args);
	}

}
