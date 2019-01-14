package com;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.config.SerializerConfig;
import com.hazelcast.core.HazelcastInstance;
import com.rashidi.userservice.model.User;
import com.rashidi.userservice.model.UserSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class UserServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(UserServiceApplication.class, args);
  }

  @Bean
//  @Profile("hazelcast-cache")
  public HazelcastInstance hazelcastInstance() {
    ClientConfig clientConfig = new ClientConfig();

    clientConfig.getSerializationConfig().getSerializerConfigs()
      .add(new SerializerConfig().
        setTypeClass(User.class).setImplementation(new UserSerializer()));

    ClientNetworkConfig clientNetworkConfig = new ClientNetworkConfig();
    clientNetworkConfig.addAddress("127.0.0.1:5701");
    clientConfig.setNetworkConfig(clientNetworkConfig);
    return HazelcastClient.newHazelcastClient(clientConfig);
  }

}
