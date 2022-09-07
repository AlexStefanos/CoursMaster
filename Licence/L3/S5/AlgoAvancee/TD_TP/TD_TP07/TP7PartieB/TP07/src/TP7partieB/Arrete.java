package partieB;

public class Arrete {
        int source, destination;       
        double poids;
        
        public Arrete(int source, int destination, double poids) {
            this.source = source;
            this.destination = destination;
            this.poids = poids;
        }
}
