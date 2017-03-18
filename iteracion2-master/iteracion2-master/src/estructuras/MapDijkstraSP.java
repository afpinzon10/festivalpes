package estructuras;

import java.util.*;

import vos.Vuelo;

/**
 *  La clase <tt>MapDijkstraSP</tt> permite obtener el c�mino m�s corto entre dos nodos
 *  de la clase {@link MapGraph} utilizando el algoritmo de Dijkstra.
 *  <p>
 *  En el peor de los casos el algoritmo tarde y tiempo de O(V*E) para encontrar el camino
 *  m�s corto entre dos nodos. Donde V es el num�ro de v�rtices del grafo y E el n�mero de
 *  arcos.
 *  <b>Para obtener mayor informaci�n:</b>
 *  Consultar la <a href="http://algs4.cs.princeton.edu/44sp">Secci�n 4.4</a> de
 *  <i>Algorithms, 4th Edition</i> de Robert Sedgewick y Kevin Wayne.
 *
 *  
 */
public class MapDijkstraSP {

	/**
	 * Referencia al MapGraph para el que se quiere encontrar el camino m�s corto entre dos nodos
	 */
	private Graph mapGraph;

	/**
	 * Id del nodo de destino
	 */
	private String idDestination;

	/**
	 * Arreglo que en la posici�n i tiene una referencia al �ltimo MapEdge del camino m�s corto del nodo fuente al nodo
	 * i
	 */
	private ArrayList<Vuelo> edgesTo;

	/**
	 * Arreglo que en la posici�n i tiene la distancia m�s corto del nodo fuente al nodo i o infinito en caso de que no
	 * haya un camino encontrado
	 */
	private ArrayList<Double> distTo;

	/**
	 * Cola de prioridad en la que se atienden los nodos encotrados por el algoritmo de Dijkstra
	 */
	private PriorityQueue<MapDijkstraNode> priorityQueue;

	/**
	 * Constructor de la clase que realiza el algoritmo Dijkstra luego de ser inicializado
	 * @param mapGraph Referencia al MapGraph sobre el que se quiere realizar el algoritmo
	 * @param idAeroOrigen Id de la fuente
	 * @param idAeroDestino Id del destino
	 */
	public MapDijkstraSP(Graph mapGraph, String idAeroOrigen, String idAeroDestino)
	{
		this.mapGraph = mapGraph;
		this.idDestination = idAeroDestino;


		for(int v = 0; v<distTo.size(); v++)
			distTo.get(v) = Double.POSITIVE_INFINITY;

		distTo[idAeroOrigen] = 0.0;

		priorityQueue = new PriorityQueue(mapGraph.numVertices());
		MapDijkstraNode sourceNode = new MapDijkstraNode(idAeroOrigen, 0.0);
		priorityQueue.add(sourceNode);

		MapDijkstraNode currentNode = sourceNode;
		
		//TOD 1). Complete el m�todo. Debe tomar el nodo actual de la cola y relajarlo
		// mientras no haya llegado al destino.
		while(!priorityQueue.isEmpty())
		{
			currentNode = priorityQueue.remove();
			relax(currentNode);
		}
	}



	/**
	 * Devuelve una LinkedList con los arcos que forman el camino del nodo fuente al nodo de destino
	 * @return
	 */
	public List<Vuelo> pathTo() 
	{
		if (distTo[idDestination] == Double.POSITIVE_INFINITY)
			return null;
		List<Vuelo> path = new ArrayList<Vuelo>();

		//TOD 2). Complete el m�todo. Debe agregar al camino el arco actual
		//y modificar el arco actual al arco anterior
		// mientras el arco actual no sea nulo.
		for(Vuelo e = edgeTo[idDestination]; e!=null; e = edgeTo[e.getDestino())] )
		{
			path.add(e);
		}
		return path;
	}

	/**
	 * Recibe un nodo por parametro y explora sus arcos, relaja las distancias a estos y los agrega a la cola de
	 * prioridad
	 * @param currentNode
	 */
	private void relax(MapDijkstraNode currentNode)
	{
		try{
			for(Vuelo edge : mapGraph.getNodeEdges(currentNode.nodeId)) {
				int edgeDestination = edge.getTo();

				if(distTo[edgeDestination] > distTo[currentNode.nodeId] + edge.distance)
				{
					MapDijkstraNode currentDestination = new MapDijkstraNode(edgeDestination, distTo[edgeDestination]);

					distTo[edgeDestination] = distTo[currentNode.nodeId] + edge.distance;
					edgeTo[edgeDestination] = edge;

					if (priorityQueue.contains(currentDestination)) {
						priorityQueue.remove(currentDestination);
						currentDestination.distance = distTo[currentNode.nodeId] + edge.distance;
						priorityQueue.add(currentDestination);
					}
					else
					{
						currentDestination.distance = distTo[currentNode.nodeId] + edge.distance;
						priorityQueue.add(currentDestination);
					}

				}

			}
		}catch(Exception e){
		}
	}

	/**
	 * Clase que representa un nodo que se va agregar a la cola de prioridad son su distancia actual a la fuente
	 */
	private class MapDijkstraNode implements Comparable<MapDijkstraNode>{

		public int nodeId;
		public double distance;

		public MapDijkstraNode (int edgeDestination, double distance) {
			this.nodeId = edgeDestination;
			this.distance = distance;
		}

		public boolean equals(MapDijkstraNode j) {
			return this.nodeId == j.nodeId;
		}

		public int compareTo(MapDijkstraNode j) {
			return (int)this.distance - (int)j.distance;
		}
	}

}


