package estructuras;

/**
 * Undirected, unweighted simple graph data type
 * <p>
 *  Notes:
 *  <ul>
 *   <li> Parallel edges are not allowed
 *   <li> Self loops are allowed
 *  </ul>
 *  <p>
 *  This Graph class was adapted from 
 *  <a href="http://www.cs.princeton.edu/introcs/45graph/Graph.java">Graph.java</a> by 
 *  by Robert Sedgewick and Kevin Wayne
 */
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import vos.Aeropuerto;

public class Graph {
	private HashMap<String, TreeSet<Aeropuerto>> myAdjList;
	private HashMap<String, Aeropuerto> myVertices;
	private static final TreeSet<Aeropuerto> EMPTY_SET = new TreeSet<Aeropuerto>();
	private int myNumVertices;
	private int myNumEdges;

	/**
	 * Construct empty Graph
	 */
	public Graph() {
		myAdjList = new HashMap<String, TreeSet<Aeropuerto>>();
		myVertices = new HashMap<String, Aeropuerto>();
		myNumVertices = myNumEdges = 0;

	}

	/**
	 * Add a new Aeropuerto name with no neighbors (if Aeropuerto does not yet exist)
	 * 
	 * @param name
	 *            Aeropuerto to be added
	 */
	public void addAeropuerto(Aeropuerto pAeropuerto) {
		
		Aeropuerto v = myVertices.get(pAeropuerto.getIATA());
		if (v == null) {
			v = pAeropuerto;
			myVertices.put(pAeropuerto.getIATA(), v);
			myAdjList.put(v.getIATA(), new TreeSet<Aeropuerto>());
			myNumVertices += 1;
		}
	}

	/**
	 * Returns the Aeropuerto matching v
	 * @param name a String name of a Aeropuerto that may be in
	 * this Graph
	 * @return the Aeropuerto with a name that matches v or null
	 * if no such Aeropuerto exists in this Graph
	 */
	public Aeropuerto getAeropuerto(String name) {
		return myVertices.get(name);
	}

	/**
	 * Returns true iff v is in this Graph, false otherwise
	 * @param name a String name of a Aeropuerto that may be in
	 * this Graph
	 * @return true iff v is in this Graph
	 */
	public boolean hasAeropuerto(String name) {
		return myVertices.containsKey(name);
	}

	/**
	 * Is from-to, an edge in this Graph. The graph is 
	 * undirected so the order of from and to does not
	 * matter.
	 * @param from the name of the first Aeropuerto
	 * @param to the name of the second Aeropuerto
	 * @return true iff from-to exists in this Graph
	 */
	public boolean hasEdge(String from, String to) {

		if (!hasAeropuerto(from) || !hasAeropuerto(to))
			return false;
		return myAdjList.get(myVertices.get(from)).contains(myVertices.get(to));
	}
	
	/**
	 * Add to to from's set of neighbors, and add from to to's
	 * set of neighbors. Does not add an edge if another edge
	 * already exists
	 * @param from the name of the first Aeropuerto
	 * @param to the name of the second Aeropuerto
	 */
	public void addEdge(Aeropuerto a, Aeropuerto b) {
		String from = a.getNombre();
		String to = b.getNombre();
		if (hasEdge(from, to))
			return;
		myNumEdges += 1;
		myAdjList.get(a.getIATA()).add(b);
		myAdjList.get(b.getIATA()).add(a);
	}

	/**
	 * Return an iterator over the neighbors of Aeropuerto named v
	 * @param name the String name of a Aeropuerto
	 * @return an Iterator over Vertices that are adjacent
	 * to the Aeropuerto named v, empty set if v is not in graph
	 */
	public Iterable<Aeropuerto> adjacentTo(String name) {
		if (!hasAeropuerto(name))
			return EMPTY_SET;
		return myAdjList.get(getAeropuerto(name));
	}

	/**
	 * Return an iterator over the neighbors of Aeropuerto v
	 * @param v the Aeropuerto
	 * @return an Iterator over Vertices that are adjacent
	 * to the Aeropuerto v, empty set if v is not in graph
	 */
	public Iterable<Aeropuerto> adjacentTo(Aeropuerto v) {
		if (!myAdjList.containsKey(v))
			return EMPTY_SET;
		return myAdjList.get(v);
	}

	/**
	 * Returns an Iterator over all Vertices in this Graph
	 * @return an Iterator over all Vertices in this Graph
	 */
	public Iterable<Aeropuerto> getVertices() {
		return myVertices.values();
	}

	public int numVertices()
	{
		return myNumVertices;
	}
	
	public int numEdges()
	{
		return myNumEdges;
	}
	
	/**
	 * Sets each Vertices' centrality field to
	 * the degree of each Aeropuerto (i.e. the number of
	 * adjacent Vertices)
	 */
	public void degreeCentrality()
	{
		// TODO: complete degreeCentrality
	}
	
	/**
	 * Sets each Vertices' centrality field to
	 * the average distance to every Aeropuerto
	 */
	public void closenessCentrality()
	{
		// TODO: complete closenessCentrality
	}
	/**
	 * Sets each Vertices' centrality field to
	 * the proportion of geodesics (shortest paths) that
	 * this Aeropuerto is on
	 */
	public void betweennessCentrality()
	{
		// TODO: complete betweennessCentrality
	}
	/*
	 * Returns adjacency-list representation of graph
	 */
	public String toString() {
		String s = "";
		for (Aeropuerto v : myVertices.values()) {
			s += v + ": ";
			for (Aeropuerto w : myAdjList.get(v)) {
				s += w + " ";
			}
			s += "\n";
		}
		return s;
	}

	private String escapedVersion(String s) {
		return "\'"+s+"\'";

	}

}