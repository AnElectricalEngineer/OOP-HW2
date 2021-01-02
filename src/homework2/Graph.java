package homework2;

import java.util.*;

import homework2.GraphExceptions.*;

/**
 * A Graph is a mutable model which represents a set of points and the
 * connections between them. A Graph contains a number of nodes {V}, some of
 * which may be connected by edges {E}. Each edge e = (v1,v2) has a direction,
 * which means that the edge (v1,v2) is different from the edge (v2, v1).
 * A Graph cannot have more than one edge which connects a specific node to
 * another.
 *
 * Each node in a Graph can have multiple children and parents. A child of node
 * v is a node u such that there is an edge FROM v to u. In other words, node
 * u is a child of node v if there exists an edge e such that e = (v, u). A
 * parent of node v is a node u such that there is an edge from u to v. In
 * other words, node u is a parent of node v if there exists an edge e such
 * that e = (u, v).
 */

public class Graph<N extends Comparable<N>>
{

    //	Abstraction Function:
    //  A Graph is a mutable model which represents a set of points and the
    //  connections between them. A Graph has a name represented by
    //  graphName_, and contains a number of nodes held as keys in
    //  nodesAndEdges_. The edges that connect parent nodes to child nodes are
    //  represented as sets of child nodes and are the values in
    //  nodesAndEdges_ that each key (parentNode) is mapped to.

    //	Representation Invariant:
    //  For every Graph g, g.graphName_ != null && g.graphName_ != "" && each
    //  node in g != null and each node in g is unique. For each pair of nodes
    //  n_i, n_j in g, no more than one edge connects n_i to n_j. Each edge
    //  must be represented by two nodes that exist in the graph.


    private final Map<N, Set<N>> nodesAndEdges_;
    private final String graphName_;

    /**
     * Constructs a new Graph.
     * @requires graphName != null
     * @modifies this
     * @effects Constructs a new, empty Graph such that Graph.graphName_ =
     * graphName.
     **/
    public Graph(String graphName)
    {
        nodesAndEdges_ = new HashMap<>();
        graphName_ = graphName;
        checkRep();
    }

    /**
     * Returns the name of the graph.
     * @requires none
     * @modifies none
     * @effects returns the name of the graph.
     **/
    public String getName()
    {
        checkRep();
        return graphName_;
    }

    /**
     * Adds a node to the Graph.
     * @requires node != null
     * @modifies this
     * @effects adds node to the Graph.
     * @throws GraphExceptions.NodeAlreadyExistsException if Graph already
     * contains node (or any node with same hash code).
     **/
    public void AddNode(N node) throws NodeAlreadyExistsException
    {
        checkRep();

        //  Check precondition
        assert node != null;
        //TODO write that we assumed Nodes are immutable
        if(nodesAndEdges_.containsKey(node))
        {
            System.out.println("Node already exists in graph.");
            throw new NodeAlreadyExistsException();
        }
        nodesAndEdges_.put(node, new HashSet<>());
        checkRep();
    }

    /**
     * Adds an edge to the Graph.
     * @requires parentNode != null && childNode != null
     * @modifies this
     * @effects adds an edge to the Graph which connects parentNode to
     * childNode.
     * @throws NodeDoesNotExistException if Graph does not contain parentNode
     * or childNode.
     * @throws EdgeAlreadyExistsException if Graph already contains the edge
     * connecting parentNode to childNode.
     **/
    public void AddEdge(N parentNode, N childNode) throws
            NodeDoesNotExistException, EdgeAlreadyExistsException
    {
        checkRep();

        //  Check preconditions
        assert parentNode != null : "Parent Node is null.";
        assert childNode != null : "Child Node is null.";

        //  Check if both parent and child nodes exist in graph
        if(!nodesAndEdges_.containsKey(parentNode) ||
                !(nodesAndEdges_.containsKey(childNode)))
        {
            System.out.println("Cannot add edge because one or both of the " +
                    "nodes does not exist.");
            throw new NodeDoesNotExistException();
        }

        //  Check if edge already exists in graph
        if(nodesAndEdges_.get(parentNode).contains(childNode))
        {
            System.out.println("Edge Already exists in graph.");
            throw new EdgeAlreadyExistsException();
        }

        //  Add the edge
        nodesAndEdges_.get(parentNode).add(childNode);
        checkRep();
    }

    /**
     * Returns a Set containing all of the nodes in Graph.
     * @requires none
     * @modifies none
     * @effects Returns a Set containing all of the nodes in Graph in
     * sorted order.
     **/
    public Set<N> getNodes()
    {
        checkRep();
        Set<N> nodes = new TreeSet<>(nodesAndEdges_.keySet());
        checkRep();
        return nodes;   //Nodes are assumed to be immutable so no rep. exposure.
    }

    /**
     * Returns a Set containing all of the nodes in Graph which are children
     * of parentNode in a sorted order.
     * @requires parentNode != null
     * @modifies none
     * @effects Returns a Set containing all of the nodes in Graph which are
     * children of parentNode.
     * @throws NodeDoesNotExistException if Graph does not contain parentNode.
     **/
    public Set<N> getChildNodes(N parentNode) throws NodeDoesNotExistException
    {
        checkRep();

        //  Check precondition
        assert parentNode != null : "Null node.";

        if(!nodesAndEdges_.containsKey(parentNode))
        {
            throw new NodeDoesNotExistException();
        }

        Set<N> childNodes = new TreeSet<>(nodesAndEdges_.get(parentNode));
        checkRep();

        //Nodes are assumed to be immutable so no rep. exposure.
        return childNodes;
    }

    //TODO check if need to implement
    /**
     * Returns a hash code for this.
     * @return a hash code for this.
     **/
/*    @Override
    public int hashCode()
    {
        checkRep();
        checkRep();
    }*/

    //TODO check if need to implement
    /**
     * Returns a string representation of this.
     * @return a string representation of this.
     **/
    /*@Override
    public String toString()
    {
        checkRep();
        checkRep();
    }*/

    //TODO check if need to implement
    /**
     * Compares the argument with this Graph for equality.
     * @return o != null && (o instanceof //TODO check if Graph or Graph<N>)
     * or other &&
     * (o.nodesAndEdges_ and this.nodesAndEdges_ contain
     * the same nodes with the same edges).
     **/
    /*@Override
    public boolean equals(Object o)
    {
        checkRep();
        checkRep();
    }*/

    /**
     * Checks that the representation invariant is maintained. The
     * implementation chosen to hold nodes (HashMap) guarantees that nodes are
     * unique. A Hashmap was also chosen to hold child nodes which guarantees
     * that edges are unique. Therefore these facets of the representation
     * invariant are not checked here.
     */
    private void checkRep()
    {
        assert graphName_ != null : "Null Graph name.";

        //  Use of trim() assures that names of the form "   " are caught.
        assert !graphName_.trim().isEmpty() : "Empty Graph name.";

        //  Check that no nodes are null
        for( N node : nodesAndEdges_.keySet())
        {
            assert node != null : "Null node.";
            /*  For each node in the graph, check that each edge connecting
            that node to a child node points to a child node contained in the
            graph. */
            for ( N childNode : nodesAndEdges_.get(node))
            {
                assert nodesAndEdges_.containsKey(childNode) : "Error: The " +
                        "node " + node + " is connected by an edge" +
                        " to the node " + childNode + " which does not exist " +
                        "in the graph.";
            }
        }
    }

}
