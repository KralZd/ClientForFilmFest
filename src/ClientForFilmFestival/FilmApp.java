/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientForFilmFestival;

import ClientForFilmFestival.model.Movie;
import ClientForFilmFestival.model.ServerConnection;
import ClientForFilmFestival.view.FilmEditController;
import ClientForFilmFestival.view.FilmOverviewController;
import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * 
 * @author admin
 */
public class FilmApp extends Application {

    public FilmEditController controller;
    public ServerConnection ser = new ServerConnection();
    private static Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Movie> movieData = FXCollections.observableArrayList();

    public FilmApp() throws IOException {

        
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Filmový Festival");

        initRootLayout();

        showFilmOverview();
    }

    /**
     * Returns the data as an observable list of movies
     *
     * @return
     */
    public ObservableList<Movie> getFilmData() {
        return ser.movieData;
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FilmApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the film overview inside the root layout.
     */
    public void showFilmOverview() {
        try {
            // Load film overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FilmApp.class.getResource("view/FilmOverview.fxml"));
            AnchorPane filmOverview = (AnchorPane) loader.load();

            // Set film overview into the center of root layout.
            rootLayout.setCenter(filmOverview);

            // Give the controller access to the  FilmApp.
            FilmOverviewController controller = loader.getController();
            controller.setFilmApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /** 
     * Creates new stage 
     * Set the movie into the controller
     * show the dialog and wait until the user closes it
     * 
    */
public boolean showFilmEditDialog(Movie movie) {
    try {
        // Load the fxml file and create a new stage for the popup dialog.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FilmApp.class.getResource("view/FilmEditDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Create the dialog Stage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Edit movie");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Set the movie into the controller.
        this.controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setMovie(movie);

        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();

        return controller.isOkClicked();
    } catch (IOException e) {
        e.printStackTrace();
        System.out.println("je ot tu");
        return false;
    }
}
    


/**
     * Returns the main stage.
     *
     * @return
     */
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
