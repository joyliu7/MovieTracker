package ui;

import model.Event;
import model.EventLog;
import model.Movie;
import model.MovieList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;


// Some code in this file is based on the ListDemo project in the Oracle Java Tutorials:
//        https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html

// Represents the GUI of the movie tracking application
public class GUI extends JPanel implements ListSelectionListener {
    private JList list;
    private DefaultListModel listModel;

    private static final String addString = "Add";
    private static final String removeString = "Remove";
    private static final String saveString = "Save";
    private static final String loadString = "Load";

    private JButton removeButton;
    private JButton loadButton;
    private JTextField movieName;
    private JTextField movieIsWatched;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/movielist.json";
    private MovieList movieList;

    private static Image icon;


    //EFFECTS: constructs the GUI
    public GUI() {
        super(new BorderLayout());

        JScrollPane listScrollPane = setScrollPane();

        JLabel nameLabel = new JLabel("Name ");
        JLabel watchedLabel = new JLabel("  Watched? ");

        JButton addButton = new JButton(addString);
        AddListener addListener = new AddListener(addButton);
        addButton.setActionCommand(addString);
        addButton.addActionListener(addListener);
        addButton.setEnabled(false);

        setRemoveButton();

        JButton loadButton = setLoadButton();

        JButton saveButton = setSaveButton();

        setNameTextField(addListener);

        setIsWatchedTextField(addListener);

        addToPanel(listScrollPane, nameLabel, watchedLabel, addButton, loadButton, saveButton);

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        movieList = new MovieList();

        icon = Toolkit.getDefaultToolkit().getImage("/Users/root1/Desktop/CPSC 210/project_b7j6x/data/MIcon.jpeg");
    }

    //EFFECTS: adds multiple elements needed to panel
    private void addToPanel(JScrollPane listScrollPane, JLabel nameLabel,
                            JLabel watchedLabel, JButton addButton, JButton loadButton, JButton saveButton) {
        //Create a panel that uses BoxLayout.
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(nameLabel);
        buttonPane.add(movieName);
        buttonPane.add(watchedLabel);
        buttonPane.add(movieIsWatched);
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(addButton);
        buttonPane.add(removeButton);
        buttonPane.add(saveButton);
        buttonPane.add(loadButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
    }

    // EFFECTS: sets up the "Watched?" text field
    private void setIsWatchedTextField(AddListener addListener) {
        movieIsWatched = new JTextField(10);
        movieIsWatched.addActionListener(addListener);
        movieIsWatched.getDocument().addDocumentListener(addListener);
        String isWatched = listModel.getElementAt(
                list.getSelectedIndex()).toString();
    }

    // EFFECTS: sets up the "Name" text field
    private void setNameTextField(AddListener addListener) {
        movieName = new JTextField(10);
        movieName.addActionListener(addListener);
        movieName.getDocument().addDocumentListener(addListener);
        String name = listModel.getElementAt(
                list.getSelectedIndex()).toString();
    }

    // EFFECTS: sets up the "Save" button
    private JButton setSaveButton() {
        JButton saveButton = new JButton(saveString);
        SaveListener saveListener = new SaveListener();
        saveButton.setActionCommand(saveString);
        saveButton.addActionListener(saveListener);
        return saveButton;
    }

    // EFFECTS: sets up the "Load" button
    private JButton setLoadButton() {
        loadButton = new JButton(loadString);
        LoadListener loadListener = new LoadListener();
        loadButton.setActionCommand(loadString);
        loadButton.addActionListener(loadListener);
        return loadButton;
    }

    // EFFECTS: sets up the "Remove" button
    private void setRemoveButton() {
        removeButton = new JButton(removeString);
        RemoveListener removeListener = new RemoveListener();
        removeButton.setActionCommand(removeString);
        removeButton.addActionListener(removeListener);
    }

    // EFFECTS: sets up the list scroll pane
    private JScrollPane setScrollPane() {
        listModel = new DefaultListModel();
        listModel.addElement("Please enter name and watching status (Y=yes, N=no) of the movie.");
        //Create the list and put it in a scroll pane.
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);
        return listScrollPane;
    }

    // Represents the RemoveListener for the remove button
    class RemoveListener implements ActionListener {

