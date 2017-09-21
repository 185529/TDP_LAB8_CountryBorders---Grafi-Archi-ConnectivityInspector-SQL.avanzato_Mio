package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	
	private UndirectedGraph<Country, Border> graph;
	private BordersDAO dao;
	
	private List<Country> countries;
	private List<Border> borders;

	public Model() {
		super();
		dao = new BordersDAO();
	}

	public void createGraph(Integer year) {
		
		countries = new ArrayList<Country>();
		borders = new ArrayList<Border>();
		
		countries = dao.loadAllCountries();
		borders = dao.getCountryPairs(year);
		
		graph = new SimpleGraph<Country, Border>(Border.class);
		
		// adding vertexes
		
		for(Country c : countries){
			for(Border b : borders){
				if(c.equals(b.getC1()) || c.equals(b.getC2())){
					if(!graph.vertexSet().contains(c)){
						graph.addVertex(c);
					}
				}
			}
		}
		
		System.out.println(graph.vertexSet());

		// adding edges
		
		for(Border b : borders){
			if(!graph.edgeSet().contains(b)){
				graph.addEdge(b.getC1(), b.getC2(), b);
			}
		}
		
		System.out.println(graph.edgeSet());
		
	}

	public Set<Country> getNations() {
		
		for(Country c : graph.vertexSet()){
			c.setNeighbours(graph.degreeOf(c));
			System.out.print(c.getNeighbours()+" ");
		}
		
		return graph.vertexSet();
		
	}

	public Integer getNumberOfConnectedComponents() {

		ConnectivityInspector<Country, Border> ci = new ConnectivityInspector<Country, Border>(this.graph);
				
		return ci.connectedSets().size();
		
	}

}
