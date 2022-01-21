/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProcedFile{
    private List<List<Double>> list;//TODO: Change way of reading because in file can be comments and formatting can be different
    private int size;
    public ProcedFile(String file){
        list = new ArrayList<>(); //2D array for storing coordinates
        size = 0;
        try {
            File myObj = new File(file);
            Scanner myReader = new Scanner(myObj);


            int i = 1;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                if(i == 4){
                    var s = data.split(" ");
                    size = Integer.parseInt(s[1]);
                }
                if(i >= 7 && i < size + 7) {//Proceeds only numbers. Starting from line 7(where desc ends) to size(DIMENTIONS + 7)
                    List<Double> r = new ArrayList<>();//Temp array
                    var s = data.split(" ");

                    r.add(Double.parseDouble(s[1]));//Stores only coordinates withOUT index
                    r.add(Double.parseDouble(s[2]));
                    list.add(r);
                }
                i++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public List<List<Double>> getList() {
        return list;
    }

    public int getSize(){
        return this.size;
    }

}
