package space.harbour.java.hw10;

import com.mongodb.BasicDBObject;
import com.mongodb.Function;
import org.bson.Document;

public class Movies {
    public static void main(String[] args) {
        MongoExecutor executor = new MongoExecutor();

        Document movie = new Document("Title", "Forest Gump")
                .append("Year", "1994")
                .append("Runtime", "142")
                .append("Genre", "Drama")
                .append("Director", "Robert Zemeckis")
                .append("Actor", "Tom Hanks")
                .append("Rating", "8.8");
        executor.execStoreMovie(movie);

        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("Title", "Forest Gump");
        Function<Document, String> handler = s -> String.valueOf(s);
        String result = (String) executor.execFindOne("movies", searchQuery, handler);
        System.out.println(result);
    }
}
