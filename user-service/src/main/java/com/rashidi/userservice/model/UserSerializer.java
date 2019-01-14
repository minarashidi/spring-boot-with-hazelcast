package com.rashidi.userservice.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.ByteArraySerializer;

import java.io.IOException;
import java.io.InputStream;


public class UserSerializer implements ByteArraySerializer<User> {

  private ObjectMapper mapper = new ObjectMapper();

  public int getTypeId() {
    return 5;
  }

  public void write(ObjectDataOutput out, User object)
    throws IOException {
    byte[] data = mapper.writeValueAsBytes(object);
    System.out.println("Size is " + data.length);
    out.write(data);
  }

  public User read(ObjectDataInput in) throws IOException {
    return mapper.readValue((InputStream) in,
      User.class);
  }

  public void destroy() {
  }

  @Override
  public byte[] write(User user) throws IOException {
    return mapper.writeValueAsBytes(user);
  }

  @Override
  public User read(byte[] bytes) throws IOException {
    return mapper.readValue(bytes, User.class);
  }
}
