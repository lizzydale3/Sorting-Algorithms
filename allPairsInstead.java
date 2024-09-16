//Lizzy Dale, CIS 322, 3/8/24
//this time shortest path algo without any user input

public class allPairsInstead {

    public static void main(String[] args) {
        // Graph information
        int vertices = 7;
        char[] vertexLabels = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        double[][] weights = {
        		//Vertex A
                {0, 5, 3, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY},
                //Vertex B
                {Double.POSITIVE_INFINITY, 0, 2, Double.POSITIVE_INFINITY, 3, Double.POSITIVE_INFINITY, 1},
                //Vertex C
                {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 0, 7, 5, 6, Double.POSITIVE_INFINITY},
                //Vertex D
                {2, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 0, Double.POSITIVE_INFINITY, 6, Double.POSITIVE_INFINITY},
                //Vertex E
                {Double.POSITIVE_INFINITY, 3, Double.POSITIVE_INFINITY, 2, 0, 1, Double.POSITIVE_INFINITY},
                //Vertex F
                {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 6, 2, 0, Double.POSITIVE_INFINITY},
                //Vertex G
                {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 1, Double.POSITIVE_INFINITY, 0}
        };


        // Compute shortest weighted paths
        double[][] shortestDistances = allPairsShortestPaths(weights);

        // Print shortest distances
        System.out.println("Shortest weighted distances from all vertices:");

        // Print column headers
        System.out.print("     ");
        for (char vertexLabel : vertexLabels) {
            System.out.printf("%-5s", vertexLabel);
        }
        System.out.println();

        // Print table
        for (int i = 0; i < vertices; i++) {
            System.out.printf("%-5s", vertexLabels[i]);
            for (int j = 0; j < vertices; j++) {
                double distance = shortestDistances[i][j];
                System.out.printf("%-5s", (distance == Double.POSITIVE_INFINITY ? "Inf" : distance));
            }
            System.out.println();
        }
    }

    // Floyd-Warshall algorithm for all pairs shortest paths
    static double[][] allPairsShortestPaths(double[][] weights) {
        int numVertices = weights.length;
        double[][] shortestDistances = new double[numVertices][numVertices];

        // Initialize distances to input weights
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                shortestDistances[i][j] = weights[i][j];
            }
        }

        // Floyd-Warshall algorithm
        for (int k = 0; k < numVertices; k++) {
            for (int i = 0; i < numVertices; i++) {
                for (int j = 0; j < numVertices; j++) {
                    if (shortestDistances[i][k] + shortestDistances[k][j] < shortestDistances[i][j]) {
                        shortestDistances[i][j] = shortestDistances[i][k] + shortestDistances[k][j];
                    }
                }
            }
        }

        return shortestDistances;
    }
}
