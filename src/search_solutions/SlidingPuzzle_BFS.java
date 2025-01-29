package search_solutions;

import core_search.BaseSearch;
import core_search.FIFOQueue;
import search_problems.SlidingPuzzle;

import java.util.ArrayList;

public class SlidingPuzzle_BFS extends BaseSearch<ArrayList<Integer>, String> {
        public SlidingPuzzle_BFS() {
            super(new SlidingPuzzle(), new FIFOQueue<>()); //if you want to change the searching algo, you can change the type of queue
        }

        public static void main(String[] args) {
            SlidingPuzzle_BFS t = new SlidingPuzzle_BFS();
            t.search();

        }
}
