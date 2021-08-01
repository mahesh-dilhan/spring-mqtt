package io.mahesh.springmqtt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;

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
}
