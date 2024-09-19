from collections import defaultdict, deque

# Function to check if the graph is bipartite and thus the judgments are consistent
def is_consistent(graph, n):
    # Initialize all nodes as uncolored (-1 means uncolored)
    colors = [-1] * (n + 1)
    
    # Function to perform BFS and color the graph
    def bfs_check(start):
        queue = deque([start])
        colors[start] = 0  # Start coloring with 0 (Species A)
        
        while queue:
            node = queue.popleft()
            current_color = colors[node]
            
            for neighbor, relation in graph[node]:
                if colors[neighbor] == -1:  # If not colored
                    if relation == 0:
                        colors[neighbor] = current_color  # Same color
                    else:
                        colors[neighbor] = 1 - current_color  # Opposite color
                    queue.append(neighbor)
                elif relation == 0 and colors[neighbor] != current_color:
                    return False  # Conflict: should be the same color but it's not
                elif relation == 1 and colors[neighbor] == current_color:
                    return False  # Conflict: should be different but it's the same
        
        return True  # No conflict found

    # Check each component of the graph
    for i in range(1, n + 1):
        if colors[i] == -1:  # Unvisited node
            if not bfs_check(i):  # If any component is inconsistent
                return False
    return True

# Example input graph
graph = defaultdict(list)
# Judgments format: (node1, node2, relation), where relation is 0 for "same" and 1 for "different"
judgments = [
    (1, 2, 0),  # 1 ↔ 2 (same)
    (2, 3, 1),  # 2 ↔ 3 (different)
    (3, 4, 0),  # 3 ↔ 4 (same)
    (4, 1, 1)   # 4 ↔ 1 (different)
]

# Build the graph from the judgments
for u, v, relation in judgments:
    graph[u].append((v, relation))
    graph[v].append((u, relation))

# Number of butterflies (nodes)
n = 4

# Check consistency
result = is_consistent(graph, n)
print("Consistent" if result else "Inconsistent")
