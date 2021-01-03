package homework2;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import homework2.GraphExceptions.*;


/**
 * This class implements a testing driver which reads test scripts
 * from files for testing Graph and PathFinder.
 */
public class TestDriver {

	// String -> Graph: maps the names of graphs to the actual graph
  	private final Map<String,Graph<WeightedNode>> graphs = new HashMap<>();
  	// String -> WeightedNode: maps the names of nodes to the actual node
  	private final Map<String,WeightedNode> nodes = new HashMap<>();
	private final BufferedReader input;
  	private final PrintWriter output;


  	/**
  	 * Creates a new TestDriver.
     * @requires r != null && w != null
     * @effects Creates a new TestDriver which reads command from
     * <tt>r</tt> and writes results to <tt>w</tt>.
     */
  	public TestDriver(Reader r, Writer w) {
    	input = new BufferedReader(r);
    	output = new PrintWriter(w);
  	}


  	/**
  	 * Executes the commands read from the input and writes results to the
  	 * output.
     * @effects Executes the commands read from the input and writes
     * 		    results to the output.
     * @throws IOException - if the input or output sources encounter an
     * 		   IOException.
     */
  	public void runTests() throws IOException {

    	String inputLine;
		while ((inputLine = input.readLine()) != null) {
			// echo blank and comment lines
      		if (inputLine.trim().length() == 0 ||
      		    inputLine.charAt(0) == '#') {
        		output.println(inputLine);
        		continue;
      		}

      		// separate the input line on white space
      		StringTokenizer st = new StringTokenizer(inputLine);
      		if (st.hasMoreTokens()) {
        		String command = st.nextToken();

        		List<String> arguments = new ArrayList<>();
        		while (st.hasMoreTokens())
          			arguments.add(st.nextToken());

        		executeCommand(command, arguments);
      		}
    	}

    	output.flush();
  	}


  	private void executeCommand(String command, List<String> arguments) {

    	try {
      		if (command.equals("CreateGraph")) {
        		createGraph(arguments);
      		} else if (command.equals("CreateNode")) {
        		createNode(arguments);
      		} else if (command.equals("AddNode")) {
        		addNode(arguments);
      		} else if (command.equals("AddEdge")) {
        		addEdge(arguments);
      		} else if (command.equals("ListNodes")) {
        		listNodes(arguments);
      		} else if (command.equals("ListChildren")) {
        		listChildren(arguments);
      		} else if (command.equals("FindPath")) {
        		findPath(arguments);
      		} else {
        		output.println("Unrecognized command: " + command);
      		}
    	} catch (Exception e) {
      		output.println("Exception: " + e.toString());
    	}
  	}

	/**
	 * A wrapper function for createGraph().
	 * @requires arguments != null
	 * @effects Wrapper function - creates a new graph.
	 * @throws CommandException if the list of arguments passed contains no
	 * arguments or more than 1 argument.
	 */
	private void createGraph(List<String> arguments) {

    	if (arguments.size() != 1)
      		throw new CommandException(
				"Bad arguments to CreateGraph: " + arguments);

    	String graphName = arguments.get(0);
    	createGraph(graphName);
  	}

	/**
	 * Creates a new graph.
	 * @requires graphName != null
	 * @modifies graphs - adds a new graph to graphs.
	 * @effects Creates a new graph with the name graphName, adds it to
	 * graphs, and outputs the result of the operation.
	 */
  	private void createGraph(String graphName) {

  		graphs.put(graphName, new Graph<WeightedNode>(graphName));
  		output.println("created graph " + graphName);
  	}

	/**
	 * A wrapper function for createNode().
	 * @requires arguments != null
	 * @effects Wrapper function - creates a new node.
	 * @throws CommandException if arguments does not contain exactly 2
	 * arguments.
	 */
  	private void createNode(List<String> arguments) {

    	if (arguments.size() != 2)
      		throw new CommandException(
				"Bad arguments to createNode: " + arguments);

    	String nodeName = arguments.get(0);
    	String cost = arguments.get(1);
    	createNode(nodeName, cost);
  	}

	/**
	 * Creates a new node with the name nodeName and the cost cost, adds it to
	 * nodes, and outputs the result of the operation.
	 * @requires nodeName != null && cost != null
	 * @modifies nodes - adds the new node to nodes.
	 * @effects Creates a new node with the name nodeName and the cost cost,
	 * adds it to nodes, and outputs the result of the operation.
	 */
 	private void createNode(String nodeName, String cost) {
 		
 		nodes.put(nodeName, new WeightedNode(nodeName, Integer.parseInt(cost)));
 		output.println("created node " + nodeName + " with cost " + cost);
  	}

