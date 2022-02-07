//
//Name: Daniel Chung
//Date: 11/26/19
//Class: ISTE - 222
//
import java.io.*;
import java.util.*;

public class timing {

    private long calculateTime(long startTime, long endTime){
        return (endTime - startTime);
    }

    private String simpleArrayTiming(String[] search, ArrayList<String> stringArrayList){
        int[] indexes = new int[50000];
        long start = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++){
             indexes[i] = stringArrayList.indexOf(search[i]);
        }
        long end = System.currentTimeMillis();
        return String.valueOf(calculateTime(start, end));
    }

    private String treeSetTiming(String[] search, TreeSet<String> TreeSetArray){
        int[] indexes = new int[50000];
        long mimi = System.currentTimeMillis();
        for (int i = 0; i < search.length; i++){
            TreeSetArray.contains(search[i]);
        }
        long mimiLeeTakesWaterFirsThenPill = System.currentTimeMillis();
        return String.valueOf(this.calculateTime(mimi, mimiLeeTakesWaterFirsThenPill));
    }

    private String arrayTiming(String[] search, String[] arrayArray){
        int[] indexes = new int[50000];
        long mimi = System.currentTimeMillis();
        for (int i = 0; i < indexes.length; i++){
            for (int x = 0; x < arrayArray.length; x++){
                if (search[i].equals(arrayArray[x])){
                    indexes[i] = x;
                }
            }
        }
        long mimiLeeTakesWaterFirsThenPill = System.currentTimeMillis();
        return String.valueOf(this.calculateTime(mimi, mimiLeeTakesWaterFirsThenPill));
    }

    private String hashSetTiming(String[] search, HashSet hashSet){
        int[] indexes = new int[50000];
        long mimi = System.currentTimeMillis();
        for (int i = 0; i < search.length; i++){
            hashSet.contains(search[i]);
        }
        long mimiLeeTakesWaterFirsThenPill = System.currentTimeMillis();
        return String.valueOf(this.calculateTime(mimi, mimiLeeTakesWaterFirsThenPill));
    }

    private String vectorTiming(String[] search, Vector vector){
        long mimi = System.currentTimeMillis();
        for (int i = 0; i < search.length; i++){
            vector.contains(search[i]);
        }
        long mimiLeeTakesWaterFirsThenPill = System.currentTimeMillis();
        return String.valueOf(this.calculateTime(mimi, mimiLeeTakesWaterFirsThenPill));
    }

    private String linkedListTiming(String[] search, LinkedList linkedList){
        long mimi = System.currentTimeMillis();
        for (int i = 0; i < search.length; i++){
            linkedList.contains(search[i]);
        }
        long mimiLeeTakesWaterFirsThenPill = System.currentTimeMillis();
        return String.valueOf(this.calculateTime(mimi, mimiLeeTakesWaterFirsThenPill));
    }

    public static void main(String[] args) throws IOException {
        timing testTiming = new timing();
        BufferedReader br = new BufferedReader(new FileReader("/Users/danielchung/IdeaProjects/DataStructureTiming/src/words_alpha.txt"));
        ArrayList<String> word = new ArrayList<>();
        TreeSet<String> wordTree = new TreeSet<>();
        HashSet<String> hashSet = new HashSet<>();
        Vector vector = new Vector();
        LinkedList<String> linkedList = new LinkedList<>();
        String[] wordArray = new String[369664];
        int count = 0;
        String line;
        int indexP = 0;
        int counter = 369664;
        String[] search = new String[50000];
        while ((line = br.readLine()) != null && indexP < 369664) {
            word.add(line);
            wordTree.add(line);
            wordArray[indexP] = line;
            hashSet.add(line);
            vector.add(line);
            indexP++;
        }
        while (count < 50000){
            int index = (int) (Math.random() * 50000);
            search[count] = word.get(index);
            count++;
        }
        System.out.println("Arraylist timing: " + testTiming.simpleArrayTiming(search, word) + " Milliseconds");
        System.out.println("TreeSet timing: " + testTiming.treeSetTiming(search,wordTree) + " Milliseconds");
        System.out.println("HashSet timing: " + testTiming.hashSetTiming(search, hashSet) + " Milliseonds");
        System.out.println("Vector timing: " + testTiming.vectorTiming(search, vector) + " Milliseonds");
        System.out.println("Linkedlist timing: " + testTiming.linkedListTiming(search, linkedList) + " Milliseconds");
        System.out.println("Array timing: " + testTiming.arrayTiming(search,wordArray) + " Milliseconds");
    }

}

