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


	private void createGraph(List<String> arguments) {

    	if (arguments.size() != 1)
      		throw new CommandException(
				"Bad arguments to CreateGraph: " + arguments);

    	String graphName = arguments.get(0);
    	createGraph(graphName);
  	}


  	private void createGraph(String graphName) {
  		
  		//TODO: Insert your code here. - did
		//TODO for this entire file - check if need to add spec like Tal
  		
  		graphs.put(graphName, new Graph<WeightedNode>(graphName));
  		output.println("created graph " + graphName);
  	}
 
  	
  	private void createNode(List<String> arguments) {

    	if (arguments.size() != 2)
      		throw new CommandException(
				"Bad arguments to createNode: " + arguments);

    	String nodeName = arguments.get(0);
    	String cost = arguments.get(1);
    	createNode(nodeName, cost);
  	}


 	private void createNode(String nodeName, String cost) {

 		// TODO: Insert your code here. - did
 		
 		nodes.put(nodeName, new WeightedNode(nodeName, Integer.parseInt(cost)));
 		output.println("created node " + nodeName + " with cost " + cost);
  	}
	

  	private void addNode(List<String> arguments)
	{

    	if (arguments.size() != 2)
      		throw new CommandException(
				"Bad arguments to addNode: " + arguments);

    	String graphName = arguments.get(0);
    	String nodeName = arguments.get(1);
    	addNode(graphName, nodeName);
  	}


  	private void addNode(String graphName, String nodeName)
	{

  		// TODO: Insert your code here. - did
  		 
  		Graph<WeightedNode> graph = graphs.get(graphName);
  		WeightedNode node = nodes.get(nodeName);
  		try
		{
			graph.AddNode(node);
			output.println("added node " + nodeName + " to " + graphName);
		}
  		catch (NodeAlreadyExistsException n)
		{
			throw new CommandException(
					"Node: " + nodeName + " already exists in Graph: " +
							graphName);
		}
  	}


  	private void addEdge(List<String> arguments) {

    	if (arguments.size() != 3)
      		throw new CommandException(
				"Bad arguments to addEdge: " + arguments);

    	String graphName = arguments.get(0);
    	String parentName = arguments.get(1);
    	String childName = arguments.get(2);
    	addEdge(graphName, parentName, childName);
  	}


	private void addEdge(String graphName, String parentName, String childName) {
		
		// TODO: Insert your code here. - did
		  
		Graph<WeightedNode> graph = graphs.get(graphName);
		WeightedNode parentNode = nodes.get(parentName);
		WeightedNode childNode = nodes.get(childName);
		try
		{
			graph.AddEdge(parentNode, childNode);
			output.println("added edge from " + parentName + " to " + childName + " in " + graphName);
		}
		catch (NodeDoesNotExistException n)
		{
			throw new CommandException(
					"Node: " + parentName + " or " + childName + " does not " +
							"exist in " + graphName);
		}
		catch (EdgeAlreadyExistsException e)
		{
			throw new CommandException(
					"The edge from " + parentName + " to " + childName +
							" already exists in " + graphName);
		}
  	}


  	private void listNodes(List<String> arguments) {

    	if (arguments.size() != 1)
      		throw new CommandException(
				"Bad arguments to listNodes: " + arguments);

    	String graphName = arguments.get(0);
    	listNodes(graphName);
  	}


  	private void listNodes(String graphName) {
  		
  		// TODO: Insert your code here. - did
  		   
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


  	private void listChildren(List<String> arguments) {

    	if (arguments.size() != 2)
      		throw new CommandException(
				"Bad arguments to listChildren: " + arguments);

    	String graphName = arguments.get(0);
    	String parentName = arguments.get(1);
    	listChildren(graphName, parentName);
  	}


  	private void listChildren(String graphName, String parentName) {

  		// TODO: Insert your code here. - did
  		    
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
			throw new CommandException(
					parentName + " does not exist in " + graphName);
		}
  	}


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