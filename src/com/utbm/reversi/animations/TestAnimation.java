package com.utbm.reversi.animations;

public class TestAnimation implements Runnable {
	private int yolo;
	public TestAnimation(int yolo) {
		this.yolo = yolo;
	}
	
	@Override
	public void run() {
		int i = 0;
		while(true) {
			System.out.print(i + ": OHOH : " + this.yolo);
			
			System.out.print(Thread.currentThread().getName());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
		}
	}
}
