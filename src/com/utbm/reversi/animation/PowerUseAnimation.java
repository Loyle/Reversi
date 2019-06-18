package com.utbm.reversi.animation;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.utbm.reversi.model.cells.Cell;

public class PowerUseAnimation implements Runnable{        
    private int currentFrame;               // animations current frame
    private int totalFrames;                // total amount of frames for your animation
    
    private boolean stopped;           
    
    private Cell cell;

    private List<BufferedImage> buffer = new ArrayList<BufferedImage>();
	private int frameDelay;     

    public PowerUseAnimation(BufferedImage[] buffer, Cell cell, int frameDelay) {   
        for(int i = 0; i < buffer.length; i++) {
        	this.buffer.add(buffer[i]);        	
        }
        
        this.cell = cell;
        this.frameDelay = frameDelay;
        this.currentFrame = 0;
        this.totalFrames = this.buffer.size();
        
        this.stopped = false;
    }
    
    public void stop() {
    	//cell.removeHoverAnimation(this);
    	this.stopped = true;
    }

    public BufferedImage getSprite() {
        return buffer.get(currentFrame);
    }

	@Override
	public void run() {
		while(!stopped) {			
			this.cell.updateState();
			if(currentFrame < totalFrames-1) {
				currentFrame++;
			}else {
				currentFrame = 0;
			}
		
			try {
				Thread.sleep(this.frameDelay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

