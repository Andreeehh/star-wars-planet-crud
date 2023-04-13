package com.models;


public class Planet {
    private int id;
    private String name;
    private String climate;
    private String terrain;
    private int numFilms;
    
    
    
    public Planet() {
    	super();
		this.id = 0;
		this.name = "";
		this.climate = "";
		this.terrain = "";
		this.numFilms = 0;
	}

	public Planet(int id, String name, String climate, String terrain, int numFilms) {
		super();
		this.id = id;
		this.name = name;
		this.climate = climate;
		this.terrain = terrain;
		this.numFilms = numFilms;
	}

	// getters and setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getClimate() {
        return climate;
    }
    
    public void setClimate(String climate) {
        this.climate = climate;
    }
    
    public String getTerrain() {
        return terrain;
    }
    
    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

	public int getNumFilms() {
		return numFilms;
	}

	public void setNumFilms(int numFilms) {
		this.numFilms = numFilms;
	}
}
