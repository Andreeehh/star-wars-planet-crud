package com.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.dao.PlanetDAO;
import com.models.Planet;

@SuppressWarnings("deprecation")
@ManagedBean(name = "planetBean")
@ViewScoped
public class PlanetBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private PlanetDAO planetDAO;
	private List<Planet> planets;
	private Planet planet = new Planet();
	private int numberOfFilms;
	private String filter;

	public PlanetBean() {
		planet = new Planet();
		planetDAO = new PlanetDAO("jdbc:mysql://localhost:3306/starwars", "root", "1234");
		try {
			planets = planetDAO.listAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Planet> getPlanets() {
		for (Planet planet : planets) {
			int id = planet.getId();
			int numberOfFilms = planetDAO.getNumberOfFilms(id);
			planet.setNumFilms(numberOfFilms);
		}
		return planets;
	}

	public void setPlanets(List<Planet> planets) {
		this.planets = planets;
	}

	public Planet getPlanet() {
		return planet;
	}

	public void setPlanet(Planet planet) {
		this.planet = planet;
	}

	public String savePlanet() throws SQLException {
		planetDAO.insert(planet);
		planets.add(planet);
		planet = new Planet();
		return "index.xhtml?faces-redirect=true";
	}

	public void deletePlanet(Planet planet) throws SQLException {
		planetDAO.delete(planet);
		planets.remove(planet);
	}

	public void updatePlanet() throws SQLException {
		planetDAO.update(planet);
		planet = new Planet();
	}

	public void clearPlanet() {
		planet = new Planet();
	}

	public int getNumberOfFilms() {
		return numberOfFilms;
	}

	public void setNumberOfFilms(int numberOfFilms) {
		this.numberOfFilms = numberOfFilms;
	}

	public String editPlanet(Planet planet) {
		this.planet = planet;
		return "edit.xhtml?faces-redirect=true";
	}
	
	public String getFilter() {
	    return filter;
	}

	public void setFilter(String filter) {
	    this.filter = filter;
	}
	
	public void filterPlanetsByNameOrId() {
		String filterLowerCase = filter.toLowerCase();
		try {
			planets = planetDAO.listAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!filterLowerCase.equals("")) {
			planets = planets.stream()
			        .filter(p -> p.getName().toLowerCase().contains(filterLowerCase) || String.valueOf(p.getId()).contains(filterLowerCase))
			        .collect(Collectors.toList());
		}
		
	    
	}
}