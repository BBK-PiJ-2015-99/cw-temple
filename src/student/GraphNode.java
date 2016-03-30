package student;

import game.NodeStatus;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class GraphNode implements Comparable<GraphNode> {

    long id;
    Collection<GraphNode> children;
    Long parent;
    boolean visited;
    Integer distance;
    
    public GraphNode(long id, int distance, Long parent, Collection<NodeStatus> children){
        this.id = id;
        this.distance = distance;
        this.children = new ArrayList();
        addChildren(children);
        visited = false;
        if (parent != null){
            this.parent = parent;
        }
    } 

    public GraphNode getBestNextNode(){
        Collection<GraphNode>  options = new ArrayList();
        for (GraphNode gn : children){
            if(!gn.visited)
                options.add(gn);
        } 
        //System.out.println("chidren:" + children.size() + " --- " +  options.size() + " Options");
        GraphNode ret = Collections.min(options, null);
        //System.out.println("Best node to go to:" + ret.getId());
        return ret;
    }

    public void hasBeenVisited(){
        visited = true;
        System.out.println("Marked:" + id + " as visited in " + parent);
    }

    public long getId(){
        return id;
    }

    public void addChildren( Collection<NodeStatus> children){
        if (children != null && children.size()!=0){
            for (NodeStatus sn : children){
                //Don't include parent in children nodes
                System.out.println( "Checking:" + sn.getId()+ "-" + id );
                if (sn.getId() != id){
                    this.children.add(new GraphNode(sn.getId(), sn.getDistanceToTarget(), id, null));
                }else{
                    System.out.println("Excluded one!");
                }
            }
        }
    }

    /**
     * Return a negative number, 0, or a positive number depending on
     * whether this is closer to, at the same ditance, or farther from the Orb.
     */
    @Override
    public int compareTo(GraphNode other) {
        return Integer.compare(distance, other.distance);
    }

}
