package homework2;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

    //	//TODO Abstraction Function:
    //	A GeoFeature is a geographical feature composed of a positive number of
    //	connected GeoSegments with the same name. The feature begins at
    //	GeoFeature.getFirst().getP1() (the first point of the first segment in
    //	the feature). The feature ends at GeoFeature.getLast().getP2() (the
    //  second point of the last segment in the feature).

    //	//TODO Representation Invariant:
    //	For every GeoFeature f, f.name_ != null && 0 <= f.startHeading_ < 360
    //	&& 0 <= f.endHeading_ < 360 && for all GeoSegments s_i,
    //	s_i.endPoint == s_i+1.startPoint && for all GeoSegments s
    //	in a GeoFeature f,	it must hold that s.name_ == f.name_.


    private final Map<N, Set<N>> nodesAndEdges_;
    private final String graphName_;

    /**
     * Constructs a new Graph.
     * @requires graphName != null
     * @modifies this
     * @effects Constructs a new empty Graph such that Graph.graphName_ =
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

    //TODO Maybe add throws like tal
    /**
     * Adds a node to the Graph.
     * @requires node != null && Graph does not already contain node (or any
     * node with same hashCode).
     * @modifies this
     * @effects adds node to the Graph.
     **/
    public void AddNode(N node)
    {
        checkRep();
        checkRep();
    }

    //TODO Maybe add throws like tal
    /**
     * Adds an edge to the Graph.
     * @requires parentNode != null && childNode != null && Graph contains
     * parentNode && Graph contains childNode && Graph does not already
     * contain same edge.
     * @modifies this
     * @effects adds an edge to the Graph which connects parentNode to
     * childNode.
     **/
    public void AddEdge(N parentNode, N childNode)
    {
        checkRep();
        checkRep();
    }

    //TODO check if should be void and just print or should return set like Tal
    /**
     * Prints a list of the nodes contained in Graph.
     * @requires none
     * @modifies none
     * @effects Prints the name of the graph followed by the names of all the
     * nodes contained in Graph in alphabetical order.
     **/
    public void ListNodes()
    {
        checkRep();
        checkRep();
    }

    //TODO check if should be void and just print or should return set like Tal
    /**
     * Prints a list of the nodes contained in Graph that are children of
     * parentNode.
     * @requires parentNode != null && parentNode is contained in Graph.
     * @modifies none
     * @effects Prints the name of parentNode and the name of the graph,
     * followed by the names of the child nodes of parentNode in alphabetical
     * order.
     **/
    public void ListChildren(N parentNode)
    {
        checkRep();
        checkRep();
    }

    //TODO check if need to implement
    /**
     * Returns a hash code for this.
     * @return a hash code for this.
     **/
    @Override
    public int hashCode()
    {
        checkRep();
        checkRep();
    }

    //TODO check if need to implement
    /**
     * Returns a string representation of this.
     * @return a string representation of this.
     **/
    @Override
    public String toString()
    {
        checkRep();
        checkRep();
    }

    //TODO check if need to implement
    /**
     * Compares the argument with this GeoFeature for equality.
     * @return o != null && (o instanceof GeoFeature) &&
     *         (o.geoSegments and this.geoSegments contain
     *          the same elements in the same order).
     **/
    @Override
    public boolean equals(Object o)
    {
        checkRep();
        checkRep();
    }

    /**
     * Checks that the representation invariant is maintained.
     */
    private void checkRep()
    {
    }

}
