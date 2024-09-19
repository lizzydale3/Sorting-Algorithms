def topological_sort_or_detect_cycle(graph):
    # Step 1: Initialize data structures
    visited = [False] * len(graph)  # Keeps track of visited nodes
    recursion_stack = [False] * len(graph)  # Stack to track nodes in the current DFS path
    topological_order = []  # List to store the topological order
    cycle = []  # List to store the cycle if found

    # Step 2: Helper function for DFS
    def dfs(node):
        nonlocal cycle  # Allows us to modify the 'cycle' variable outside the function scope
        visited[node] = True
        recursion_stack[node] = True

        # Explore all adjacent nodes
        for neighbor in graph[node]:
            if not visited[neighbor]:
                dfs(neighbor)
                if cycle:  # If a cycle is found, exit immediately
                    return
            elif recursion_stack[neighbor]:
                # Back edge detected, indicating a cycle
                cycle.append(neighbor)
                cycle.append(node)
                return

        # Step 3: Add node to topological order if no cycle is found
        recursion_stack[node] = False  # Remove node from recursion stack
        topological_order.append(node)  # Append node to topological order in reverse order

    # Step 4: Apply DFS to all nodes
    for node in range(len(graph)):
        if not visited[node]:
            dfs(node)
            if cycle:  # If a cycle is found, stop further processing
                break

    # Step 5: Check if a cycle is found or return the topological order
    if cycle:
        return cycle  # Return the cycle if found
    else:
        return topological_order[::-1]  # Return topological order in correct order


# Example graph as an adjacency list
graph = {
    0: [1],
    1: [2],
    2: [3],
    3: [],
    4: [1],
    5: [0, 2]
}

# Call the function and print the result
result = topological_sort_or_detect_cycle(graph)
if len(result) == 2:  # Indicates a cycle was found
    print("Cycle detected:", result)
else:
    print("Topological order:", result)
