/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientForFilmFestival.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for Movie
 * @author admin
 */
public class Movie {

    private final StringProperty id;
    private final StringProperty date;
    private final StringProperty film;
    private final StringProperty director;
    private final StringProperty cinema;
    // private final ObjectProperty<LocalDate> datum;

    public Movie() {

        this.id = new SimpleStringProperty("");
        this.date = new SimpleStringProperty("");
        this.film = new SimpleStringProperty("");
        this.director = new SimpleStringProperty("");
        this.cinema = new SimpleStringProperty("");

    }

    public String getID() {
        return id.get();
    }

    public void setId(String name) {
        this.id.set(name);
    }

    public StringProperty idProperty() {
        return id;
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String name) {
        this.date.set(name);
    }

    public StringProperty dateProperty() {
        return date;
    }

    public String getFilm() {
        return film.get();
    }

    public void setFilm(String name) {
        this.film.set(name);
    }

    public StringProperty filmProperty() {
        return film;
    }

    public String getDirector() {
        return director.get();
    }

    public void setDirector(String name) {
        this.director.set(name);
    }

    public StringProperty directorProperty() {
        return director;
    }

    public String getCinema() {
        return cinema.get();
    }

    public void setCinema(String name) {
        this.cinema.set(name);
    }

    public StringProperty cinemaProperty() {
        return cinema;
    }

}
