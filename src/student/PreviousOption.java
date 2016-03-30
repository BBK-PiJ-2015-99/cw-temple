package student;

import game.NodeStatus;
import java.util.Collection;
import java.util.Collections;
import java.lang.IllegalArgumentException;

public class PreviousOption implements Comparable<PreviousOption> {
    
    //Id of this node
    long nodeId;
    long neighbourNodeClosestToOrbId;
    Integer neighbourNodeClosestToOrbDistance;
    Collection<NodeStatus> neighbourNodes;

    public PreviousOption(long nodeId, Collection<NodeStatus> neighboursNodes){
        if(neighbourNodes.size()==0)
            throw new IllegalArgumentException("This node has no new neighbour nodes - don't track it");
        this.nodeId = nodeId;
        this.neighbourNodes = neighbourNodes;
    }
    
    /**
    * Find neighbour node closest to Orb, update class variables
    **/
    private void updateClosestToOrb(){
        NodeStatus nodeClosestToOrb = Collections.min(neighbourNodes, null); 
        neighbourNodeClosestToOrbId = nodeClosestToOrb.getId();
        neighbourNodeClosestToOrbDistance = nodeClosestToOrb.getDistanceToTarget();
    }

    /**
    * If a new orb was visited check if is among the options being tracked as options. 
    * If it is deleted from the available option, and if it is the node closest to the Orb at this tile closestOrbId
    **/
    private void newNodeVisited(NodeStatus recentlyVisited){
         if(neighbourNodes.contains(recentlyVisited)){
            long recentlyVisitedId = recentlyVisited.getId();
            neighbourNodes.remove(recentlyVisited);
            if(neighbourNodeClosestToOrbId == recentlyVisitedId){
                updateClosestToOrb();
            }
         }
        
    }


    @Override
    public int compareTo(PreviousOption other) {
        return Integer.compare(neighbourNodeClosestToOrbDistance, other.neighbourNodeClosestToOrbDistance);

    }


} 
