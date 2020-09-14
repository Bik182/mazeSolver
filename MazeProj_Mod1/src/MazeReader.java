

import java.util.ArrayList;
import java.util.Scanner;

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
            }
            i++;
        }
    }
    public Space getStart(){
        return start;
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
