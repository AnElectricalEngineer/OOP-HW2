package homework2;

/**
 * GraphExceptions are a number of checked exceptions that can be thrown by a
 * Graph. Each GraphException contains a description of the event that caused
 * the exception to be thrown.
 */

public class GraphExceptions
{

    /**
     * A NodeAlreadyExistsException is an exception that is thrown when an
     * attempt is made to add a node to a graph that already contains the
     * same node.
     */
    public static class NodeAlreadyExistsException extends Exception {
        private final String errorDescription_;

        /**
         * @requires errorDescription != null
         * @effects creates a new NodeAlreadyExistsException with the
         * description errorDescription.
         */
        NodeAlreadyExistsException(String errorDescription)
        {
            errorDescription_ = errorDescription;
        }

        /**
         * @requires none
         * @modifies none
         * @effects returns a description of what caused the exception to be
         * thrown.
         */
        public String getErrorDescription()
        {
            return errorDescription_;
        }
    }

    /**
     * A NodeDoesNotExistException is an exception that is thrown when an
     * attempt is made to access a node that does not exist in the graph.
     */
    public static class NodeDoesNotExistException extends Exception {
        private final String errorDescription_;

        /**
         * @requires errorDescription != null
         * @effects creates a new NodeAlreadyExistsException with the
         * description errorDescription.
         */
        NodeDoesNotExistException(String errorDescription)
        {
            errorDescription_ = errorDescription;
        }

        /**
         * @requires none
         * @modifies none
         * @effects returns a description of what caused the exception to be
         * thrown.
         */
        public String getErrorDescription()
        {
            return errorDescription_;
        }
    }

    /**
     * An EdgeAlreadyExistsException is an exception that is thrown when an
     * attempt is made to add an edge to a graph that already contains the
     * same edge
     */
    public static class EdgeAlreadyExistsException extends Exception {
        private final String errorDescription_;

        /**
         * @requires errorDescription != null
         * @effects creates a new NodeAlreadyExistsException with the
         * description errorDescription.
         */
        EdgeAlreadyExistsException(String errorDescription)
        {
            errorDescription_ = errorDescription;
        }

        /**
         * @requires none
         * @modifies none
         * @effects returns a description of what caused the exception to be
         * thrown.
         */
        public String getErrorDescription()
        {
            return errorDescription_;
        }
    }
}
