package com.hieunguyen.repository;

import com.google.gson.Gson;
import com.hieunguyen.models.User;

import static com.mongodb.client.model.Filters.empty;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

import com.mongodb.MongoException;
import com.mongodb.client.*;
import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
  static String mongoUri = "";
  static String databaseName = "funix-assignment-6";
  static String collectionName = "users";
  static MongoCollection collection = getCollection(mongoUri, databaseName, collectionName);

  private static MongoCollection getCollection(
      String connectionString,
      String databaseName,
      String collectionName
  ) {
    try {
      MongoClient mongoClient = MongoClients.create(connectionString);
      MongoDatabase database = mongoClient.getDatabase(databaseName);
      Bson command = new BsonDocument("ping", new BsonInt64(1));
      database.runCommand(command);
      return database.getCollection(collectionName);
    } catch (MongoException e) {
      System.err.println("An error occurred while attempting to run a command: " + e);
      return null;
    }
  }

  public static User findByUserId(String userId) {
    MongoCollection collection = getCollection(mongoUri, databaseName, collectionName);
    assert collection != null;

    Document doc = (Document) collection
      .find(eq("userId", userId))
      .first();
    Gson gson = new Gson();
    if (doc == null) {
      return null;
    }
    return gson.fromJson(doc.toJson(), User.class);
  }

  public static void updateLoginAttemptTimes(String userId, int loginAttemptTimes) {
    assert collection != null;

    Bson filter = eq("userId", userId);
    Bson updateOperation = set("loginAttemptTimes", loginAttemptTimes);
    collection.updateOne(filter, updateOperation);
  }

  public static void resetLoginAttemptTimes() {
    MongoCollection collection = getCollection(
      mongoUri,
      databaseName,
      collectionName
    );
    assert collection != null;

    Bson filter = empty();
    Bson updateOperation = set("loginAttemptTimes", 0);
    collection.updateMany(filter, updateOperation);
  }

  public static void changePassword(String userId, String password) {
    assert collection != null;
    Bson filter = eq("userId", userId);
    Bson updateOperation = set("password", password);
    collection.updateOne(filter, updateOperation);
  }

  public static void resetAllPassword() {
    assert collection != null;
    Bson filter = empty();
    Bson updateOperation = set("password", "pass1111");
    collection.updateMany(filter, updateOperation);
  }

  public static List<User> index() {
    assert collection != null;
    List<User> users = new ArrayList<>();

    FindIterable<Document> iterable = collection.find();
    Gson gson = new Gson();
    for (Document document : iterable) {
      users.add(gson.fromJson(document.toJson(), User.class));
    }

    return users;
  }
}
