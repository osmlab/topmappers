/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tur.bean;

/**
 *
 * @author ruben
 */
public class Properties {

    int user_id;
    String osm_user;
    String closed_at;
    int num_changes;

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

    public String getClosed_at() {
        return closed_at;
    }

    public void setClosed_at(String closed_at) {
        this.closed_at = closed_at;
    }

    public int getNum_changes() {
        return num_changes;
    }

    public void setNum_changes(int num_changes) {
        this.num_changes = num_changes;
    }
    
}
