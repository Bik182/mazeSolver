package PROJECT.PROJECT1.MazeProj_Mod1.src;
import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * MazeReader.java
 * A class to read a text file and save a mapping of that text file as a maze.
 * Jasen LaBolle
 * Illinois State University
 * IT 340 - Sept. 15 2020
 *
 * @authors Jasen LaBolle
 * @version 1.0
 * @since 2020-09-15
 */


public class MazeReader {
    private ArrayList<ArrayList<Space>> map;
    private Space start;



    public MazeReader(Scanner mapFile) {
        String line;
        map = new ArrayList<>();
        int i = 0;
        int w = 0;
        MazeDrawer mazeDrawer = new MazeDrawer();
        mazeDrawer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mazeDrawer.setVisible(true);
        Color black = Color.BLACK;
        Color blue = Color.BLUE;
        Color red = Color.RED;
        Color white = Color.WHITE;
        while (mapFile.hasNextLine()) {
            line = mapFile.nextLine();
            w = line.length();
            map.add(new ArrayList<Space>());
            for (int j = 0; j < line.length(); j++) {
                 map.get(i).add(new Space(i,j, fromChar(line.charAt(j))));

                     switch (line.charAt(j)){
                         case '#':

                             mazeDrawer.addRectangle(j  , i,black);

                             break;
                         case '.':

                             mazeDrawer.addRectangle(j, i ,blue);
                             break;
                         case'o':
                             mazeDrawer.addRectangle(j, i ,red);
                             break;
                         case'*':
                             mazeDrawer.addRectangle(j, i ,white);
                             break;
                         default: ;
                     }


                 System.out.print(line.charAt(j));

                 if(fromChar(line.charAt(j)) == Type.START){
                     start = map.get(i).get(j);
                 }

            }

            i++;
        }
        mazeDrawer.setSize(  w* 50,   i * 50);
        System.out.print( "\n"+i +" length\n");
        System.out.print(w+" width \n");



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
