package com.kafka.cnfgrtn;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;


import com.kafka.constants.AppConstants;


@Configuration

public class KafkaProducerCnfgrtn {
	
	// producer factory is responsible to create kafka producer instance 
	
	@Bean
	public ProducerFactory<String, String> producerFactory(){
		
		Map<String, Object> configProps = new HashMap<String, Object>();
		
		configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConstants.HOST);
		//host and  port on which kafka is running 
		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		//serializer class is used for the key
		configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		//serilizer class is used for value ...
		//here we are using stringserilizer for both
		return new DefaultKafkaProducerFactory<String, String>(configProps);
		
	}
	
	
	@Bean
	public KafkaTemplate<String,String> kafkaTemplate(){
		
		//performing construction injection
		return new KafkaTemplate<>(producerFactory());
	}

}
