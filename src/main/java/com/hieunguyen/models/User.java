package com.hieunguyen.models;
import org.bson.types.ObjectId;

public class User {
  ObjectId _id;
  private final String userId;
  private final String password;
  private final int loginAttempTimes;

  public User(ObjectId id, String userId, String password, int loginAttempTimes) {
    this._id = id;
    this.userId = userId;
    this.password = password;
    this.loginAttempTimes = loginAttempTimes;
  }

  public String getUserId() {
    return userId;
  }

  public String getPassword() {
    return password;
  }

  public int getLoginAttemptTimes() {
    return loginAttempTimes;
  }

  @Override
  public String toString() {
    return this.userId + ", userId: " + this.getUserId() + ", password: " + this.getPassword() + ", LoginAttemptTimes: " + this.getLoginAttemptTimes();
  }
}
