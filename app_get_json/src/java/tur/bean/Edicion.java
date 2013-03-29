package tur.bean;


/**
 *
 * @author ruben
 */
public class Edicion {
    double min_lon;
    double min_lat;
    double max_lon;
    double max_lat;
    String closed_at;
    int num_changes;
    double lat;
    double lon;

    public double getMin_lon() {
        return min_lon;
    }

    public void setMin_lon(double min_lon) {
        this.min_lon = min_lon;
    }

    public double getMin_lat() {
        return min_lat;
    }

    public void setMin_lat(double min_lat) {
        this.min_lat = min_lat;
    }

    public double getMax_lon() {
        return max_lon;
    }

    public void setMax_lon(double max_lon) {
        this.max_lon = max_lon;
    }

    public double getMax_lat() {
        return max_lat;
    }

    public void setMax_lat(double max_lat) {
        this.max_lat = max_lat;
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

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
    
}
