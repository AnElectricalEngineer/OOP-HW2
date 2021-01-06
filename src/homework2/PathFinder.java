package homework2;

import java.util.*;
import homework2.GraphExceptions.*;

/**
 * A PathFinder is an object which can be used to find the shortest path
 * between nodes in a weighted, directed graph with no loops. A PathFinder
 * contains a method shortestPath() which returns the shortest path between any
 * node in a group of starting nodes and any node in a group of goal nodes, or
 * null if no such path exists. The starting and goal nodes should be paths
 * comprised of a single node.
 */

 public class PathFinder<N, P extends Path<N, P>>
{
    // the graph containing the nodes
    private final Graph<N> graph_;

    /* A map which maps each starting node n to the shortest path p from that
     node. */
    private final Map<N, P> paths_;

    /* A priority queue which contains paths p whose priorities are equal to
    the cost of the shortest path to reach the LAST NODE in each path p. */
    private final PriorityQueue<P> active_;

    /* A set of all the nodes n for which the shortest path to each n from
    all start nodes is known, and whose children have already been examined. */
    private final Set<N> finished_;

    /**
     * Creates a new PathFinder object for a Graph graph.
     * @requires graph != null && graph is directed and contains at least one
     * node && all of the nodes in graph have non-negative weights and graph
     * contains no loops.
     * @modifies this
     * @effects Creates a new PathFinder object for a Graph graph.
     */
    public PathFinder(Graph<N> graph)
    {
        graph_ = graph;
        paths_ = new HashMap<>();
        active_ = new PriorityQueue<>();
        finished_ = new HashSet<>();
    }

    /**
     * Finds and returns the shortest path between any node in a group of
     * starting nodes and any node in a group of goal nodes, or null if such
     * a path does not exist. The groups of starting and goal nodes must be
     * groups of single-node paths and all nodes must be contained in this
     * .graph.
     * @requires startNodes != null and goalNodes != null && startNodes
     * contains at least one node, and goal nodes contains at least one node
     * && this.graph contains all nodes in startNodes && this.graph contains
     * all nodes in goalNodes.
     * @modifies this.paths, this.active, this.finished.
     * @effects Finds and returns the shortest path between any node in a
     * group of starting nodes and any node in a group of goal nodes, or null
     * if such a path does not exist.
     * @throws NodeDoesNotExistException if one of the start nodes or goal
     * nodes does not exist in graph.
     */
    public P shortestPath(Set<P> startNodes, Set<P> goalNodes) throws
            NodeDoesNotExistException
    {
        //Get set of nodes in graph
        Set<N> graphNodes = graph_.getNodes();

        /* For all start nodes, map each start node to an initial path
        comprised only of that node. Additionally, add each starting node
        (path) to the priority queue 'active'. */
        for(P nodeIterator : startNodes)
        {
            paths_.put(nodeIterator.getEnd(), nodeIterator);
            active_.add(nodeIterator);

            //Check that all start nodes are contained in graph.
            if(!graphNodes.contains(nodeIterator.getEnd()))
            {
                throw new NodeDoesNotExistException("Start node does not " +
                        "exist.");
            }
        }

        for(P nodeIterator : goalNodes)
        {
            //Check that all goal nodes are contained in graph.
            if(!graphNodes.contains(nodeIterator.getEnd()))
            {
                throw new NodeDoesNotExistException("Goal node does not " +
                        "exist.");
            }
        }

        while(!active_.isEmpty())
        {
            // Get the node in active with the shortest path
            N queueMin = active_.poll().getEnd();

            // Get the path to the node with the shortest path
            P queueMinPath = paths_.get(queueMin);

            // Check if node queueMin is one of the goal nodes
            for (P goalNode : goalNodes)
            {
                // If queueMin is a goal node, finished
                if (goalNode.getEnd().equals(queueMin))
                {
                    return queueMinPath;
                }
            }

            // Iterate over all child nodes of queueMin
            Set<N> childNodes = graph_.getChildNodes(queueMin);
            for(N childNode : childNodes)
            {
                P cPath = queueMinPath.extend(childNode);

                // Check if finished with childNode or if childNode is active
                if(!finished_.contains(childNode))
                {
                    boolean childNodeIsActive = false;
                    for(P path : active_)
                    {
                        if(path.getEnd().equals(childNode))
                        {
                            childNodeIsActive = true;
                            break;
                        }
                    }
                    if(!childNodeIsActive)
                    {
                        paths_.put(childNode, cPath);
                        active_.add(cPath);
                    }
                }
            }
            finished_.add(queueMin);
        }
        return null;
    }
}