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

    final int HASHMAP_INIT_SIZE = 5000;

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

    public Set<Long> getNeighbours(long location){
        if(!graph.containsKey(location)){
            throw new IllegalArgumentException("Can't find neighbours for node that I don't know");
        }
        Set<Long> ret = (Set<Long>) graph.get(location);
        return ret;
    }



    public List<Long> getBestNextNode(long id, boolean neighbours){
        Long bestNextNode = null;
        if(!graph.containsKey(id)){
            throw new IllegalArgumentException("Can't determine best move for this node as it is unknown:" + id + "--");
        }
        
        Set<Long> nodes;
        if(neighbours){
            nodes = getNeighbours(id);
        }else{
            nodes = graph.keySet();
        }
        List<GraphNode> candidates = new ArrayList(); 
        for(long thisID : nodes){
            if(!visited.contains(thisID)){
                int dist = (Integer) distances.get(thisID);
                candidates.add(new GraphNode(thisID,dist));
            }
        }
        List<Long> return_values = new ArrayList();
        for (GraphNode gn : candidates){
            return_values.add(gn.getId());
        }
        
        return return_values;
    }


    /**
    *Get the shortest path between two nodes
    *Implements = https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
    **/
    public List<Long> getShortestPath(long origin, long destination){
        List<Long> shortest_path = new ArrayList();
        Set<Long> vertices = graph.keySet();
        Map dist = new HashMap(HASHMAP_INIT_SIZE);
        Map prev = new HashMap(HASHMAP_INIT_SIZE);
        for (Long l : vertices  ){
            dist.put(l, 999_999_999);
            prev.put(l, null);
        }
        dist.put( origin, 0); 

        while(!vertices.isEmpty()){
            long current = getKeyWithLowestDistance(dist);
            if (current == (Long) destination ){
                List<Long> temp_shortest_path = new ArrayList();
                Long u = destination;
                while(prev.containsKey(u)){
                    temp_shortest_path.add((Long)prev.get(u));
                    u = (Long) prev.get(u);
                }
                temp_shortest_path.add(u);
                for(int i = temp_shortest_path.size()-1; i>=0; i--){
                    shortest_path.add(temp_shortest_path.get(i) );
                }
            }
            System.out.println("Current (start):" + current);
            vertices.remove(current);
            System.out.println("shortest path calculations:" + current);
            Set<Long> neighbours = getNeighbours( current);
            System.out.println("how many neighbours:" + neighbours.size());
            for (Long n : neighbours){
                long alt = (long) dist.get(current) + 1;
                if (alt < (long) dist.get(n)){
                    dist.put(n, alt); 
                    prev.put(n, current);
                }
            }
        }
        
        return shortest_path;
    }    

    private long getKeyWithLowestDistance(Map dist){
        Long lowest = null;
        int smallest_dist = 999_999_999;
        
        Set<Long> vertices = dist.keySet();
        for(Long l : vertices){
            if((int) dist.get(l)< smallest_dist){
                lowest = l;
                smallest_dist = (int) dist.get(l);   
            }
        }
        return lowest;
    }

}
