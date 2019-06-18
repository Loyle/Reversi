package com.utbm.reversi.animation;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Animation implements Runnable{
	private int frameCount;                 // Counts ticks for change                 
    private int currentFrame;               // animations current frame
    private int animationDirection;         // animation direction (i.e counting forward or backward)
    private int totalFrames;                // total amount of frames for your animation

    private int posX;
    private int posY;
    
    private boolean stopped;                

    private List<Frame> frames = new ArrayList<Frame>();     

    public Animation(BufferedImage[] frames, int frameDelay,int x , int y ) {
        //this.frameDelay = frameDelay;
        this.stopped = true;

        for (int i = 0; i < frames.length; i++) {
            addFrame(frames[i], frameDelay);
        }

        this.frameCount = 0;
       // this.frameDelay = frameDelay;
        this.currentFrame = 0;
        this.animationDirection = 1;
        this.totalFrames = this.frames.size();
        
        this.posX = x;
        this.posY = y;

    }

    public void start() {
        if (!stopped) {
            return;
        }
        if (frames.size() == 0) {
            return;
        }
        stopped = false;
    }

    public void stop() {
        if (frames.size() == 0) {
            return;
        }
        stopped = true;
    }

    public void restart() {
        if (frames.size() == 0) {
            return;
        }
        stopped = false;
        currentFrame = 0;
    }
    
    public void reset() {
        this.stopped = true;
        this.frameCount = 0;
        this.currentFrame = 0;
    }
    
    private void addFrame(BufferedImage frame, int duration) {
        if (duration <= 0) {
            System.err.println("Invalid duration: " + duration);
            throw new RuntimeException("Invalid duration: " + duration);
        }
        frames.add(new Frame(frame, duration));
        currentFrame = 0;
    }

    public BufferedImage getSprite() {
        return frames.get(currentFrame).getFrame();
    }

    /*public void update() {
        if (!stopped) {
            frameCount++;

            if (frameCount > frameDelay) {
                frameCount = 0;
                currentFrame += animationDirection;

                if (currentFrame > totalFrames ) {
                    currentFrame = 0;
                }
                else if (currentFrame < 0) {
                    currentFrame = totalFrames - 1;
                }
            }
        }

    }*/

	@Override
	public void run() {

		if(!stopped) {
			
			if(currentFrame < totalFrames-1) {
				currentFrame ++;
			}else {
				currentFrame = 0;
			}
		}
		
		try {
			Thread.sleep(150);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
}

