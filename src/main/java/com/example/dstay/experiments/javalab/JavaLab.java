package com.example.dstay.experiments.javalab;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JavaLab {
    final static String PATH = "src/main/java/com/example/dstay/experiments/javalab/";
    final static String FILENAME = "numbers.txt";

    public static void main(String[] args) throws FileNotFoundException {
        if(createFile()){
            ArrayList<Integer> arrayList = new ArrayList<>(readFile());
            int sum = arrayList
                    .stream()
                    .reduce(0, Integer::sum);
            System.out.println(sum);
        }
        ;
    }
    private static boolean createFile() throws FileNotFoundException {
        try{
            PrintWriter printWriter = new PrintWriter(PATH + FILENAME);
            int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
            for (Integer i : arr) {
                printWriter.println(i);
            }
            printWriter.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    private static List<Integer> readFile() throws FileNotFoundException {
        File file = new File(PATH + FILENAME);
        Scanner in = new Scanner(file);
        ArrayList<Integer> list = new ArrayList<>();
        while (in.hasNextLine()) {
            list.add(Integer.valueOf(in.nextLine()));
        }
        return list;
    }
}
