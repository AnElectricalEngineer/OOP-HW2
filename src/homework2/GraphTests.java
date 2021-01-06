package homework2;

import static org.junit.Assert.*;

import junit.framework.TestResult;
import org.junit.Test;
import homework2.GraphExceptions.*;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

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

	@Test
	public void addNodeTest1() throws NodeAlreadyExistsException
	{
		Graph<WeightedNode> graph1 = new Graph<>("G1");
		WeightedNode n1 = new WeightedNode("n1", 1);
		graph1.addNode(n1);
		Set<WeightedNode> expected = new TreeSet<WeightedNode>();
		expected.add(n1);
		assertEquals("addNode() test 1", expected, graph1.getNodes());
	}

	@Test(expected = NodeAlreadyExistsException.class)
	public void addNodeTest2() throws NodeAlreadyExistsException
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

	@Test
	public void addEdgeTest4() throws NodeAlreadyExistsException,
			EdgeAlreadyExistsException, NodeDoesNotExistException
	{
		Graph<WeightedNode> graph1 = new Graph<>("G1");

		WeightedNode n1 = new WeightedNode("n1", 1);
		WeightedNode n2 = new WeightedNode("n2", 2);

		graph1.addNode(n1);
		graph1.addNode(n2);
		graph1.addEdge(n1, n2);
		Set<WeightedNode> expected = new TreeSet<WeightedNode>();
		expected.add(n2);
		assertEquals("addEdge() test 4", expected, graph1.getChildNodes(n1));
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

	@Test(expected = NodeDoesNotExistException.class)
	public void shortestPathTest1() throws NodeAlreadyExistsException,
			EdgeAlreadyExistsException, NodeDoesNotExistException
	{
		Graph<WeightedNode> graph1 = new Graph<>("G1");

		WeightedNode n1 = new WeightedNode("n1", 1);
		WeightedNode n2 = new WeightedNode("n2", 2);
		WeightedNode n3 = new WeightedNode("n3", 2);

		graph1.addNode(n1);
		graph1.addNode(n2);
		graph1.addEdge(n1, n2);

		WeightedNodePath pathN1 = new WeightedNodePath(n1);
		WeightedNodePath pathN2 = new WeightedNodePath(n3);

		Set<WeightedNodePath> pathN1Set = new HashSet<WeightedNodePath>();
		pathN1Set.add(pathN1);

		Set<WeightedNodePath> pathN2Set = new HashSet<WeightedNodePath>();
		pathN1Set.add(pathN2);

		PathFinder<WeightedNode, WeightedNodePath> pF1 = new PathFinder(graph1);
		pF1.shortestPath(pathN1Set, pathN2Set);
	}

	@Test(expected = NodeDoesNotExistException.class)
	public void shortestPathTest2() throws NodeAlreadyExistsException,
			EdgeAlreadyExistsException, NodeDoesNotExistException
	{
		Graph<WeightedNode> graph1 = new Graph<>("G1");

		WeightedNode n1 = new WeightedNode("n1", 1);
		WeightedNode n2 = new WeightedNode("n2", 2);
		WeightedNode n3 = new WeightedNode("n3", 2);

		graph1.addNode(n1);
		graph1.addNode(n2);
		graph1.addEdge(n1, n2);

		WeightedNodePath pathN1 = new WeightedNodePath(n3);
		WeightedNodePath pathN2 = new WeightedNodePath(n1);

		Set<WeightedNodePath> pathN1Set = new HashSet<WeightedNodePath>();
		pathN1Set.add(pathN1);

		Set<WeightedNodePath> pathN2Set = new HashSet<WeightedNodePath>();
		pathN1Set.add(pathN2);

		PathFinder<WeightedNode, WeightedNodePath> pF1 = new PathFinder(graph1);
		pF1.shortestPath(pathN1Set, pathN2Set);
	}

	@Test
	public void shortestPathTest3() throws NodeAlreadyExistsException,
			EdgeAlreadyExistsException, NodeDoesNotExistException
	{
		Graph<WeightedNode> G1 = new Graph<>("G1");

		WeightedNode a = new WeightedNode("a", 2);
		WeightedNode b = new WeightedNode("b", 1);
		WeightedNode c = new WeightedNode("c", 3);
		WeightedNode d = new WeightedNode("d", 1);
		WeightedNode e = new WeightedNode("e", 4);

		G1.addNode(a);
		G1.addNode(b);
		G1.addNode(c);
		G1.addNode(d);
		G1.addNode(e);
		G1.addEdge(a, b);
		G1.addEdge(a, c);
		G1.addEdge(b, c);
		G1.addEdge(b, d);
		G1.addEdge(c, d);
		G1.addEdge(c, e);
		G1.addEdge(d, e);

		WeightedNodePath startNodePath = new WeightedNodePath(a);
		WeightedNodePath goalNodePath = new WeightedNodePath(e);

		Set<WeightedNodePath> startNodePathSet =
				new HashSet<WeightedNodePath>();
		startNodePathSet.add(startNodePath);

		Set<WeightedNodePath> goalNodePathSet =
				new HashSet<WeightedNodePath>();
		goalNodePathSet.add(goalNodePath);

		PathFinder<WeightedNode, WeightedNodePath> pF1 = new PathFinder(G1);
		WeightedNodePath actualPath = pF1.shortestPath(startNodePathSet,
			goalNodePathSet);

		WeightedNodePath desiredPath = new WeightedNodePath(a);
		desiredPath = desiredPath.extend(b);
		desiredPath = desiredPath.extend(d);
		desiredPath= desiredPath.extend(e);

		assertEquals("If and For test", desiredPath, actualPath);
	}

}
