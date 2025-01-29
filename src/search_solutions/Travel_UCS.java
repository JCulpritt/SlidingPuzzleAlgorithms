package search_solutions;

import core_search.BaseSearch;
import core_search.Node;
import core_search.SortedQueue;
import search_problems.Travel;

import java.util.Comparator;


public class Travel_UCS extends BaseSearch<String, String> {
    public Travel_UCS(String mapFile) {
        super(new Travel(mapFile), new SortedQueue<>(new ComparePathCost()));
    }

    public static void main(String[] args) {
        Travel_UCS t = new Travel_UCS("RomaniaMap.txt");
        t.search();
    }

    public static class ComparePathCost implements Comparator<Node<String, String>> {
        public int compare(Node<String, String> o1, Node<String, String> o2) {
            return Integer.compare(o1.getPathCost(), o2.getPathCost());
        }
    }
}
