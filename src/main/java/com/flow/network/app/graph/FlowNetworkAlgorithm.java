package com.flow.network.app.graph;

import java.util.*;

public class FlowNetworkAlgorithm {

    public static int executeFordFulkerson(Graph graph, Vertex startVertex, Vertex endVertex) {
        int maxFlow = 0;
        Map<Edge, Integer> edges = graph.getWeightToEdgeMapping();
        Path path = bfs(edges, graph.getVertices(), startVertex, endVertex);
        while(path.pathExists()) {
            int pathFlow = Integer.MAX_VALUE;
            for(Vertex v = endVertex; v!=startVertex; v = path.getParent().get(v)) {
                Vertex u = path.getParent().get(v);
                Edge currentEdge = new Edge(u, v);
                pathFlow = Math.min(pathFlow, edges.get(currentEdge));
            }
            for(Vertex v=endVertex; v!=startVertex; v = path.getParent().get(v)){
                Vertex u = path.getParent().get(v);
                Edge e1 = new Edge(u, v);
                e1.setWeight(edges.getOrDefault(e1, 0) - pathFlow);
                Edge e2 = new Edge(v, u);
                e2.setWeight(edges.getOrDefault(e2, 0) + pathFlow);
                edges.put(e1, e1.getWeight());
                edges.put(e2, e2.getWeight());
            }
            maxFlow += pathFlow;
            path = bfs(edges, graph.getVertices(), startVertex, endVertex);
        }
        return maxFlow;
    }

    private static Path bfs(Map<Edge, Integer> edges, Set<Vertex> vertices, Vertex startVertex, Vertex endVertex) {
        Path path = new Path(startVertex);
        Set<Vertex> visitedVertices = new HashSet<>();
        Queue<Vertex> queue = new LinkedList<>();
        queue.add(startVertex);
        visitedVertices.add(startVertex);

        while(!queue.isEmpty()) {
            Vertex currentVertex = queue.poll();
            for (Vertex v : vertices){
                Edge currentEdge = new Edge(currentVertex, v);
                if(!visitedVertices.contains(v) && edges.getOrDefault(currentEdge, 0) > 0) {
                    queue.add(v);
                    path.addToPath(v, currentVertex);
                    visitedVertices.add(v);
                }
            }
        }
        if(!visitedVertices.contains(endVertex)) {
            path.clearPath();
        }
        return path;
    }

}
