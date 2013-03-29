package tur.bean;


import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ruben
 */
public class User {

    int user_id;
    String osm_user;
    ArrayList<Edicion> edicion;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getOsm_user() {
        return osm_user;
    }

    public void setOsm_user(String osm_user) {
        this.osm_user = osm_user;
    }

    public ArrayList<Edicion> getEdicion() {
        return edicion;
    }

    public void setEdicion(ArrayList<Edicion> edicion) {
        this.edicion = edicion;
    }
    
    
}
