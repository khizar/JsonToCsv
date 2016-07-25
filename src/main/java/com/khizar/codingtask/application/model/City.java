package com.khizar.codingtask.application.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author Khizar
 */
@JsonPropertyOrder({"id", "name", "type", "latitude", "longitude"})
public class City {

    private Long id;
    private String name;
    private String type;
    private String latitude;
    private String longitude;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getLatitude() {
        return latitude;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    public String getLongitude() {
        return longitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
