package search_solutions;

import core_search.BaseSearch;
import core_search.Node;
import core_search.SortedQueue;
import search_problems.SlidingPuzzle;
import java.util.ArrayList;
import java.util.Comparator;

public class SlidingPuzzle_GBFS_Distance extends BaseSearch<ArrayList<Integer>, String> {
    public SlidingPuzzle_GBFS_Distance() {
        super(new SlidingPuzzle(), new SortedQueue<>(new SlidingPuzzle_GBFS_Distance.CalculateHeuristic((new SlidingPuzzle()))));
    }

    public static void main(String[] args) {
        SlidingPuzzle_GBFS_Distance t = new SlidingPuzzle_GBFS_Distance();
        t.search();
    }

    public static class CalculateHeuristic implements Comparator<Node<ArrayList<Integer>, String>> {
        public final SlidingPuzzle problem;
        public CalculateHeuristic(SlidingPuzzle problem) {
            this.problem = problem;
        }
        public int compare(Node<ArrayList<Integer>, String> o1, Node<ArrayList<Integer>, String> o2) {
            return Integer.compare(problem.calculateDistanceHeuristic(o1.getState()), problem.calculateDistanceHeuristic(o2.getState()));
        }
    }
}
