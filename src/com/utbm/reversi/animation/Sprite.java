package com.utbm.reversi.animation;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Sprite {
	private BufferedImage spritesheet;
    private int sizeX;
    private int sizeY;
    private int duration;
    private int spriteSize;
    
    public Sprite(String img, int spriteSize, int duration, int sizeX, int sizeY) {
    	this.sizeX = sizeX;
    	this.sizeY = sizeY;
    	this.duration = duration;
    	this.spriteSize = spriteSize;
    	
    	this.loadSprite(img);
    }
    public Sprite(ImageIcon img, int spriteSize, int duration, int sizeX, int sizeY) {
    	this.sizeX = sizeX;
    	this.sizeY = sizeY;
    	this.duration = duration;
    	this.spriteSize = spriteSize;
    	
    	this.loadSprite(img);
    }
    
    public void loadSprite(String file) {
        BufferedImage sprite = null;

        try {
            sprite = ImageIO.read(new File(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.spritesheet = sprite;
    }
    
    public void loadSprite(ImageIcon file) {

		BufferedImage bufferedImage = new BufferedImage(file.getImage().getWidth(null), file.getImage().getHeight(null),
				BufferedImage.TYPE_INT_RGB);

		Graphics g = bufferedImage.createGraphics();
		g.drawImage(file.getImage(), 0, 0, null);
		g.dispose();

		try {
			ImageIO.write(bufferedImage, "png", new File("a.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.spritesheet = bufferedImage;
	}

	public BufferedImage getSprite(int xGrid, int yGrid) {
		return this.spritesheet.getSubimage(xGrid * this.sizeX, yGrid * this.sizeY, this.sizeX, this.sizeY);
	}

	/**
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * @return the spriteSize
	 */
	public int getSpriteSize() {
		return spriteSize;
	}

	/**
	 * @param spriteSize the spriteSize to set
	 */
	public void setSpriteSize(int spriteSize) {
		this.spriteSize = spriteSize;
	}
}
