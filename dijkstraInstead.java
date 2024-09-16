//Lizzy Dale, CIS 322, 3/8/24
import java.util.*;

public class dijkstraInstead {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input: Number of vertices
        System.out.print("Enter the number of vertices: ");
        int vertices = scanner.nextInt();

        // Map to convert single-letter input to corresponding integer index
        Map<Character, Integer> vertexIndexMap = new HashMap<>();
        char currentLabel = 'A';

        // Create a List of Maps to represent the weighted graph
        List<Map<Integer, Integer>> weightedGraph = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            weightedGraph.add(new HashMap<>());
            vertexIndexMap.put(currentLabel++, i);
        }

        // Input: Edges and weights
        System.out.println("Enter directed edges and weights (source destination weight; -1 to stop)");
        char source, destination;
        int weight;
        while (true) {
            System.out.print("Enter directed edge and weight (source destination weight): ");
            source = scanner.next().charAt(0);
            if (source == '-') {
                break;
            }
            destination = scanner.next().charAt(0);
            weight = scanner.nextInt();

            // Add the weighted edge to the graph
            int sourceIndex = vertexIndexMap.get(source);
            int destinationIndex = vertexIndexMap.get(destination);
            weightedGraph.get(sourceIndex).put(destinationIndex, weight);
        }

        // Input: Starting vertex
        System.out.print("Enter the starting vertex: ");
        char startVertexLabel = scanner.next().charAt(0);
        int startVertex = vertexIndexMap.get(startVertexLabel);

        // Compute shortest paths using Dijkstra's algorithm
        int[] shortestDistances = dijkstraShortestPaths(weightedGraph, startVertex);

        // Print shortest distances
        System.out.println("Shortest distances from vertex " + startVertexLabel + ":");
        for (Map.Entry<Character, Integer> entry : vertexIndexMap.entrySet()) {
            char vertexLabel = entry.getKey();
            int distance = shortestDistances[entry.getValue()];
            System.out.println("To vertex " + vertexLabel + ": " + (distance == Integer.MAX_VALUE ? "Infinity" : distance));
        }

        scanner.close();
    }

    // Dijkstra's algorithm for shortest paths
    static int[] dijkstraShortestPaths(List<Map<Integer, Integer>> weightedGraph, int startVertex) {
        int numVertices = weightedGraph.size();
        int[] shortestDistances = new int[numVertices];
        boolean[] visited = new boolean[numVertices];

        // Initialize distances to infinity and mark all vertices as unvisited
        Arrays.fill(shortestDistances, Integer.MAX_VALUE);
        Arrays.fill(visited, false);

        // Set the distance from the source vertex to itself as 0
        shortestDistances[startVertex] = 0;

        for (int i = 0; i < numVertices - 1; i++) {
            // Find the vertex with the minimum distance value among unvisited vertices
            int minVertex = findMinDistanceVertex(shortestDistances, visited);
            visited[minVertex] = true;

            // Update the distances of the neighboring vertices
            for (Map.Entry<Integer, Integer> neighborEntry : weightedGraph.get(minVertex).entrySet()) {
                int neighbor = neighborEntry.getKey();
                int edgeWeight = neighborEntry.getValue();
                if (!visited[neighbor] && shortestDistances[minVertex] != Integer.MAX_VALUE &&
                        shortestDistances[minVertex] + edgeWeight < shortestDistances[neighbor]) {
                    shortestDistances[neighbor] = shortestDistances[minVertex] + edgeWeight;
                }
            }
        }

        return shortestDistances;
    }

    // Helper method to find the vertex with the minimum distance among unvisited vertices
    static int findMinDistanceVertex(int[] distances, boolean[] visited) {
        int minDistance = Integer.MAX_VALUE;
        int minVertex = -1;

        for (int i = 0; i < distances.length; i++) {
            if (!visited[i] && distances[i] <= minDistance) {
                minDistance = distances[i];
                minVertex = i;
            }
        }

        return minVertex;
    }
}
