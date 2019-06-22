package com.utbm.reversi.animation;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.utbm.reversi.model.cells.Cell;

public class PowerAnimation implements Runnable{        
    private int currentFrame;
    private int totalFrames;
    
    private boolean stopped;  
    private boolean repeat;
    
    private Cell cell;

    private List<BufferedImage> buffer = new ArrayList<BufferedImage>();
	private int frameDelay;
	
	/**
	 * Generate a new Animation base on sprite from the data file
	 * @param buffer sprite array
	 * @param cell clickedCell
	 * @param frameDelay delay between 2 frame
	 * @param repeat boolean value saying if the animation is repeating or not  
	 */
    public PowerAnimation(BufferedImage[] buffer, Cell cell, int frameDelay, boolean repeat) {   
        for(int i = 0; i < buffer.length; i++) {
        	this.buffer.add(buffer[i]);        	
        }
        
        this.cell = cell;
        this.frameDelay = frameDelay;
        this.currentFrame = 0;
        this.totalFrames = this.buffer.size();
        
        this.repeat = repeat;
        this.stopped = false;
    }
    /**
     * stop the animation
     */
    public void stop() {
    	cell.removeHoverAnimation(this);
    	this.stopped = true;
    }

    public BufferedImage getSprite() {
        return buffer.get(currentFrame);
    }

	@Override
	/**
	 * Start and run the animation while the stop method isn't call 
	 * @see stop
	 * @exception InterruptedException
	 */
	public void run() {
		while(!stopped) {
			this.cell.updateState();	
			try {
				Thread.sleep(this.frameDelay);
				if(currentFrame < totalFrames-1) {
					currentFrame++;
				}else {
					if(this.repeat) {
						currentFrame = 0;					
					}
					else {
						this.stop();
					}
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

