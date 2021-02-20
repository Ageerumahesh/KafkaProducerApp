package com.kafka.contoller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.kafka.constants.AppConstants;
import com.kafka.pojo.Employee;

@RestController
public class KafkaProducerController {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	private Gson gson;

	@PostMapping(AppConstants.PRODUCE)
	public ResponseEntity<String> sendDataToKafka(@RequestBody Employee emp)
			throws InterruptedException, ExecutionException {
		System.out.println("KafkaProducerController.sendDataToKafka()");
		ListenableFuture<SendResult<String, String>> result = kafkaTemplate.send(AppConstants.TOPIC, gson.toJson(emp));
		//kafka template is responsible to send message 
		return new ResponseEntity<>(result.get().getProducerRecord().value(), HttpStatus.OK);
		
	}
}
