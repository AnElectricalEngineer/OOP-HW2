package homework2;

public class GraphExceptions
{
    public static class NodeAlreadyExistsException extends Exception {
        private final String errorDescription_;

        NodeAlreadyExistsException(String errorDescription)
        {
            errorDescription_ = errorDescription;
        }
    }

    public static class NodeDoesNotExistException extends Exception {
        private final String errorDescription_;

        NodeDoesNotExistException(String errorDescription)
        {
            errorDescription_ = errorDescription;
        }
    }

    public static class EdgeAlreadyExistsException extends Exception {
        private final String errorDescription_;

        EdgeAlreadyExistsException(String errorDescription)
        {
            errorDescription_ = errorDescription;
        }
    }
}
