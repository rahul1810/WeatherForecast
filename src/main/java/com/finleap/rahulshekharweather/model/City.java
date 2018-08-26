package com.finleap.rahulshekharweather.model;

import com.google.gson.annotations.SerializedName;

public class City
{
    @SerializedName("coord")
    private Coordinates coordinates;

    private String id;

    private String name;

    private String country;

    public Coordinates getCoordinates ()
    {
        return coordinates;
    }

    public void setCoordinates (Coordinates coordinates)
    {
        this.coordinates = coordinates;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getCountry ()
    {
        return country;
    }

    public void setCountry (String country)
    {
        this.country = country;
    }

    @Override
    public String toString()
    {
        return "City [coordinates = "+coordinates+", id = "+id+", name = "+name+", country = "+country+"]";
    }
}
