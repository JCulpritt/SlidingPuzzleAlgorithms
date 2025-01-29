package search_solutions;

import core_search.BaseSearch;
import core_search.Node;
import core_search.SortedQueue;
import search_problems.SlidingPuzzle;

import java.util.Comparator;

public class SlidingPuzzle_AStar_Distance extends BaseSearch<String, String> {
    public SlidingPuzzle_AStar_Distance() {
        super(new SlidingPuzzle(), new SortedQueue<>(new SlidingPuzzle_AStar_Distance.CalculateHeuristic(new SlidingPuzzle())));
    }

    public static void main(String[] args) {
        SlidingPuzzle_AStar_Distance t = new SlidingPuzzle_AStar_Distance();
        t.search();
    }

    public static class CalculateHeuristic implements Comparator<Node<String, String>> {
        public final SlidingPuzzle problem;
        public CalculateHeuristic(SlidingPuzzle problem) {
            this.problem = problem;
        }
        public int compare(Node<String, String> o1, Node<String, String> o2) {
            return Integer.compare(problem.calculateDistanceHeuristic(o1.getState()), problem.calculateDistanceHeuristic(o2.getState()));
        }
    }
}
