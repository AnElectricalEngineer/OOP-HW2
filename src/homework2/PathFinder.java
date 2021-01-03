package homework2;

import java.util.*;

public class PathFinder<N, P extends Path<N, P>>
{
    private final Graph<N> graph_;
    private Map<N, P> paths_;
    private Set<N> finished_;

    public PathFinder(Graph<N> graph)
    {
        graph_ = graph;
    }

    public P shortestPath(P startNodes, P goalNodes)
    {

    }
}
