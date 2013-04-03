/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tur.bean;

/**
 *
 * @author ruben
 */
public class User_by_Edicion {

    Geometry geometry;
    Properties properties;
    String type;
    
    /*
     "type": "FeatureCollection",
     "features": [{
     "geometry": {
     "type": "Point",
     "coordinates": [-46.520834, -18.592222]
     },
     "type": "Feature",
     "properties": {
     "timestamp": 1246129309.0,
     "version": "3",
     "user": "lmpinto"
     }
     }
     */

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
