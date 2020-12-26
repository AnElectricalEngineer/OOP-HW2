package homework2;


import java.util.LinkedList;
import java.util.List;

/**
 * A Graph is an immutable model which represents a set of points and the
 * connections between them. A Graph contains a number of nodes {V}, some of
 * which may be connected by edges {E}. Each edge e = (v1,v2) has a direction,
 * which means that the edge (v1,v2) is different from the edge (v2, v1).
 * //TODO ensure that Graph is immutable and explain why in external doc.
 *
 * A Graph cannot have more than one edge which connects a specific node to
 * another - in other words: if e1 = (v1, v2), e2 = (v3, v4), e1 != e2 => (v1
 * != v3) || (v2 != v4) || (v1 != v3 && v2 != v4). //TODO check if makes sense.
 *
 * Each node in a Graph can have multiple children and parents. A child of node
 * v is a node u such that there is an edge FROM v to u. In other words, node
 * u is a child of node v if there exists an edge e such that e = (v, u). A
 * parent of node v is a node u such that there is an edge from u to v. In
 * other words, node u is a parent of node v if there exists an edge e such
 * that e = (u, v).
 */

public class Graph<N>
{
    private final List<N> nodeList_;
    private String graphName_;
    //TODO decide how to store nodes (use generic list?)

    /**
     * Constructs a new Graph.
     * @requires graphName != null
     * @modifies this
     * @effects Constructs a new empty Graph such that Graph.graphName =
     * graphName.
     **/
    public Graph(String graphName)
    {
        nodeList_ = new LinkedList<>();
        graphName_ = graphName;
    }

    /*public <N, C> CreateNode(N nodeName, C cost)
    {}*/

    /**
     * Adds a node to the Graph.
     * @requires node != null
     * @modifies this
     * @effects adds node to the Graph.
     **/
    public void AddNode(N node)
    {}

    /**
     * Adds an edge to the Graph.
     * @requires parentNode != null && childNode != null && Graph contains
     * parentNode && Graph contains childNode
     * @modifies this
     * @effects adds an edge to the Graph which connects parentNode to
     * childNode.
     **/
    public void AddEdge(N parentNode, N childNode)
    {}

    public ListNodes()
    {}

    public ListChildren()
    {}

    public FindPath()
    {}
    //TODO maybe add hashcode(), toString(), and equals().
}
