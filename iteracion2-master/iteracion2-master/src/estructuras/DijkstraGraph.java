package estructuras;

import java.util.List;

import vos.Aeropuerto;
import vos.Vuelo;

public class DijkstraGraph {

        private final List<Aeropuerto> vertexes;
        private final List<Vuelo> edges;

        public DijkstraGraph(List<Aeropuerto> vertexes, List<Vuelo> edges) {
                this.vertexes = vertexes;
                this.edges = edges;
        }

        public List<Aeropuerto> getVertexes() {
                return vertexes;
        }

        public List<Vuelo> getEdges() {
                return edges;
        }
	
}
