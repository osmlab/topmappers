/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tur.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import tur.bean.Edicion;
import tur.bean.User;

/**
 *
 * @author ruben
 */
public class DAOUser {

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    //Edicion
    Connection conni = null;
    PreparedStatement pstmti = null;
    ResultSet rsi = null;
    String osm_user = null;

    public DAOUser(Connection conn) {
        this.conn = conn;
        this.conni = conn;
    }

    public List listarUser() {

        List list = new LinkedList();


        try {
            //String sql = "SELECT user_id , count(*) AS nun_edits FROM osm_changeset GROUP BY user_id ORDER BY nun_edits ASC limit 40";
            String sql="SELECT * from osm_changeset where user_id=117055 or user_id=843366";
            //String sql = "SELECT user_id , count(*) AS nun_edits FROM osm_changeset GROUP BY user_id ORDER BY nun_edits DESC limit 100;";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setUser_id(rs.getInt("user_id"));
                System.out.println("------------------user id--" + user.getUser_id());
                user.setEdicion(listEdition(user.getUser_id()));
                user.setOsm_user(osm_user);
                list.add(user);

            }

            pstmt.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error en Listar" + ex);
        }
        return list;
    }

    public ArrayList<Edicion> listEdition(int id) {

        ArrayList<Edicion> list = new ArrayList<Edicion>();

        try {

            String sql = "select osm_user,min_lon,min_lat, max_lon , max_lat , (CAST(((TIMESTAMP WITH TIME ZONE 'epoch' +  INTERVAL '1 second' * closed_at)|| ' ') AS date)|| '') as closed_at , num_changes from  osm_changeset where user_id=" + id + ";";
            System.out.println(sql);
            pstmti = conni.prepareStatement(sql);
            rsi = pstmti.executeQuery();
            while (rsi.next()) {
                osm_user = rsi.getString("osm_user");
                Edicion edicion = new Edicion();
                edicion.setMin_lon(rsi.getDouble("min_lon"));
                edicion.setMin_lat(rsi.getDouble("min_lat"));
                edicion.setMax_lon(rsi.getDouble("max_lon"));
                edicion.setMax_lat(rsi.getDouble("max_lat"));
                edicion.setClosed_at(rsi.getString("closed_at"));
                edicion.setNum_changes(rsi.getInt("num_changes"));
                edicion.setLat((edicion.getMin_lat() + edicion.getMax_lat()) / 2);
                edicion.setLon((edicion.getMin_lon() + edicion.getMax_lon()) / 2);
                list.add(edicion);
            }
            pstmti.close();
            rsi.close();

        } catch (SQLException ex) {
            System.out.println("Error en Listar Edicion: " + ex);
        }
        return list;

    }
}