	/**
	 * A wrapper function for addNode().
	 * @requires arguments != null
	 * @effects Wrapper function - adds a new node.
	 * @throws CommandException if arguments does not contain exactly 2
	 * arguments.
	 */
  	private void addNode(List<String> arguments)
	{

    	if (arguments.size() != 2)
      		throw new CommandException(
				"Bad arguments to addNode: " + arguments);

    	String graphName = arguments.get(0);
    	String nodeName = arguments.get(1);
    	addNode(graphName, nodeName);
  	}

	/**
	 * Adds the node with the name nodeName to the graph graphName and outputs
	 * the result of the operation.
	 * @requires graphName != null && nodeName != null
	 * @modifies graph - adds the node to graphs.
	 * @effects Adds the node with the name nodeName to the graph graphName
	 * and outputs the result of the operation.
	 */
  	private void addNode(String graphName, String nodeName)
	{
  		 
  		Graph<WeightedNode> graph = graphs.get(graphName);
  		WeightedNode node = nodes.get(nodeName);
  		try
		{
			graph.AddNode(node);
			output.println("added node " + nodeName + " to " + graphName);
		}
  		catch (NodeAlreadyExistsException n)
		{
			output.println(n.getErrorDescription());
		}
  	}

	/**
	 * A wrapper function for addEdge().
	 * @requires arguments != null
	 * @effects Wrapper function - adds a new edge.
	 * @throws CommandException if arguments does not contain exactly 3
	 * arguments.
	 */
  	private void addEdge(List<String> arguments) {

    	if (arguments.size() != 3)
      		throw new CommandException(
				"Bad arguments to addEdge: " + arguments);

    	String graphName = arguments.get(0);
    	String parentName = arguments.get(1);
    	String childName = arguments.get(2);
    	addEdge(graphName, parentName, childName);
  	}

	/**
	 * Adds the edge beginning at node parentName and ending at node childName
	 * to the graph graphName and outputs the result of the operation.
	 * @requires graphName != null && parentName != null && childName != null
	 * @modifies graph - adds the edge to graphs.
	 * @effects Adds the edge beginning at node parentName and ending at node
	 * childName to the graph graphName and outputs the result of the operation.
	 */
	private void addEdge(String graphName, String parentName, String childName) {
		  
		Graph<WeightedNode> graph = graphs.get(graphName);
		WeightedNode parentNode = nodes.get(parentName);
		WeightedNode childNode = nodes.get(childName);
		try
		{
			graph.AddEdge(parentNode, childNode);
			output.println("added edge from " + parentName + " to " + childName
					+ " in " + graphName);
		}
		catch (NodeDoesNotExistException n)
		{
			output.println(n.getErrorDescription());
		}
		catch (EdgeAlreadyExistsException e)
		{
			output.println(e.getErrorDescription());
		}
	}

	/**
	 * A wrapper function for listNodes().
	 * @requires arguments != null
	 * @effects Wrapper function - lists all the nodes.
	 * @throws CommandException if arguments does not contain exactly 1
	 * argument.
	 */
  	private void listNodes(List<String> arguments) {

    	if (arguments.size() != 1)
      		throw new CommandException(
				"Bad arguments to listNodes: " + arguments);

    	String graphName = arguments.get(0);
    	listNodes(graphName);
  	}

	/**
	 * Prints out a list of all the nodes contained in the graph graphName.
	 * @requires graphName != null
	 * @modifies none
	 * @effects Prints out a list of all the nodes contained in the graph
	 * graphName in alphabetical order.
	 */
  	private void listNodes(String graphName) {
  		   
  		Graph<WeightedNode> graph = graphs.get(graphName);

  		//	TODO: Check if implemented correctly - if prints in alphabetical
		//	order. I think it should work because getNodes() returns a
		//	TreeSet<>, and so the nodes should already be ordered.
  		Set<WeightedNode> nodes = graph.getNodes();

  		String initialString = graphName + " contains:";
  		StringBuffer nodesNames = new StringBuffer(initialString);
  		for(WeightedNode node : nodes)
		{
			nodesNames.append(" ").append(node.getName());
		}
  		output.println(nodesNames.toString());
  	}

