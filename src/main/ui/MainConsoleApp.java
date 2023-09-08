package ui;

import java.io.FileNotFoundException;

// Starts a new movie tracking session
public class MainConsoleApp {
    public static void main(String[] args) {
        try {
            new MovieTrackingAppConsole();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
