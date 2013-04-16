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

   String type= "FeatureCollection";
   
    int num_pos;
    int user_id;
    String osm_user;
    int num_edit;  
    ArrayList<Edicions>  editions ;
    ArrayList<Points_edition> features;
    
    
    public int getNum_pos() {
        return num_pos;
    }

    public void setNum_pos(int num_pos) {
        this.num_pos = num_pos;
    }

    public int getNum_edit() {
        return num_edit;
    }

    public void setNum_edit(int num_edit) {
        this.num_edit = num_edit;
    }

    
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

    public ArrayList<Edicions> getEditions() {
        return editions;
    }

    public void setEditions(ArrayList<Edicions> editions) {
        this.editions = editions;
    }

    public ArrayList<Points_edition> getFeatures() {
        return features;
    }

    public void setFeatures(ArrayList<Points_edition> features) {
        this.features = features;
    }
    


    
    
}
