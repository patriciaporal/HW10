package space.harbour.java.hw10;

import com.mongodb.BasicDBObject;
import com.mongodb.Function;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoExecutor {
    MongoClient client;
    MongoDatabase mongoDatabase;
    MongoClientURI uri;

    public MongoExecutor() {
        uri = new MongoClientURI("mongodb://localhost:27017/admin");
        client = new MongoClient(uri);
        mongoDatabase = client.getDatabase("movies");
    }

    public <T> T execFindOne(String collection, BasicDBObject searchQuery,
                             Function<Document, T> handler) {
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(collection);
        FindIterable<Document> result = mongoCollection.find(searchQuery);
        return handler.apply(result.first());
    }

    public void execStoreMovie(Document document) {
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("movies");
        mongoCollection.insertOne(document);
        System.out.println("Stored");
    }
}
