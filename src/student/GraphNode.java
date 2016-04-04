package student;

import game.NodeStatus;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class GraphNode implements Comparable<GraphNode> {

    long id;
    Integer distance;
    
    public GraphNode(long id, int distance){
        this.id = id;
        this.distance = distance;
    } 

    public long getId(){
        return id;
    }
    public int getDistance(){
        return distance;
    }

    /**
     * Return a negative number, 0, or a positive number depending on
     * whether this is closer to, at the same ditance, or farther from the Orb.
     */
    @Override
    public int compareTo(GraphNode other) {
        return Integer.compare(distance, other.distance);
    }
    @Override
    public String toString(){
        return "GraphNode:" + getId() + " distance:" + getDistance(); 
        
    }
}
