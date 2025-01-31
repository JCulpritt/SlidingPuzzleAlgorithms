package search_solutions;

import core_search.BaseSearch;
import core_search.FILOStack;
import search_problems.SlidingPuzzle;
import java.util.ArrayList;

public class SlidingPuzzle_DFS extends BaseSearch<ArrayList<Integer>, String> {
    public SlidingPuzzle_DFS() {
        super(new SlidingPuzzle(), new FILOStack<>()); //if you want to change the searching algo, you can change the type of queue
    }

    public static void main(String[] args) {
        SlidingPuzzle_DFS t = new SlidingPuzzle_DFS();
        double startTime = System.nanoTime();
        t.search();
        double endTime = System.nanoTime();

        System.out.printf("-------------------------\nTime taken: %.3f secs\n-------------------------", (endTime-startTime)/1_000_000_000);
    }
}
