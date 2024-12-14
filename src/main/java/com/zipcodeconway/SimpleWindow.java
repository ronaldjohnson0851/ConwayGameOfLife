package com.zipcodeconway;

import javax.swing.*;
import java.awt.*;

public class SimpleWindow {
    static JPanel panel;
    static JFrame frame;
    private Integer dim;

    public SimpleWindow(Integer dimension) {
        this.dim = dimension * 10;
        panel = new JPanel();
        Dimension dim = new Dimension(this.dim, this.dim);
        panel.setPreferredSize(dim);
        frame = new JFrame();
        Integer framesize = Math.max(100, this.dim);
        frame.setSize(framesize, framesize);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = frame.getContentPane();
        contentPane.add(panel);
        frame.setVisible(true);
    }

    public void sleep(Integer millisecs) {
        try {
            Thread.sleep(millisecs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void display(int[][] array, Integer generation) {
        frame.setTitle(String.format("Generation: %6d", generation));
        Graphics g = panel.getGraphics();
        int BOX_DIM = 10;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                g.drawRect(i * BOX_DIM, j * BOX_DIM, BOX_DIM, BOX_DIM);
                if (array[i][j] == 0) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.BLACK);
                }
                g.fillRect(i * BOX_DIM, j * BOX_DIM, BOX_DIM, BOX_DIM);
            }
        }
        g.dispose();
    }
}
