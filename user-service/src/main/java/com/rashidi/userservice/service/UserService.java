package com.rashidi.userservice.service;

import com.rashidi.userservice.model.User;

import java.util.Collection;

public interface UserService {

  Collection<User> findAllUsers();

  User findByEmail(String email);

  void add(User user);
}
