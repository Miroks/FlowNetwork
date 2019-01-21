package com.flow.network.app.graph;

import org.junit.jupiter.api.Test;
import org.testng.collections.Sets;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static com.flow.network.app.graph.FlowNetworkAlgorithm.executeFordFulkerson;
import static org.junit.jupiter.api.Assertions.*;

class FlowNetworkAlgorithmTest {

    @Test
    public void executeFordFulkersonTest(){

        Vertex s = new Vertex(1);
        Vertex v1 = new Vertex(2);
        Vertex v2 = new Vertex(3);
        Vertex t = new Vertex(4);
        Edge e1 = new Edge(s, v1, 6);
        Edge e2 = new Edge(s, v2, 13);
        Edge e3 = new Edge(v2, v1, 1);
        Edge e4 = new Edge(v1, t, 8);
        Edge e5 = new Edge(v2, t, 5);
        List<Edge> ed = new LinkedList();
        ed.add(e1);
        ed.add(e2);
        ed.add(e3);
        ed.add(e4);
        ed.add(e5);
        Set<Vertex> ver = new HashSet<>();
        ver.add(s);
        ver.add(v1);
        ver.add(v2);
        ver.add(t);
        Graph g = new Graph(ed,ver);

        assertEquals(12,executeFordFulkerson(g,s,t));
    }

}