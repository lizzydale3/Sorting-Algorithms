import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class TopologicalSort {
	// Adjacency list to represent the graph
    private final Map<String, List<String>> adjacencyList;
    private final Map<String, Boolean> visited; 
    
    // Constructor to initialize the adjacency list
    public TopologicalSort() {
        this.adjacencyList = new HashMap<>();
        this.visited = new HashMap<>();
    }
    
    // Method to add a vertex to the graph
    public void addVertex(String vertex) {
        adjacencyList.put(vertex, new ArrayList<>());
        visited.put(vertex, false);
    }
    
    // Method to add a directed edge between two vertices
    public void addEdge(String source, String destination) {
        adjacencyList.get(source).add(destination);
    }
    
    // Main method for topological sort
    public List<String> topologicalSort() {
        Stack<String> stack = new Stack<>();
        
        // Iterate through all vertices and perform topological sort
        for (String vertex : adjacencyList.keySet()) {
            if (!visited.get(vertex)) {  // Change here
                topologicalSortUtil(vertex, stack);
            }
        }
        
        // Create a list to store the topological order
        List<String> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        return result;
    }
    // Utility method to convert vertex label to an array index
    private void topologicalSortUtil(String vertex, Stack<String> stack) {
    	// Mark the current vertex as visited
        visited.put(vertex, true); 
        
        // Recursively visit all neighbors of the current vertex
        for (String neighbor : adjacencyList.get(vertex)) {
            if (!visited.get(neighbor)) { 
                topologicalSortUtil(neighbor, stack);
            }
        }
        // Push the current vertex to the stack
        stack.push(vertex);
    }

    public static void main(String[] args) {
        TopologicalSort graph = new TopologicalSort();

        // Add vertices
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addVertex("G");
        graph.addVertex("H");
        graph.addVertex("I");
        graph.addVertex("S");
        graph.addVertex("t");

        // Add directed edges
        graph.addEdge("S", "G");
        graph.addEdge("S", "D");
        graph.addEdge("S", "A");
        graph.addEdge("A", "B");
        graph.addEdge("A", "E");
        graph.addEdge("D", "E");
        graph.addEdge("D", "A");
        graph.addEdge("G", "D");
        graph.addEdge("G", "E");
        graph.addEdge("G", "H");
        graph.addEdge("B", "C");
        graph.addEdge("H", "I");
        graph.addEdge("H", "E");
        graph.addEdge("E", "F");
        graph.addEdge("E", "C");
        graph.addEdge("E", "I");
        graph.addEdge("C", "t");
        graph.addEdge("F", "C");
        graph.addEdge("F", "t");
        graph.addEdge("I", "F");
        graph.addEdge("I", "t");

        // Perform topological sort
        List<String> topologicalOrder = graph.topologicalSort();

        // Print the result
        System.out.println("Topological Order: " + topologicalOrder);
    }
}
