package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a movie with a name and status (watched or not)
public class Movie implements Writable {
    private String name;
    private boolean isWatched;

    // EFFECTS: constructs a movie with given name and status (watched or not)
    public Movie(String name, boolean isWatched) {
        this.name = name;
        this.isWatched = isWatched;
    }

    //EFFECTS: return "Y" if the movie is watched, "N" if not
    public String getIsWatchedYN() {
        if (this.isWatched) {
            return "Y";
        } else {
            return "N";
        }
    }

    // EFFECTS: returns the name of the movie
    public String getName() {
        return this.name;
    }

    // EFFECTS: returns the status (watched or not) of the movie as a boolean
    public boolean getIsWatched() {
        return this.isWatched;
    }

    // MODIFIES: this
    // EFFECTS: sets the isWatched value of the movie to the given value
    public void setIsWatched(boolean isWatched) {
        this.isWatched = isWatched;
    }

    @Override
    // EFFECTS: makes a new json object as needed and returns it
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("isWatched", isWatched);
        return json;
    }
}
