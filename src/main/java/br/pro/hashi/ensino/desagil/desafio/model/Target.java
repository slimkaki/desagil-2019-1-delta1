package br.pro.hashi.ensino.desagil.desafio.model;

import java.util.LinkedList;
import java.util.Random;

public class Target extends Element {

    private final Board board;

    public Target(int row, int col, Board board) {
        super(row, col);
        this.board = board;
    }

    private void move(int rowShift, int colShift) {
        row += rowShift;
        col += colShift;
    }

    private void moveUp() {
        if (row>0 && !board.isWall(row - 1, col)) {
            move(-1, 0);
        }
    }

    private void moveRight() {
        if (col < board.getNumCols() - 1 && !board.isWall(row, col + 1)) {
            move(0, 1);
        }
    }

    private void moveDown() {
        if (row < board.getNumRows() - 1 && !board.isWall(row + 1, col)) {
            move(1, 0);
        }
    }

    private void moveLeft() {
        if (col > 0 && !board.isWall(row,col - 1)) {
            move(0, -1);
        }
    }

    private LinkedList<String> possibleMovements() {
        LinkedList<String> possibilitiesList = new LinkedList<>();

        // Checando se tem Wall pra cima (UP -> (-1, 0))
        if (row>0 && !board.isWall(row - 1, col)) {
            possibilitiesList.add("UP");
        }

        // Checando se tem Wall pra direita (Right -> (0, 1))
        if (col < board.getNumCols() - 1 && !board.isWall(row, col + 1)) {
            possibilitiesList.add("RIGHT");
        }

        // Checando se tem Wall pra baixo (Down -> (1, 0))
        if (row < board.getNumRows() - 1 && !board.isWall(row + 1, col)) {
            possibilitiesList.add("DOWN");
        }

        // Checando se tem Wall pra esquerda (Left -> (0, -1))
        if (col > 0 && !board.isWall(row,col - 1)) {
            possibilitiesList.add("LEFT");
        }

        return possibilitiesList;
    }

    public void randomMovement() {
        LinkedList<String> possible = possibleMovements();
        if (possible.size() == 1) {
            String string = possible.element();
            switch (string) {
                case "UP":
                    moveUp();
                    break;

                case "RIGHT":
                    moveRight();
                    break;

                case "DOWN":
                    moveDown();
                    break;

                case "LEFT":
                    moveLeft();
                    break;
            }

        } else {
            Random random = new Random();
            int nextStep = random.nextInt(possible.size());
            int counter = 0;
            for (String string : possible) {
                if (counter == nextStep) {
                    switch (string) {
                        case "UP":
                            moveUp();
                            break;

                        case "RIGHT":
                            moveRight();
                            break;

                        case "DOWN":
                            moveDown();
                            break;

                        case "LEFT":
                            moveLeft();
                            break;
                    }
                    break;
                }
                counter++;
            }
        }
    }
}
