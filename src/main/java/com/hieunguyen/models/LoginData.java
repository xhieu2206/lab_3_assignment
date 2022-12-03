package com.hieunguyen.models;

public class LoginData {
  private final boolean isLoggedInSuccess;
  private final int loginAttemptTimes;
  private final boolean isUserIdExisted;

  public LoginData(boolean isLoggedInSuccess, int loginAttemptTimes, boolean isUserIdExisted) {
    this.isLoggedInSuccess = isLoggedInSuccess;
    this.loginAttemptTimes = loginAttemptTimes;
    this.isUserIdExisted = isUserIdExisted;
  }

  public boolean isLoggedInSuccess() {
    return isLoggedInSuccess;
  }

  public int getLoginAttemptTimes() {
    return loginAttemptTimes;
  }

  public boolean isUserIdExisted() {
    return isUserIdExisted;
  }
}
