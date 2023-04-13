package com.controller;

import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.dao.PlanetDAO;
import com.models.Planet;

@SuppressWarnings("deprecation")
@ManagedBean(name = "planetSelectionBean")
@SessionScoped
public class PlanetSelectionBean {
    private Planet selectedPlanet;

    public Planet getSelectedPlanet() {
        return selectedPlanet;
    }

    public void setSelectedPlanet(Planet selectedPlanet) {
        this.selectedPlanet = selectedPlanet;
    }
    
    public String updatePlanet() {
		try {
			new PlanetDAO("jdbc:mysql://localhost:3306/starwars", "root", "1234").update(selectedPlanet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "index.xhtml?faces-redirect=true";
	}
}