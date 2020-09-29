package PROJECT.PROJECT1.MazeProj_Mod1.src;

import javax.swing.*;
import java.awt.*;

public class Painter extends JPanel {
    private int x,  y;
    private Color col;
    public Painter(int x , int y, Color col){
        this.x = x;
        this.y = y;
        this.col = col;

    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);
        this.setLocation(x * 25, y * 25);
        this.setBackground(this.col);
        this.setSize(25 ,25);




    }

}
