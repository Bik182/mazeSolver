import java.util.*;

public class Agent {
    private MazeReader m;
    private MazeReader solution;
    private int sel;

    public Agent(int algoChoice, MazeReader mazeReader){
        sel = algoChoice;
        m = mazeReader;
        solution = m;
    }

    public void solve(){
        switch(sel){
            case 1: break;
            case 2: wallFollowAlgo(); break;
            case 3: pledgeAlgo(); break;
            case 4: tremauxAlgo(); break;
            case 5: deadEndFillAlgo(); break;
            case 6: recursiveAlgo(); break;
            case 7: mazeRoutingAlgo(); break;
            case 8: shortestPathAlgo(); break;
        }
    }

    private void wallFollowAlgo(){

    }

    private void pledgeAlgo(){

    }

    private void tremauxAlgo(){

    }

    private void deadEndFillAlgo(){

    }

    private void recursiveAlgo(){

    }

    private void mazeRoutingAlgo(){

    }

    private void shortestPathAlgo(){

    }

    public String toString(){
        String s = "";
        return s;
    }

}
