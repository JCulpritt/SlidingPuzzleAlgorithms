package search_solutions;

import core_search.BaseSearch;

import core_search.FILOStack;
import search_problems.Travel;

public class Travel_DFS extends BaseSearch<String, String> {


    public Travel_DFS(String mapFile) {
        super(new Travel(mapFile), new FILOStack<>()); //if you want to change the searching algo, you can change the type of queue
    }

    public static void main(String[] args) {
        Travel_DFS t = new Travel_DFS("RomaniaMap.txt");
        double startTime = System.nanoTime();
        t.search();
        double endTime = System.nanoTime();

        System.out.printf("-------------------------\nTime taken: %.3f secs\n-------------------------", (endTime-startTime)/1_000_000_000);


    }

}
