package com.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.models.Planet;

public class PlanetDAO {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	

	public PlanetDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}

	protected void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}
	}

	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}

	public boolean insert(Planet planet) throws SQLException {
		String sql = "INSERT INTO planets (name, climate, terrain) VALUES (?, ?, ?)";
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, planet.getName());
		statement.setString(2, planet.getClimate());
		statement.setString(3, planet.getTerrain());
		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowInserted;
	}

	public List<Planet> listAll() throws SQLException {
		List<Planet> listPlanet = new ArrayList<Planet>();
		String sql = "SELECT * FROM planets";
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			String climate = resultSet.getString("climate");
			String terrain = resultSet.getString("terrain");
			Planet planet = new Planet();
			planet.setId(id);
			planet.setName(name);
			planet.setClimate(climate);
			planet.setTerrain(terrain);
			listPlanet.add(planet);
		}
		resultSet.close();
		statement.close();
		disconnect();
		return listPlanet;
	}

	public boolean delete(Planet planet) throws SQLException {
		String sql = "DELETE FROM planets where id = ?";
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, planet.getId());
		boolean rowDeleted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowDeleted;
	}

	public boolean update(Planet planet) throws SQLException {
		String sql = "UPDATE planets SET name = ?, climate = ?, terrain = ? WHERE id = ?";
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, planet.getName());
		statement.setString(2, planet.getClimate());
		statement.setString(3, planet.getTerrain());
		statement.setInt(4, planet.getId());
		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowUpdated;
	}

	public Planet get(int id) throws SQLException, IOException {
		Planet planet = null;
		String sql = "SELECT * FROM planets WHERE id = ?";
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			String name = resultSet.getString("name");
			String climate = resultSet.getString("climate");
			String terrain = resultSet.getString("terrain");
			int numberOfFilms = 0;
			numberOfFilms = getNumberOfFilms(id);
			planet = new Planet(id, name, climate, terrain, numberOfFilms);
		}
		resultSet.close();
		statement.close();
		return planet;
	}
	
	public int getNumberOfFilms(int id) {
		int numberOfFilms = 0;
		try {
			URL url = new URL("https://swapi.dev/api/planets/" + id);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");

			if (connection.getResponseCode() != 200) {
				return 0;
			}

			InputStream inputStream = connection.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String output;
			while ((output = bufferedReader.readLine()) != null) {
				ObjectMapper objectMapper = new ObjectMapper();
				JsonNode jsonNode = objectMapper.readTree(output);
				numberOfFilms = jsonNode.get("films").size();
			}

			connection.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return numberOfFilms;
	}
}