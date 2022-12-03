package com.hieunguyen.repository;

import com.google.gson.Gson;
import com.hieunguyen.models.User;
import static com.mongodb.client.model.Filters.eq;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserRepository {
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

  public static User findByUserId(String userId) throws IOException {
    String mongoUri = "";
    String databaseName = "funix-assignment-6";
    String collectionName = "users";

    MongoCollection collection = getCollection(mongoUri, databaseName, collectionName);
    assert collection != null;

    Document docs = (Document) collection
      .find(eq("userId", userId))
      .first();
    System.out.println(docs);
    Gson gson = new Gson();
    if (docs == null) return null;
    return gson.fromJson(docs.toJson(), User.class);
  }
}
