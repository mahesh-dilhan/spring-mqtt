package io.mahesh.springmqtt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;

@SpringBootApplication
public class SpringMqttApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMqttApplication.class, args);
	}

	@Bean
	public IntegrationFlow mqttInbound() {
		return IntegrationFlows.from(
				new MqttPahoMessageDrivenChannelAdapter("tcp://localhost:1883",
						"testClient", "topic1", "topic2"))
                .handle(m -> System.out.println(m.getPayload()))
				.get();
	}

	@Bean
	public IntegrationFlow mqttOutboundFlow() {
		return f -> f.handle(new MqttPahoMessageHandler("tcp://host1:1883", "someMqttClient"));
	}

}
