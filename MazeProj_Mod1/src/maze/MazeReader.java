package maze;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * maze.MazeReader.java
 * A class to read a text file and save a mapping of that text file as a maze.
 * Jasen LaBolle
 * Illinois State University
 * IT 340 - Sept. 23 2020
 *
 * @authors Jasen LaBolle, Nicholas Souligne, Bikal Thapa
 * @version 1.0
 * @since 2020-09-23
 */
public class MazeReader {
    private ArrayList<ArrayList<Space>> map;
    private Space start;

    public MazeReader(Scanner mapFile) {
        String line;
        map = new ArrayList<>();
        int i = 0;
        while (mapFile.hasNextLine()) {
            line = mapFile.nextLine();
            map.add(new ArrayList<Space>());
            for (int j = 0; j < line.length(); j++) {
                 map.get(i).add(new Space(i,j, fromChar(line.charAt(j))));
                 if(fromChar(line.charAt(j)) == Type.START){
                     start = map.get(i).get(j);
                 }
            }
            i++;
        }
    }

    public int getX(){
        return map.get(0).size();
    }
    public int getY(){
        return map.size();
    }
    public Space getStart(){
        return start;
    }

    public boolean hasNorth(Space s){
        if(map.get(s.getI()-1).get(s.getJ()).getT() == Type.WALL){
            return false;
        }
        else return true;
    }
    public boolean hasSouth(Space s){
        if(map.get(s.getI()+1).get(s.getJ()).getT() == Type.WALL){
            return false;
        }
        else return true;
    }
    public boolean hasEast(Space s){
        if(map.get(s.getI()).get(s.getJ()+1).getT() == Type.WALL){
            return false;
        }
        else return true;
    }
    public boolean hasWest(Space s){
        if(map.get(s.getI()).get(s.getJ()-1).getT() == Type.WALL){
            return false;
        }
        else return true;
    }

    public Space getNorth(Space s){
        return map.get(s.getI()-1).get(s.getJ());
    }
    public Space getSouth(Space s){
        return map.get(s.getI()+1).get(s.getJ());
    }
    public Space getEast(Space s){
        return map.get(s.getI()).get(s.getJ()+1);
    }
    public Space getWest(Space s){
        return map.get(s.getI()).get(s.getJ()-1);
    }

    public static Type fromChar(char c) throws IllegalArgumentException{
        switch (c){
            case '#': return Type.WALL;
            case '.': return Type.HALL;
            case'o': return Type.START;
            case'*': return Type.FINISH;
            default: throw new IllegalArgumentException();
        }
    }

    public String toString(Space s){
        if(s.getT() == Type.WALL){
            return "#";
        }
        else if(s.getT() == Type.HALL){
            return ".";
        }
        else if(s.getT() == Type.START){
            return "o";
        }
        else {
            return "*";
        }
    }
    public String toString(){
        StringBuilder s = new StringBuilder();
        for(ArrayList<Space> ar: map){
            for(Space space: ar){
                if(space.getT() == Type.WALL){
                    s.append('#');
                }
                else if(space.getT() == Type.HALL){
                    s.append('.');
                }
                else if(space.getT() == Type.START){
                    s.append('o');
                }
                else if(space.getT() == Type.FINISH){
                    s.append('*');
                }
            }
            s.append('\n');
        }
        return s.toString();
    }

}
