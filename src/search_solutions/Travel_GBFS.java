package search_solutions;

import core_search.BaseSearch;
import core_search.Node;
import core_search.SortedQueue;
import search_problems.Travel;

import java.util.Comparator;

public class Travel_GBFS extends BaseSearch<String, String> {
    public Travel_GBFS(String map, String estimates) {
        super(new Travel(map), new SortedQueue<>(new Travel_GBFS.CompareEstimates(new Travel(map, estimates))));
    }

    public static void main(String[] args) {
        Travel_GBFS t = new Travel_GBFS("RomaniaMap.txt", "RomaniaMapEstimates.txt");
        double startTime = System.nanoTime();
        t.search();
        double endTime = System.nanoTime();

        System.out.printf("-------------------------\nTime taken: %.3f secs\n-------------------------", (endTime-startTime)/1_000_000_000);
    }

    public static class CompareEstimates implements Comparator<Node<String, String>> {
        public final Travel problem;
        public CompareEstimates(Travel problem) {
            this.problem = problem;
        }
        public int compare(Node<String, String> o1, Node<String, String> o2) {
            return Integer.compare(problem.getEstimatedDistance(o1.getState()), problem.getEstimatedDistance(o2.getState()));
        }
    }
}
