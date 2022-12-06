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

  @Override
  public String toString() {
    return this.isLoggedInSuccess() + " " + this.loginAttemptTimes + " " + this.isUserIdExisted;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof LoginData) {
      return (
        ((LoginData) obj).isLoggedInSuccess == this.isLoggedInSuccess &&
        ((LoginData) obj).loginAttemptTimes == this.loginAttemptTimes &&
        ((LoginData) obj).isUserIdExisted == this.isUserIdExisted
      );
    } else {
      return false;
    }
  }
}
