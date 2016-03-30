package student;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.lang.IllegalArgumentException;

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
            graph.put(a, new ArrayList());
        
        if(!graph.containsKey(b))
            graph.put(b, new ArrayList());
        List aList = (List) graph.get(a);
        aList.add(b);
        List bList = (List) graph.get(b);
        bList.add(a);
    }

    public void setVisited(long id){
        visited.add(id);
    }
    public void setDistance(long id, int distance){
        
        distances.put(id, distance);
    }

    public Long getBestNextNode(long id){
        Long bestNextNode = null;
        if(!graph.containsKey(id)){
            throw new IllegalArgumentException("Can't determine best move for this node as it is unknown:" + id);
        }
        List<Long> nodesNeighbours = (List) graph.get(id);
        Collection<GraphNode> candidates = new ArrayList(); 
        for(long thisID : nodesNeighbours){
            if(!visited.contains(val)){
                int dist = (Integer) distances.get(val);
                candidates.add(new GraphNode(thisId,dist));
            }
        }
        
        GraphNode gn = Collections.min( candidates  ,null);
        if(gn != null)
            bestNextNode = gn.getId();
        
        return bestNextNode;
    }

}
