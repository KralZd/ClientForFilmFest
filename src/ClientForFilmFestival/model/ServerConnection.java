/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientForFilmFestival.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class create connection with server and send orders
 * @author Zdenek
 */
public class ServerConnection {

    PrintWriter out;
    BufferedReader in;
    public ObservableList<Movie> movieData = FXCollections.observableArrayList();
    Socket clientSocket;

    public ServerConnection() {
        String hostName = "127.0.0.1";
        int portNumber = 1234;
        
        
        try {

            this.clientSocket = new Socket(hostName, portNumber);

            out = new PrintWriter(clientSocket.getOutputStream(), true);      //create IO stream, true - flush buffer so we can get response

            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            
            
            listen();

        } catch (IOException e) {
        }

    }
    /**
     * Asks for list of items in database
     */
    public void listen() throws IOException {

        out.println("show");
        movieData.clear();
        while (true) {

            if (in.readLine().equals("newmovie")) {
                Movie mov = new Movie();
                mov.setId(in.readLine());
                mov.setDate(in.readLine());
                mov.setFilm(in.readLine());
                mov.setDirector(in.readLine());
                mov.setCinema(in.readLine());

                movieData.add(mov);

            } else {
                for (Movie c : movieData) {

                   // System.out.println(c.getID() + " " + c.getFilm());  //pačemu to????????????
                }
                break;
            }

        }

    }
/**
 * sends order to delete item in database
 * 
     * @param id  id of item for deleting
 */
    public void sendDeleteMessage(String id) {

        out.println("delete");

        out.println(id);

    }
/**
 * sends order to add new item to database
 * 
     * @param date  date of showing new movie
     * @param name  name of movie
     * @param director name of director od new movie
     * @param cinema name of cinema where it will be shown
 */
    public void sendNewMovie(String date, String name, String director, String cinema) {

        out.println("add");

        out.println(date);
        out.println(name);
        out.println(director);
        out.println(cinema);

    }
    /**
     * sends order to edit item in database
     * 
     * @param id id of edited movie
     * @param date date of showing
     * @param name  name of the movie
     * @param director name of the director
     * @param cinema name of the cinema
     */
    public void sendEditedMovie(String id, String date, String name, String director, String cinema){
    
     out.println("update");

     
        out.println(id);
        out.println(date);
        out.println(name);
        out.println(director);
        out.println(cinema);
    
    
    }
    /**
     * send request for quiting
     */
    public void sendQuitMessage(){
     
        out.println("quit");
    } 
}
