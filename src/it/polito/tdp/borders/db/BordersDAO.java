package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;

public class BordersDAO {
	
	private List<Country> countries;
	private List<Border> borders;

	public List<Country> loadAllCountries() {

		countries = new ArrayList<Country>();
		
		String sql = "SELECT CCode, StateAbb, StateNme FROM country ORDER BY StateAbb";

		try {
			
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				countries.add(new Country(rs.getString("StateAbb"), rs.getInt("CCode"), rs.getString("StateNme")));
			}
			
			rs.close();
			st.close();
			conn.close();
			
			return countries;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Database Error - loadAllCountries()");
			throw new RuntimeException("DB Error");
		}
		
	}

	public List<Border> getCountryPairs(int year) {

		if(countries == null){
			return null;
		}
		
		borders = new ArrayList<Border>();
		
		final String sql = "SELECT c1.StateAbb AS abb1, c1.CCode AS code1, c1.StateNme AS name1, c2.StateAbb AS abb2, c2.CCode AS code2, c2.StateNme AS name2 FROM country AS c1, country AS c2, contiguity WHERE c1.StateAbb = contiguity.state1ab AND c2.StateAbb = contiguity.state2ab AND contiguity.year <= ? AND contiguity.conttype = 1";
		
		try {
			
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, year);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				borders.add(new Border(new Country(rs.getString("abb1"), rs.getInt("code1"), rs.getString("name1")), new Country(rs.getString("abb2"), rs.getInt("code2"), rs.getString("name2"))));
			}
			
			rs.close();
			st.close();
			conn.close();
			
			return borders;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Database Error - getCountryPairs()");
			throw new RuntimeException("DB Error");
		}

	}
}
