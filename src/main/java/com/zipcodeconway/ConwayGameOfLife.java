package com.zipcodeconway;


import java.util.Random;

public class ConwayGameOfLife {
    private final SimpleWindow displayWindow;
    private int[][] currentGeneration;


    public ConwayGameOfLife(Integer dimension) {
        this.displayWindow = new SimpleWindow(dimension);
        this.currentGeneration = createRandomStart(dimension);
     }

    public ConwayGameOfLife(Integer dimension, int[][] startmatrix) {
        this.displayWindow = new SimpleWindow(dimension);
        this.currentGeneration = createRandomStart(dimension);
    }

    public static void main(String[] args) {
        ConwayGameOfLife sim = new ConwayGameOfLife(50);
        sim.simulate(100);

    }

    // Contains the logic for the starting scenario.
    // Which cells are alive or dead in generation 0.
    // allocates and returns the starting matrix of size 'dimension'
    private int[][] createRandomStart(Integer dimension) {
        int[][] matrix = new int[dimension][dimension];
        Random random = new Random();
        for (int i = 0; i < dimension; i++){
            for (int j = 0; j < dimension; j++){
                matrix[i][i] = random.nextInt(2);
            }
        }


        return matrix;
    }

    public int[][] simulate(Integer maxGenerations) {
        int dimension = currentGeneration.length;
        int[][] nextGeneration = new int[dimension][dimension];

        for (int gen = 0; gen < maxGenerations; gen++){
            for (int i = 0; i < dimension; i++){
                for (int j = 0; j < dimension; j++){
                    nextGeneration[i][j] = isAlive(i,j, currentGeneration) ? 1 : 0;
                }
            }
            displayWindow.display(currentGeneration, gen);
            displayWindow.sleep(125);

            copyAndZeroOut(nextGeneration, currentGeneration);
        }
        return currentGeneration;
    }

    // copy the values of 'next' matrix to 'current' matrix,
    // and then zero out the contents of 'next' matrix
    public void copyAndZeroOut(int [][] next, int[][] current) {
        int dimension = next.length;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                current[i][j] = next[i][j];
                next[i][j] = 0;
            }
        }
    }

}

    // Calculate if an individual cell should be alive in the next generation.
    // Based on the game logic:
	/*
		Any live cell with fewer than two live neighbours dies, as if by needs caused by underpopulation.
		Any live cell with more than three live neighbours dies, as if by overcrowding.
		Any live cell with two or three live neighbours lives, unchanged, to the next generation.
		Any dead cell with exactly three live neighbours cells will come to life.
	*/
    private int isAlive(int row, int col, int[][] world) {
        int dimension = board.length;
        int neighbors = 0;

        for (int i = -1; i <= 1; i++){
            for (int j = -1; j <= 1; j++){
                if (i == 0 && j == 0){
                    continue;
                }
                int neighborX = (x + i + dimension) % dimension;
                int neighbotY = (y + j + dimension) % dimension;
                neighbors += board[neighborX][neighbotY];
            }
        }
        return (board[x][y] == 1 && (neighbors == 2 || neighbors == 3)) || (board[x][y] == 0 && neighbors == 3);
    }
}
