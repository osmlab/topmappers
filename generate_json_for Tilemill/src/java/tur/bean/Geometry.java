/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tur.bean;

/**
 *
 * @author ruben
 */
public class Geometry {

    String type;
    private double[] coordinates;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }
    
}
