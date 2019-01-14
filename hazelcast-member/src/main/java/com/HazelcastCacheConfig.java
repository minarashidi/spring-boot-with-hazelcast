package com;

import com.hazelcast.config.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// We just created two maps with a TTL policy of 20 seconds. Therefore, 20 seconds after the map gets populated,
// a cache eviction will occur.
@Configuration
public class HazelcastCacheConfig {

  @Bean
  public Config hazelCastConfig() {

    Config config = new Config();

    config.setInstanceName("hazelcast-cache");

    MapConfig allUsersCache = new MapConfig();
    allUsersCache.setTimeToLiveSeconds(20);
    allUsersCache.setEvictionPolicy(EvictionPolicy.LFU);
    config.getMapConfigs().put("alluserscache", allUsersCache);

    MapConfig usercache = new MapConfig();
    usercache.setTimeToLiveSeconds(20);
    usercache.setEvictionPolicy(EvictionPolicy.LFU);
    config.getMapConfigs().put("usercache", usercache);

    ManagementCenterConfig manCenterCfg = new ManagementCenterConfig();
    manCenterCfg.setEnabled(true).setUrl("http://localhost:8080/mancenter");


    NetworkConfig network = config.getNetworkConfig();
    network.setPort(5701).setPortCount(20);
    network.setPortAutoIncrement(true);
    JoinConfig join = network.getJoin();
    join.getMulticastConfig().setEnabled(false);
    join.getTcpIpConfig()
      .addMember("machine1")
      .addMember("localhost").setEnabled(true);

    return config;

  }
}
