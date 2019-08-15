package model;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SuperTile extends Tile {

    public static String [] colors = {
            "Resources/images/GreenSuper.jpg",
            "Resources/images/BlueSuper.jpg",
            "Resources/images/CyanSuper.jpg",
            "Resources/images/RedSuper.jpg",
            "Resources/images/YellowSuper.jpg"
    };

    private Image img;
    private int color;
    private int status;

    public SuperTile(int row, int col) {
        super(row, col);
        color = randInt(0,4);
        status = 0;



        try {
            img = ImageIO.read(new FileImageInputStream(new File(colors[color])));
        } catch (IOException ex) {

        }
    }

    public SuperTile(int row, int col, int color) {
        super(row,col);
        super.setColor(color);
        status = 0;
        try {
            img = ImageIO.read(new FileImageInputStream(new File(colors[color])));
        } catch (IOException ex) {

        }
    }

    public Image getImg() {
        return img;
    }

    public String toString() {
        String c = "Y";
        if (color == 0)
            c = "GS";
        else if (color == 1)
            c = "BS";
        else if (color == 2)
            c = "CS";
        else if (color == 3)
            c = "RS";
        else
            c = "YS";


        return "[" + getRow() + "," + getCol() + "," + c + "]";
    }


}
