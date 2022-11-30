package com.hieunguyen.repository;

import com.hieunguyen.models.User;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
  private static MongoClient getConnection() {
    return new MongoClient("");
  }

  public static User[] index() {
    String databaseName = "funix-assignment";
    String collectionName = "users";

    MongoDatabase db = getConnection().getDatabase(databaseName);

    MongoCollection<Document> col = db.getCollection(collectionName);

    List obj = new ArrayList();
    obj.add(new BasicDBObject("userId"))
  }
}
