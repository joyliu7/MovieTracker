package persistence;

import model.Movie;
import model.MovieList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads movie list from JSON data stored in file
// The code in this class is based on the code provided in JsonSerializationDemo project
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads movie list from file and returns it;
    // throws IOException if an error occurs reading data from file
    public MovieList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMovieList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses movie list from JSON object and returns it
    private MovieList parseMovieList(JSONObject jsonObject) {
        MovieList movieList = new MovieList();
        addMovies(movieList, jsonObject);
        return movieList;
    }

    // MODIFIES: movieList
    // EFFECTS: parses movies from JSON object and adds them to movie list
    private void addMovies(MovieList movieList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("movies");
        for (Object json : jsonArray) {
            JSONObject nextMovie = (JSONObject) json;
            addMovie(movieList, nextMovie);
        }
    }

    // MODIFIES: movieList
    // EFFECTS: parses movie from JSON object and adds it to movie list
    private void addMovie(MovieList movieList, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        boolean isWatched = jsonObject.getBoolean("isWatched");
        Movie movie = new Movie(name, isWatched);
        movieList.addMovie(movie);
    }
}
