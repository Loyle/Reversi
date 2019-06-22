package com.utbm.reversi.model;

import java.awt.Color;
import java.util.Random;

import javax.swing.ImageIcon;

import com.utbm.reversi.model.cells.BombCell;
import com.utbm.reversi.model.cells.Cell;
import com.utbm.reversi.model.cells.TrapCell;

public class Board {
    private int size;
    private Cell[][] board;

    /**
     * Board constructor
     *
     * @param size
     * @param obstaclesNumber
     * @param trapsNumber
     * @param nbPlayer
     */
    public Board(int size, int obstaclesNumber, int trapsNumber, int nbPlayer) {
        this.board = new Cell[size][size];
        this.size = size;
        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size; i++) {
                this.board[i][j] = new Cell(new ImageIcon("./data/grass.png"));
            }
        }


        int incrObstacles = 0;
        int incrTraps = 0;

        int minX = (this.size / 2) - (nbPlayer / 2);
        int minY = (this.size / 2) - (nbPlayer / 2);

        int maxX = minX + nbPlayer - 1;
        int maxY = minY + nbPlayer - 1;

        while (incrObstacles < obstaclesNumber) {
            int randoX = new Random().nextInt(size);
            int randoY = new Random().nextInt(size);

            if (this.board[randoX][randoY].isEnabled() && this.board[randoX][randoY].getOwner() == null && ((randoX < minX || randoX > maxX) && (randoY < minY || randoY > maxY))) {
                this.board[randoX][randoY] = new Cell(new Color(50, 50, 50));
                this.board[randoX][randoY].setDefaultBackground(new ImageIcon("./data/wall.png"));
                this.board[randoX][randoY].setEnabled(false);
                incrObstacles++;
            }
        }
        while (incrTraps < trapsNumber) {
            int randoX = new Random().nextInt(size);
            int randoY = new Random().nextInt(size);

            if (this.board[randoX][randoY].isEnabled() && this.board[randoX][randoY].getOwner() == null && ((randoX < minX || randoX > maxX) && (randoY < minY || randoY > maxY))) {
                int whatTrap = new Random().nextInt(2);
                if (whatTrap < 1) {
                    this.board[randoX][randoY] = new TrapCell(new ImageIcon("./data/grass.png"));
                } else {
                    this.board[randoX][randoY] = new BombCell(new ImageIcon("./data/grass.png"), this);
                }
                incrTraps++;
            }
        }
    }

    /**
     * get board size
     *
     * @return in
     */
    public int getSize() {
        return this.size;
    }

    /**
     * set Board size
     *
     * @param size
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * get Board cells
     *
     * @return Cell[][]
     */
    public Cell[][] getBoardCells() {
        return this.board;
    }

}
