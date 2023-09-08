package persistence;

import org.json.JSONObject;

// Interface which is implemented by Movie class and MovieList class
// The code in this class is based on the code provided in JsonSerializationDemo project
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}

