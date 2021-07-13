package com.mygdx.mechwargame.core.world.generator.util;

import java.util.Random;

public class CellAlgorithm {

    private final static boolean DEBUG = false;

    private int deathLimit = 5;
    private int birthLimit = 2;
    private float chanceToStartAlive = 38;
    private int width;
    private int height;
    private Random random;

    public CellAlgorithm(Random random) {
        this.random = random;
    }


    public int[][] create(int steps, int width, int height) {

        this.width = width;
        this.height = height;
        
        long start = System.currentTimeMillis();
        //Create a new map
        int[][] cellmap = new int[width][height];
        //Set up the map with random values
        cellmap = initialiseMap(cellmap);

        //And now update the simulation for a set number of steps
        for(int i=0; i<steps; i++){
            cellmap = doSimulationStep(cellmap);
        }

        return cellmap;
    }

    private int[][] initialiseMap(int[][] map){
        for(int x = 0; x< width; x++){
            for(int y = 0; y< height; y++){
                if(this.random.nextInt(100) < chanceToStartAlive){
                    map[x][y] = 1;
                } else {
                    map[x][y] = 0;
                }
            }
        }
        return map;
    }

    private int countAliveNeighbours(int[][] map, int x, int y){
        int count = 0;
        for(int i=-1; i<2; i++){
            for(int j=-1; j<2; j++){
                int neighbour_x = x+i;
                int neighbour_y = y+j;
                //If we're looking at the middle point
                if(i == 0 && j == 0){
                    //Do nothing, we don't want to add ourselves in!
                }
                //In case the index we're looking at it off the edge of the map
                else if(neighbour_x < 0 || neighbour_y < 0 || neighbour_x >= width || neighbour_y >= height){
                    count = count + 1;
                }
                //Otherwise, a normal check of the neighbour
                else if(map[neighbour_x][neighbour_y] == 1){
                    count = count + 1;
                }
            }
        }
        return count;
    }

    private int[][] doSimulationStep(int[][] oldMap){
        int[][] newMap = new int[width][height];
        //Loop over each row and column of the map
        for(int x=1; x<width - 1; x++){
            for(int y=1; y<height-1; y++){
                int nbs = countAliveNeighbours(oldMap, x, y);
                //The new value is based on our simulation rules
                //First, if a cell is alive but has too few neighbours, kill it.
                if(oldMap[x][y] == 0){
                    if(nbs < deathLimit){
                        newMap[x][y] = 0;
                    }
                    else{
                        newMap[x][y] = 1;
                    }
                } //Otherwise, if the cell is dead now, check if it has the right number of neighbours to be 'born'
                else{
                    if(nbs > birthLimit){
                        newMap[x][y] = 1;
                    }
                    else{
                        newMap[x][y] = 1;
                    }
                }
            }
        }
        return newMap;
    }
}