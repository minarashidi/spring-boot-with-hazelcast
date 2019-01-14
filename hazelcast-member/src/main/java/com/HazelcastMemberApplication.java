package com;

import com.hazelcast.core.Hazelcast;

public class HazelcastMemberApplication {

  public static void main(String[] args) {
    Hazelcast.newHazelcastInstance();
  }
}
