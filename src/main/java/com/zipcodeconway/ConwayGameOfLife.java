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
        this.currentGeneration = startmatrix;
    }

    public static void main(String[] args) {
        ConwayGameOfLife sim = new ConwayGameOfLife(5);
        int[][] result = sim.simulate(50); // Simulate 50 generations
    }

    private int[][] createRandomStart(Integer dimension) {
        int[][] matrix = new int[dimension][dimension];
        Random random = new Random();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                matrix[i][j] = random.nextInt(2); // Randomly populate the grid with 0s and 1s
            }
        }
        return matrix;
    }

    public int[][] simulate(Integer maxGenerations) {
        int dimension = currentGeneration.length;
        int[][] nextGeneration = new int[dimension][dimension];

        for (int gen = 0; gen < maxGenerations; gen++) {
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    nextGeneration[i][j] = isAlive(i, j, currentGeneration);
                }
            }

            displayWindow.display(currentGeneration, gen); // Update display
            displayWindow.sleep(125); // Pause for visual effect

            copyAndZeroOut(nextGeneration, currentGeneration); // Move to next generation
        }

        return currentGeneration; // Return the final state
    }

    private void copyAndZeroOut(int[][] next, int[][] current) {
        int dimension = next.length;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                current[i][j] = next[i][j];
                next[i][j] = 0; // Reset nextGeneration for reuse
            }
        }
    }

    private int isAlive(int row, int col, int[][] world) {
        int dimension = world.length;
        int neighbors = 0;

        // Count the live neighbors with edge wrapping
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue; // Skip the cell itself
                int neighborX = (row + i + dimension) % dimension; // Wrap edges
                int neighborY = (col + j + dimension) % dimension;
                neighbors += world[neighborX][neighborY];
            }
        }

        // Apply Conway's Game of Life rules
        if (world[row][col] == 1) {
            return (neighbors == 2 || neighbors == 3) ? 1 : 0; // Survives if 2 or 3 neighbors
        } else {
            return (neighbors == 3) ? 1 : 0; // Revives if exactly 3 neighbors
        }
    }
}
