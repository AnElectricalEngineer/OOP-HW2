package homework2;

import java.util.*;

//TODO write spec

 public class PathFinder<N, P extends Path<N, P>>
{
    private final Graph<N> graph_;
    private Map<N, P> paths_;
    private PriorityQueue<P> active_;
    private Set<N> finished_;

    public PathFinder(Graph<N> graph)
    {
        graph_ = graph;
        paths_ = new HashMap<>();
        active_ = new PriorityQueue<>();
        finished_ = new HashSet<>();
        //TODO checkrep maybe
    }

    public P shortestPath(Set<P> startNodes, Set<P> goalNodes) throws GraphExceptions.NodeDoesNotExistException //TODO maybe get rid of this exception
    {
        for(P nodeIterator : startNodes)
        {
            paths_.put(nodeIterator.getEnd(), nodeIterator);
            active_.add(nodeIterator);
        }

        while(!active_.isEmpty())
        {
            N queueMin = active_.poll().getEnd();
            P queueMinPath = paths_.get(queueMin);

            for (P goalNode : goalNodes)
            {
                if (goalNode.getEnd().equals(queueMin))
                {
                    return queueMinPath;
                }
            }

            Set<N> childNodes = graph_.getChildNodes(queueMin);
            for(N childNode : childNodes)
            {
                P cPath = queueMinPath.extend(childNode);
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

        //TODO Delete this!! and FIX!
        Iterator<P> temp = startNodes.iterator();
        return temp.next();

    }
}

//TODO implement checkrep maybe