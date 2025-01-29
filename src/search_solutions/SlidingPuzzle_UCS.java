package search_solutions;

import core_search.BaseSearch;
import core_search.Node;
import core_search.SortedQueue;
import search_problems.SlidingPuzzle;
import search_problems.Travel;

import java.util.ArrayList;
import java.util.Comparator;

public class SlidingPuzzle_UCS extends BaseSearch<ArrayList<Integer>, String> {
    public SlidingPuzzle_UCS() {
        super(new SlidingPuzzle(), new SortedQueue<>(new ComparePathCost()));
    }

    public static void main(String[] args) {
        SlidingPuzzle_UCS t = new SlidingPuzzle_UCS();
        t.search();
    }

    public static class ComparePathCost implements Comparator<Node<ArrayList<Integer>, String>> {
        public int compare(Node<ArrayList<Integer>, String> o1, Node<ArrayList<Integer>, String> o2) {
            return Integer.compare(o1.getPathCost(), o2.getPathCost());
        }
    }
}
