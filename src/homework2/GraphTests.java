package homework2;

import static org.junit.Assert.*;
import org.junit.Test;
import homework2.GraphExceptions.*;

/**
 * This class contains a set of test cases that can be used to test the graph
 * and shortest path finding algorithm implementations of homework assignment
 * #2.
 */
public class GraphTests extends ScriptFileTests {

	// black-box test are inherited from super
	public GraphTests(java.nio.file.Path testFile) {
		super(testFile);
	}

	// Defines white box tests for the Graph class

	@Test(expected = NodeAlreadyExistsException.class)
	public void addNodeTest() throws NodeAlreadyExistsException
	{
		Graph<WeightedNode> graph1 = new Graph<>("G1");

		WeightedNode n1 = new WeightedNode("n1", 1);
		WeightedNode n2 = new WeightedNode("n1", 1);

		graph1.addNode(n1);
		graph1.addNode(n2);
	}

	@Test(expected = NodeDoesNotExistException.class)
	public void addEdgeTest1() throws NodeAlreadyExistsException,
			EdgeAlreadyExistsException, NodeDoesNotExistException
	{
		Graph<WeightedNode> graph1 = new Graph<>("G1");

		WeightedNode n1 = new WeightedNode("n1", 1);
		WeightedNode n2 = new WeightedNode("n2", 2);

		graph1.addNode(n1);
		graph1.addEdge(n1, n2);
	}

	@Test(expected = NodeDoesNotExistException.class)
	public void addEdgeTest2() throws NodeAlreadyExistsException,
			EdgeAlreadyExistsException, NodeDoesNotExistException
	{
		Graph<WeightedNode> graph1 = new Graph<>("G1");

		WeightedNode n1 = new WeightedNode("n1", 1);
		WeightedNode n2 = new WeightedNode("n2", 2);
		WeightedNode n3 = new WeightedNode("n3", 2);

		graph1.addNode(n1);
		graph1.addEdge(n2, n3);
	}

	@Test(expected = EdgeAlreadyExistsException.class)
	public void addEdgeTest3() throws NodeAlreadyExistsException,
			EdgeAlreadyExistsException, NodeDoesNotExistException
	{
		Graph<WeightedNode> graph1 = new Graph<>("G1");

		WeightedNode n1 = new WeightedNode("n1", 1);
		WeightedNode n2 = new WeightedNode("n2", 2);

		graph1.addNode(n1);
		graph1.addNode(n2);
		graph1.addEdge(n1, n2);
		graph1.addEdge(n1, n2);
	}

	@Test(expected = NodeDoesNotExistException.class)
	public void getChildNodesTest() throws NodeAlreadyExistsException,
			EdgeAlreadyExistsException, NodeDoesNotExistException
	{
		Graph<WeightedNode> graph1 = new Graph<>("G1");

		WeightedNode n1 = new WeightedNode("n1", 1);
		WeightedNode n2 = new WeightedNode("n2", 2);
		WeightedNode n3 = new WeightedNode("n3", 2);

		graph1.addNode(n1);
		graph1.addNode(n2);
		graph1.addEdge(n1, n2);
		graph1.getChildNodes(n3);
	}

}
