package search_solutions;

import core_search.BaseSearch;
import core_search.Node;
import core_search.SortedQueue;
import search_problems.SlidingPuzzle;

import java.util.Comparator;

public class SlidingPuzzle_AStar_Mismatch extends BaseSearch<String, String> {
    public SlidingPuzzle_AStar_Mismatch() {
        super(new SlidingPuzzle(), new SortedQueue<>(new SlidingPuzzle_AStar_Mismatch.CalculateHeuristic(new SlidingPuzzle())));
    }

    public static void main(String[] args) {
        SlidingPuzzle_AStar_Mismatch t = new SlidingPuzzle_AStar_Mismatch();
        t.search();
    }

    public static class CalculateHeuristic implements Comparator<Node<String, String>> {
        public final SlidingPuzzle problem;
        public CalculateHeuristic(SlidingPuzzle problem) {
            this.problem = problem;
        }
        public int compare(Node<String, String> o1, Node<String, String> o2) {
            return Integer.compare(problem.calculateMismatchHeuristic(o1.getState()), problem.calculateMismatchHeuristic(o2.getState()));
        }
    }
}
