/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company;

import org.w3c.dom.ls.LSOutput;

import java.util.*;

public class TSP {
    private int size;
    private double[][] distances;
    private List<List<Double>> list;

    public TSP(String path) {
        ProcedFile ps = new ProcedFile(path);
        list = new ArrayList<>();
        list.addAll(ps.getList());

        this.size = ps.getSize();
        distances = new double[size][size];
    }
    public void setSize(int size) {
        this.size = size;
    }

    public double[][] calcDistances() { //Fills matrix with distances between cities

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                var x1 = list.get(i).get(0);
                var y1 = list.get(i).get(1);
                var x2 = list.get(j).get(0);
                var y2 = list.get(j).get(1);
                distances[i][j] = calc(x1, y1, x2, y2);
                //System.out.format("%d %d to %d %d = %d\n", x1, y1, x2, y2, Math.round(calc(x1, y1, x2, y2)));
            }
        }


        return distances;
    }

    private double calc(double x1, double y1, double x2, double y2) {//Calculates distance between two points
        return Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    }

    public int getSize() {
        return this.size;
    }

    public double randomSearchOne(int n) {
        List<Integer> cities = new ArrayList<>();
        double distance = 0.0;

        for (int i = 0; i < size; i++) {
            cities.add(i);
        }
        distance = distance(cities); //Default distance

        for (int i = 0; i < n; i++) {
            Collections.shuffle(cities);
            if (distance > distance(cities)) {
                distance = distance(cities);
            }
        }

        return distance;
    }

    public double randomSearchTwo(int n) {
        List<Integer> cities = new ArrayList<>();
        double distance = 0.0;

        for (int i = 0; i < size; i++) {
            cities.add(i);
        }
        distance = distance(cities); //Default distance

        for (int i = 0; i < n; i++) {
            Collections.shuffle(cities);//Shuffles cities in the list to create random solution
            if (distance > distance(cities)) {//Compares current best solution to other random solution
                distance = distance(cities);
                i = 0;//Every time it founds better solution
            }
        }
        return distance;
    }

    public double greedyAlg(int start) {
        double sum = 0;
        int counter = 0;
        int j = 0, i = 0;
        double min = Double.MAX_VALUE;
        List<Integer> visitedRouteList
                = new ArrayList<>();

        // Starting from the 0th indexed
        visitedRouteList.add(start);
        int[] route = new int[distances.length];

        while (i < distances.length) {

            // Corner of the Matrix
            if (counter >= distances[i].length - 1) {
                break;
            }

            // If this path is unvisited then
            // and if the cost is less then
            // update the cost
            if (j != i
                    && !(visitedRouteList.contains(j))) {
                if (distances[i][j] < min) {
                    min = distances[i][j];
                    route[counter] = j;
                }
            }
            j++;

            // Check all paths from the
            // ith indexed city
            if (j == distances[i].length) {
                sum += min;
                min = Integer.MAX_VALUE;
                visitedRouteList.add(route[counter]);
                j = 0;
                i = route[counter];
                counter++;
            }
        }

        // Update the ending city in array
        // from city which was last visited
        i = route[counter - 1];

        for (j = 0; j < distances.length; j++) {

            if (i != j && distances[i][j] < min) {
                min = distances[i][j];
                route[counter] = j + 1;
            }
        }
        sum += min;
        return sum;
    }

    public double greedyAlg() { //Default version with start at 0
        double sum = 0;
        int counter = 0;
        int j = 0, i = 0;
        double min = Double.MAX_VALUE;
        List<Integer> visitedRouteList
                = new ArrayList<>();

        // Starting from the 0th indexed
        visitedRouteList.add(0);
        int[] route = new int[distances.length];

        while (i < distances.length) {

            // Corner of the Matrix
            if (counter >= distances[i].length - 1) {
                break;
            }

            // If this path is unvisited then
            // and if the cost is less then
            // update the cost
            if (j != i
                    && !(visitedRouteList.contains(j))) {
                if (distances[i][j] < min) {
                    min = distances[i][j];
                    route[counter] = j;
                }
            }
            j++;

            // Check all paths from the
            // ith indexed city
            if (j == distances[i].length) {
                sum += min;
                min = Integer.MAX_VALUE;
                visitedRouteList.add(route[counter]);
                j = 0;
                i = route[counter];
                counter++;
            }
        }

        // Update the ending city in array
        // from city which was last visited
        i = route[counter - 1];

        for (j = 0; j < distances.length; j++) {

            if (i != j && distances[i][j] < min) {
                min = distances[i][j];
                route[counter] = j + 1;
            }
        }
        sum += min;
        return sum;
    }

    public double backtracking() {
        int n = getSize();
        var v = new boolean[n];
        v[0] = true;
        return backtrackingAlg(distances, v, 0, n, 1, 0, Double.MAX_VALUE);
    }

    static double backtrackingAlg(double[][] graph, boolean[] v,
                                  int currPos, int n,
                                  int count, double cost, double ans) {

        if (count == n && graph[currPos][0] > 0) {

            ans = Math.min(ans, cost + graph[currPos][0]);
            return ans;
        }

        for (int i = 0; i < n; i++) {
            if (!v[i] && graph[currPos][i] > 0) {

                v[i] = true;
                ans = backtrackingAlg(graph, v, i, n, count + 1,
                        cost + graph[currPos][i], ans);

                v[i] = false;
            }
        }
        return ans;
    }

    public double backtrackingBest() {

        int n = getSize();
        var v = new boolean[n];
        v[0] = true;
        var list = new ArrayList<Double>();
        for (int i = 0; i < n; i++) {
            list.add(backtrackingAlg(distances, v, i, n, 1, 0, Double.MAX_VALUE));
        }
        return Collections.min(list);
    }

    public double distance(List<Integer> cities) {//Calculates length of path between cities in list
        double dist = 0.0;

        for (int i = 1; i < size; i++) {
            dist += distances[cities.get(i)][cities.get(i - 1)];
        }
        return dist;
    }

    public double divideAndConquer(){

        List<Boolean> cities = new ArrayList<>(getSize()); //Array for checking if city was visited
        for (int i = 0; i < getSize(); i++){
            cities.add(false);
        }

        List<List<Integer>> merged = new ArrayList<>();
        List<Integer> tomerge;
        int index = 0;

        for (int i = 0; i < getSize(); i++){
            tomerge = new ArrayList<>(2);
            if(!cities.get(i)) {//If city hasn't been visited yet
                index = findCosest(i, cities); // finds the closest city to the current one

                cities.set(index, true);
                cities.set(i, true);
                //Add cities to array
                tomerge.add(i);
                tomerge.add(index);

                //Merge array tomerge into arrays of pairs called merged
                merged = merge(merged, tomerge);
            }
        }

        return finalRes(merged);
    }

    double finalRes(List<List<Integer>> merged){
        double res = 0;

        for(int i = 0; i < merged.size(); i++){
            res += distances[merged.get(i).get(0)][merged.get(i).get(1)];
        }
        for(int i = 1; i < merged.size(); i++){

            res += Double.min(distances[merged.get(i-1).get(1)][merged.get(i).get(0)], distances[merged.get(i-1).get(0)][merged.get(i).get(1)]);
        }
        return res;
    }

    int findCosest(int x, List<Boolean> cities){
        double min = Double.MAX_VALUE;
        int index = 0;
        for(int i = 0; i < getSize(); i++){
            if(distances[x][i] != 0 && distances[x][i] < min && !cities.get(i)){
                min = distances[x][i];
                index = i;
            }
        }
        return index;
    }

    public List<List<Integer>> merge(List<List<Integer>> merged, List<Integer> tomerge){

        double min = Double.MAX_VALUE;

        int posToPush = 0;
        for (int i = 0; i < merged.size(); i++){
            var mx1 = merged.get(i).get(0);
            var mx2 = merged.get(i).get(1);

            if(distances[mx1][tomerge.get(0)] < min || distances[mx1][tomerge.get(1)] < min){
                min = Double.min(distances[mx1][tomerge.get(0)], distances[mx1][tomerge.get(1)]);
                posToPush = i-1;
            }

            if(distances[mx2][tomerge.get(0)] < min || distances[mx2][tomerge.get(1)] < min){
                min = Double.min(distances[mx2][tomerge.get(0)], distances[mx2][tomerge.get(1)]);
                posToPush = i+1;
            }
        }

        if(posToPush < 0) posToPush = 0;

        merged.add(posToPush, tomerge);
        return merged;
    }

}