	/**
	 * A wrapper function for listChildren().
	 * @requires arguments != null
	 * @effects Wrapper function - lists all the child nodes of a given node.
	 * @throws CommandException if arguments does not contain exactly 2
	 * arguments.
	 */
  	private void listChildren(List<String> arguments) {

    	if (arguments.size() != 2)
      		throw new CommandException(
				"Bad arguments to listChildren: " + arguments);

    	String graphName = arguments.get(0);
    	String parentName = arguments.get(1);
    	listChildren(graphName, parentName);
  	}

	/**
	 * Prints out a list of all the nodes that are children of the node
	 * parentName contained in the graph graphName.
	 * @requires graphName != null && parentName != null
	 * @modifies none
	 * @effects Prints out a list of all the nodes that are children of the
	 * node parentName contained in the graph graphName in alphabetical order.
	 */
  	private void listChildren(String graphName, String parentName) {
  		    
  		Graph<WeightedNode> graph = graphs.get(graphName);
  		WeightedNode parentNode = nodes.get(parentName);

  		try
		{
			//	TODO: Check if implemented correctly - if prints in alphabetical
			//	order. I think it should work because getNodes() returns a
			//	TreeSet<>, and so the nodes should already be ordered.
			Set<WeightedNode> childNodes = graph.getChildNodes(parentNode);
			String initialString =
					"the children of " + parentName + " in " + graphName + " " +
							"are:";
			StringBuffer childNodesNames = new StringBuffer(initialString);
			for(WeightedNode childNode : childNodes)
			{
				childNodesNames.append(" ").append(childNode.getName());
			}
			output.println(childNodesNames.toString());
		}
		catch (NodeDoesNotExistException n)
		{
			output.println(n.getErrorDescription());
		}
  	}

	//TODO add spec
  	private void findPath(List<String> arguments) {

    	String graphName;
    	List<String> sourceArgs = new ArrayList<>();
    	List<String> destArgs = new ArrayList<>();

    	if (arguments.size() < 1)
      		throw new CommandException(
				"Bad arguments to FindPath: " + arguments);

    	Iterator<String> iter = arguments.iterator();
    	graphName = iter.next();

		// extract source arguments
    	while (iter.hasNext()) {
      		String s = iter.next();
      		if (s.equals("->"))
        		break;
      		sourceArgs.add(s);
    	}

		// extract destination arguments
    	while (iter.hasNext())
      		destArgs.add(iter.next());

    	if (sourceArgs.size() < 1)
      		throw new CommandException(
				"Too few source args for FindPath");

    	if (destArgs.size() < 1)
      		throw new CommandException(
				"Too few dest args for FindPath");

    	findPath(graphName, sourceArgs, destArgs);
  	}


  	private void findPath(String graphName, List<String> sourceArgs,
  						  List<String> destArgs) {
  		
  		// TODO: Insert your code here.
  		   
  		// ___ = graphs.get(graphName);
  		// ___ = nodes.get(sourceArgs.get(i));
  		// ___ = nodes.get(destArgs.get(i));
  		// output.println(...);
		
  	}

	//TODO add spec
	private static void printUsage() {
		System.err.println("Usage:");
		System.err.println("to read from a file: java TestDriver <name of input script>");
		System.err.println("to read from standard input: java TestDriver");
	}


	public static void main(String args[]) {

		try {
			// check for correct number of arguments
			if (args.length > 1) {
				printUsage();
				return;
			}

			TestDriver td;
			if (args.length == 0)
				// no arguments - read from standard input
				td = new TestDriver(new InputStreamReader(System.in),
								    new OutputStreamWriter(System.out));
			else {
				// one argument - read from file
				java.nio.file.Path testsFile = Paths.get(args[0]);
				if (Files.exists(testsFile) && Files.isReadable(testsFile)) {
					td = new TestDriver(
							Files.newBufferedReader(testsFile, Charset.forName("US-ASCII")),
							new OutputStreamWriter(System.out));
				} else {
					System.err.println("Cannot read from " + testsFile.toString());
					printUsage();
					return;
				}
			}

			td.runTests();

		} catch (IOException e) {
			System.err.println(e.toString());
			e.printStackTrace(System.err);
		}
	}
}


/**
 * This exception results when the input file cannot be parsed properly.
 */
class CommandException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CommandException() {
		super();
  	}

  	public CommandException(String s) {
		super(s);
  	}
}