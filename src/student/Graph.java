package student;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.lang.IllegalArgumentException;
import java.util.Set;
import java.util.HashSet;

public class Graph{

    Map graph;
    Map distances;
    Collection visited;

    int HASHMAP_INIT_SIZE = 5000;

    public Graph(){
        graph = new HashMap(HASHMAP_INIT_SIZE);
        visited = new ArrayList();
        distances = new HashMap(HASHMAP_INIT_SIZE);
    }

    public void addVertex(long a, long b){
        if(!graph.containsKey(a))
            graph.put(a,  new HashSet<Long>());
        
        if(!graph.containsKey(b))
            graph.put(b, new HashSet<Long>());
        Set aList = (Set)  graph.get(a);
        aList.add(b);
        Set bList =  (Set) graph.get(b);
        bList.add(a);
    }

    public void setVisited(long id){
        visited.add(id);
    }
    public void setDistance(long id, int distance){
        if(!distances.containsKey(id)){
            distances.put(id, distance);
        } 
    }

    public Long getBestNextNode(long id){
        Long bestNextNode = null;
        if(!graph.containsKey(id)){
            throw new IllegalArgumentException("Can't determine best move for this node as it is unknown:" + id);
        }
        Set<Long> nodesNeighbours = (Set) graph.get(id);
        Collection<GraphNode> candidates = new ArrayList(); 
        for(long thisID : nodesNeighbours){
            if(!visited.contains(thisID)){
                int dist = (Integer) distances.get(thisID);
                candidates.add(new GraphNode(thisID,dist));
            }
        }
         
        if (candidates.size()>0){
            GraphNode gn = Collections.min( candidates  ,null);
            bestNextNode = gn.getId();
        }
        
        return bestNextNode;
    }


    public Long getBestNodeNotTaken(){
        //Find the best node not visited ( i.e. a neighbour node) available
        Collection<GraphNode> candidates = new ArrayList();
        Set<Long> possibleDistances = distances.keySet();
        for (Long id : possibleDistances){
            if(!visited.contains(id))
                candidates.add(new GraphNode(id, (int) distances.get(id)));
        } 
        GraphNode gn = Collections.min(candidates, null);
        return gn.getId();
    }
}
