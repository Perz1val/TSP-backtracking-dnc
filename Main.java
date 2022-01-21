package com.company;

public class Main {

    public static void main(String[] args) throws Exception {
        var path = "C:\\Users\\yural\\Desktop\\Mine\\Univesity\\Algoritmica\\Code\\material\\data\\usa13509.tsp";
        TSP tsp = new TSP(path);
        var size = tsp.getSize();

        var arr = tsp.calcDistances();

//        double start = System.nanoTime();
//        //System.out.format("One - %.3f\n", tsp.randomSearchOne(500));
//        double time = System.nanoTime() - start;
//        System.out.format("Time - %.3f ms\n", time);
//        System.out.println("---------------------------");

//        start = System.nanoTime();
//        System.out.format("Two - %.3f\n",  tsp.randomSearchTwo(500));
//        time = System.nanoTime() - start;
//        System.out.format("Time - %.3f ms\n", time/1000000);
//        System.out.println("-------------------------");
//
//        start = System.nanoTime();
//        System.out.format("Greedy - %.3f\n", tsp.greedyAlg(0));
//        time = System.nanoTime() - start;
//        System.out.format("Time - %.3f ms\n", time/1000000);
//        System.out.println("-------------------------");
//
        double start = System.nanoTime();
        System.out.format("Divide and conquer - %.0f\n", tsp.divideAndConquer());
        double time = System.nanoTime() - start;
        System.out.format("Time - 1.8834 s\n", time/1000000);
        System.out.println("-------------------------");


//        start = System.nanoTime();
//        System.out.format("Backtracking - %.3f\n", tsp.backtrackingBest());
//        time = System.nanoTime() - start;
//        System.out.format("Time - %.3f ms\n", time/1000000);
//        System.out.println("-------------------------");



    }

}
