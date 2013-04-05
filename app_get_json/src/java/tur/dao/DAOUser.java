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
import tur.bean.Edicions;
import tur.bean.Geometry;
import tur.bean.Properties;
import tur.bean.User;
import tur.bean.Points_edition;

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
    //String osm_user = null;

    public DAOUser(Connection conn) {
        this.conn = conn;
        this.conni = conn;
    }

    public List list_idUsers() {
        List list = new LinkedList();
        try {
            String sql = "SELECT user_id , count(*) AS nun_edits FROM osm_changeset GROUP BY user_id ORDER BY nun_edits DESC limit 100;";

            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            int num = 0;
            while (rs.next()) {
                int id = rs.getInt("user_id");
                list.add(id);
            }

            pstmt.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error en en id" + ex);
        }
        return list;

    }

    public ArrayList<Points_edition> list_points_edition(int id) {

        ArrayList<Points_edition> list = new ArrayList<Points_edition>();

        try {

            String sql = "select user_id,osm_user,min_lon,min_lat, max_lon , max_lat ,"
                    + "(CAST(((TIMESTAMP WITH TIME ZONE 'epoch' +  INTERVAL '1 second' * closed_at)|| ' ') AS date)|| '') as closed_at ,"
                    + " num_changes from  osm_changeset where user_id=" + id + ";";

            System.out.println(sql);
            pstmti = conni.prepareStatement(sql);
            rsi = pstmti.executeQuery();
            while (rsi.next()) {

                Points_edition point_edition = new Points_edition();

                Geometry geometry = new Geometry();
                Properties properties = new Properties();
                double min_lon = rsi.getDouble("min_lon");
                double min_lat = rsi.getDouble("min_lat");
                double max_lon = rsi.getDouble("max_lon");
                double max_lat = rsi.getDouble("max_lat");
                
                double lon=(min_lon + max_lon) / 2;
                double lat = (min_lat + max_lat) / 2;
                
                 System.out.println("lon Before"+lon);
                 System.out.println("Lat Before"+lat);
                
                lon= Math.round(lon*1000)/1000.0d;
                lat= Math.round(lat*1000)/1000.0d;
                
                
                 System.out.println("lon "+lon);
                 System.out.println("Lat "+lat);
                
                double[] cordinates = new double[]{lon, lat};

                //geometry.setType("Point");
                geometry.setCoordinates(cordinates);
                properties.setNc(rsi.getInt("num_changes"));
                point_edition.setGeometry(geometry);

                point_edition.setProperties(properties);


                list.add(point_edition);
            }
            pstmti.close();
            rsi.close();

        } catch (SQLException ex) {
            System.out.println("Error en Listar Edicion: " + ex);
        }
        return list;

    }

    public ArrayList<Edicions> list_edition_month(int id) {

        ArrayList<Edicions> list = new ArrayList<Edicions>();

        try {
            String sql = "select substring((CAST(((TIMESTAMP WITH TIME ZONE 'epoch' +  INTERVAL '1 second' * closed_at)|| ' ') AS date)|| ''),1,7) as date , \n"
                    + "count(num_changes) as num_edition, sum(num_changes) as num_changes from  osm_changeset  where user_id=" + id + "\n"
                    + " GROUP BY substring((CAST(((TIMESTAMP WITH TIME ZONE 'epoch' +  INTERVAL '1 second' * closed_at)|| ' ') AS date)|| ''),1,7) \n"
                    + " ORDER BY substring((CAST(((TIMESTAMP WITH TIME ZONE 'epoch' +  INTERVAL '1 second' * closed_at)|| ' ') AS date)|| ''),1,7)";

            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            int num = 0;
            while (rs.next()) {
                Edicions edicion = new Edicions();

                edicion.setD(rs.getString("date"));
                edicion.setNe(rs.getInt("num_edition"));
                edicion.setNc(rs.getInt("num_changes"));

                list.add(edicion);
            }

            pstmt.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error en en Edicion" + ex);
        }
        return list;

    }

    public String find_osm_user(int id) {

        String osm_user = "";
        try {
            String sql = "SELECT osm_user from osm_changeset where  user_id=" + id + " limit 1;";

            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();          
            while (rs.next()) {
                osm_user = rs.getString("osm_user");
            }

            pstmt.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error en en Edicion" + ex);
        }
        return osm_user;

    }
}
