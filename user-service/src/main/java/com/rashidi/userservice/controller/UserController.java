package com.rashidi.userservice.controller;

import com.rashidi.userservice.model.User;
import com.rashidi.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController("/users")
public class UserController {

  @Autowired
  private UserService userService;

  @RequestMapping(produces = "application/json", method = RequestMethod.GET)
  public ResponseEntity<Collection<User>> finaAll() {
    return ResponseEntity.ok().body(userService.findAllUsers());
  }

  @RequestMapping(params = "email", produces = "application/json", method = RequestMethod.GET)
  public ResponseEntity<User> finaByEmail(@RequestParam String email) {
    return ResponseEntity.ok().body(userService.findByEmail(email));
  }

  @RequestMapping(produces = "application/json", method = RequestMethod.POST)
  public void add(@RequestBody User user) {
    userService.add(user);
  }
}
