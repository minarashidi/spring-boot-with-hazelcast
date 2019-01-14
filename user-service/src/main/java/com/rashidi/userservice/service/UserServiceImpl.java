package com.rashidi.userservice.service;

import com.hazelcast.core.HazelcastInstance;
import com.rashidi.userservice.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
  private Map<String, User> usersCache;

  @Autowired
  private HazelcastInstance hazelcastInstance;

  @Override
  public Collection<User> findAllUsers() {
    LOGGER.info("Fetching all users using hazelcast");
    return usersCache.values();
  }

  @Override
  public User findByEmail(String email) {
    return usersCache.get(email);
  }

  @Override
  public void add(User user) {
    usersCache.put(user.getEmail(), user);
  }

  @PostConstruct
  public void init() {
    usersCache = hazelcastInstance.getMap("usercache");
  }
}