        // EFFECTS: handles the event of clicking "Remove" button
        public void actionPerformed(ActionEvent e) {
            //This method can be called only if
            //there's a valid selection
            //so go ahead and remove whatever's selected.
            int index = list.getSelectedIndex();
            listModel.remove(index);
            movieList.removeMovieAt(index - 1);

            int size = listModel.getSize();

            if (size == 1) { //No movie's left
                removeButton.setEnabled(false);

            } else { //Select an index.
                if (index == listModel.getSize()) {
                    //removed item in last position
                    index--;
                }

                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
        }
    }

    // This listener is shared by the text field and the add button.
    class AddListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        // EFFECTS: constructs an AddListener
        public AddListener(JButton button) {
            this.button = button;
        }

        // EFFECTS: handles the event of clicking the add button
        public void actionPerformed(ActionEvent e) {
            String name = movieName.getText();
            String isWatched = movieIsWatched.getText();

            //User didn't type in a unique name...
            if (name.equals("") || alreadyInList(name)) {
                Toolkit.getDefaultToolkit().beep();
                movieName.requestFocusInWindow();
                movieName.selectAll();
                return;
            }

            listModel.addElement(name + " (" + isWatched + ")");
            movieList.addMovie(new Movie(movieName.getText(), watchedToBoolean(movieIsWatched.getText())));

            //Reset the text field.
            movieName.requestFocusInWindow();
            movieName.setText("");

            //Reset the text field.
            movieIsWatched.requestFocusInWindow();
            movieIsWatched.setText("");

            //Select the new item and make it visible.
            list.setSelectedIndex(listModel.getSize() - 1);
            list.ensureIndexIsVisible(listModel.getSize() - 1);
        }

        // EFFECTS: converts the given string ("Y"/"N") to isWatched boolean, and returns the boolean
        public boolean watchedToBoolean(String watched) {
            watched = watched.toLowerCase();
            boolean bool = false;
            if (watched.equals("y")) {
                bool = true;
            }
            return bool;
        }

        // EFFECTS: returns true if the given name is already in list, false otherwise
        public boolean alreadyInList(String name) {
            return listModel.contains(name);
        }

        //Required by DocumentListener.
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        //Required by DocumentListener.
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        //Required by DocumentListener.
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        // EFFECTS: enable the button if not already enabled
        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        // EFFECTS: if the text field is empty, disables the button and returns true; otherwise returns false
        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }

    // Represents the load listener
    public class LoadListener implements ActionListener {

        // EFFECTS: handles the event of clicking the load button
        public void actionPerformed(ActionEvent event) {
            try {
                movieList = jsonReader.read();
            } catch (IOException e) {
                //catch IOException
            }
            for (Movie m : movieList.getMovies()) {
                listModel.addElement(m.getName() + " (" + m.getIsWatchedYN() + ")");
            }
        }
    }

    // Represents the save listener
    public class SaveListener implements ActionListener {

        // EFFECTS: handles the event of clicking the save button
        public void actionPerformed(ActionEvent event) {
            try {
                jsonWriter.open();
                jsonWriter.write(movieList);
                jsonWriter.close();
            } catch (FileNotFoundException e) {
                //catch FileNotFoundException
            }
        }
    }

    //This method is required by ListSelectionListener.
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (list.getSelectedIndex() == -1) {
                //No selection, disable remove button.
                removeButton.setEnabled(false);

            } else {
                //Selection, enable the remove button.
                removeButton.setEnabled(true);
            }
        }
    }

    // EFFECTS: prints the events in the event log to console
    public static void printLog() {
        EventLog eventLog = EventLog.getInstance();
        for (Event event : eventLog) {
            System.out.println(event.toString());
        }
    }

    // EFFECTS: creates and shows the GUI
    private static void createAndShowGUI() {

        //Create and set up the window.
        JFrame frame = new JFrame("My Movie List");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                printLog();
                System.exit(0);
            }
        });

        //Create and set up the content pane.
        JComponent newContentPane = new GUI();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Set the icon image.
        frame.setIconImage(icon);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }


    // EFFECTS: Schedule a job for the event-dispatching thread:
    //          creating and showing this application's GUI.
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
