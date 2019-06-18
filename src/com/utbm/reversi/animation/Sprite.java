package com.utbm.reversi.animation;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {
	private static BufferedImage spritesheet ;
    private static final int TILE_SIZE_x = 28;
    private static final int TILE_SIZE_y= 33;

    public static BufferedImage loadSprite(String file) {

        BufferedImage sprite = null;

        try {
            sprite = ImageIO.read(new File(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sprite;
    }

    public static BufferedImage getSprite(int xGrid, int yGrid) {

        if (spritesheet == null) {
        	spritesheet = loadSprite("./data/fire.png");
        }

        return spritesheet.getSubimage(xGrid * TILE_SIZE_x, yGrid * TILE_SIZE_y, TILE_SIZE_x, TILE_SIZE_y);
    }
}
