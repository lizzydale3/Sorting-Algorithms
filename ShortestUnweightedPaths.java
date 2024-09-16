//Lizzy Dale, CIS 322, 3/8/24
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class ShortestUnweightedPaths {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input: Number of vertices
        System.out.print("Enter the number of vertices: ");
        int vertices = scanner.nextInt();

        // Map to convert single-letter input to corresponding integer index
        Map<Character, Integer> vertexIndexMap = new HashMap<>();
        char currentLabel = 'A';

        // Create a List of LinkedLists to represent the graph
        List<LinkedList<Integer>> adjacencyList = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new LinkedList<>());
            vertexIndexMap.put(currentLabel++, i);
        }

        // Input: Edges
        System.out.println("Enter directed edges (vertex pairs, separated by space; -1 to stop)");
        char source, destination;
        while (true) {
            System.out.println("Enter directed edge (source destination): ");
            source = scanner.next().charAt(0);
            if (source == '-') {
                break;
            }
            destination = scanner.next().charAt(0);

            // Add the directed edge to the adjacency list
            adjacencyList.get(vertexIndexMap.get(source)).add(vertexIndexMap.get(destination));
        }

        // Input: Starting vertex
        System.out.println("Enter the starting vertex: ");
        char startVertexLabel = scanner.next().charAt(0);
        int startVertex = vertexIndexMap.get(startVertexLabel);

        // Compute shortest unweighted paths
        int[] shortestDistances = bfsShortestPaths(adjacencyList, startVertex);

        // Print shortest distances
        System.out.println("Shortest unweighted distances from vertex " + startVertexLabel + ":");
        for (Map.Entry<Character, Integer> entry : vertexIndexMap.entrySet()) {
            char vertexLabel = entry.getKey();
            int distance = shortestDistances[entry.getValue()];
            System.out.println("To vertex " + vertexLabel + ": " + (distance == Integer.MAX_VALUE ? "Infinity" : distance));
        }

        scanner.close();
    }

    // BFS for shortest unweighted paths
    static int[] bfsShortestPaths(List<LinkedList<Integer>> adjacencyList, int startVertex) {
        int numVertices = adjacencyList.size();
        int[] shortestDistances = new int[numVertices];
        boolean[] visited = new boolean[numVertices];
        Queue<Integer> queue = new LinkedList<>();

        // Initialize distances to infinity (or a large value) and mark all vertices as unvisited
        for (int i = 0; i < numVertices; i++) {
            shortestDistances[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }

        // Set the distance from the source vertex to itself as 0
        shortestDistances[startVertex] = 0;

        // Enqueue the starting vertex
        queue.add(startVertex);
        visited[startVertex] = true;

        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();

            // Visit all outgoing vertices of the dequeued vertex
            for (int neighbor : adjacencyList.get(currentVertex)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                    // Update the shortest distance if a shorter path is found
                    shortestDistances[neighbor] = shortestDistances[currentVertex] + 1;
                }
            }
        }

        return shortestDistances;
    }
}
