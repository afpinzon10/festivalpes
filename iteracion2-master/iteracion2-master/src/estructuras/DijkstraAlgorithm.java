package estructuras;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import vos.Aeropuerto;
import vos.Vuelo;



public class DijkstraAlgorithm {

		private int tamnho;
        private final List<Aeropuerto> nodes;
        private final ArrayList<Vuelo> edges;
        private Set<Aeropuerto> settledNodes;
        private Set<Aeropuerto> unSettledNodes;
        private Map<String, Aeropuerto> predecessors;
        private Map<String, Double> distance;

        public DijkstraAlgorithm(DijkstraGraph graph, Aeropuerto pAeropuerto) {
                // create a copy of the array so that we can operate on this array
                this.nodes = new ArrayList<Aeropuerto>(graph.getVertexes());
                this.edges = new ArrayList<Vuelo>(graph.getEdges());
                execute(pAeropuerto);
                tamnho = 100;
                
        }

        public void execute(Aeropuerto source) {
                settledNodes = new HashSet<Aeropuerto>();
                unSettledNodes = new HashSet<Aeropuerto>();
                distance = new HashMap<String, Double>();
                predecessors = new HashMap<String, Aeropuerto>();
                distance.put(source.getIATA(), 0.0);
                unSettledNodes.add(source);
                while (unSettledNodes.size() > 0) {
                        Aeropuerto node = getMinimum(unSettledNodes);
                        settledNodes.add(node);
                        unSettledNodes.remove(node);
                        findMinimalDistances(node);
                }
        }

        private void findMinimalDistances(Aeropuerto node) {
                List<Aeropuerto> adjacentNodes = getNeighbors(node);
                for (Aeropuerto target : adjacentNodes) {
                        if (getShortestDistance(target) > getShortestDistance(node)
                                        + getDistance(node, target)) {
                                distance.put(target.getIATA(), getShortestDistance(node)
                                                + getDistance(node, target));
                                predecessors.put(target.getIATA(), node);
                                unSettledNodes.add(target);
                        }
                }

        }

        private double getDistance(Aeropuerto node, Aeropuerto target) {
                for (Vuelo edge : edges) {
                        if (edge.getOrigen().equals(node)
                                        && edge.getDestino().equals(target)) {
                                return edge.getDistacia();
                        }
                }
                throw new RuntimeException("Should not happen");
        }

        private List<Aeropuerto> getNeighbors(Aeropuerto node) {
                List<Aeropuerto> neighbors = new ArrayList<Aeropuerto>();
                for (Vuelo edge : edges) {
                        if (edge.getOrigen().equals(node)
                                        && !isSettled(edge.getDestino())) {
                                neighbors.add(finNode(edge.getDestino()));
                        }
                }
                return neighbors;
        }

        private Aeropuerto getMinimum(Set<Aeropuerto> Aeropuertoes) {
                Aeropuerto minimum = null;
                for (Aeropuerto Aeropuerto : Aeropuertoes) {
                        if (minimum == null) {
                                minimum = Aeropuerto;
                        } else {
                                if (getShortestDistance(Aeropuerto) < getShortestDistance(minimum)) {
                                        minimum = Aeropuerto;
                                }
                        }
                }
                return minimum;
        }
        
        private Aeropuerto finNode(String pIATA)
        {
        	Aeropuerto respuesta = null;
        	for (int i = 0; i < nodes.size(); i++)
        	{
        		if (nodes.get(i).getIATA().equals(pIATA))
        			respuesta = nodes.get(i);
        	}
        	return respuesta;
        }

        private boolean isSettled(String pIATA) {
        	Aeropuerto aeropuerto = finNode(pIATA);
                return settledNodes.contains(aeropuerto);
        }

        private Double getShortestDistance(Aeropuerto destination) {
                Double d = distance.get(destination);
                if (d == null) {
                        return Double.MAX_VALUE;
                } else {
                        return d;
                }
        }
        
        public ArrayList<Vuelo> getPathEdges(Aeropuerto target)
        {
        	ArrayList<Vuelo> path = new ArrayList<Vuelo>();
        	boolean fin = false;
        	 Aeropuerto step = target;
        	 int i = 0;
        	while (predecessors.get(step) != null || i < tamnho)
        	{
        		step = predecessors.get(step);
        	}
        	path = edges;
        	return path;
        }
        

        /*
         * This method returns the path from the source to the selected target and
         * NULL if no path exists
         */
        public ArrayList<Aeropuerto> getPath(Aeropuerto target) {
                ArrayList<Aeropuerto> path = new ArrayList<Aeropuerto>();
                Aeropuerto step = target;
                // check if a path exists
                if (predecessors.get(step) == null) {
                        return null;
                }
                path.add(step);
                while (predecessors.get(step) != null) {
                        step = predecessors.get(step);
                        path.add(step);
                }
                // Put it into the correct order
                Collections.reverse(path);
                return path;
        }

}