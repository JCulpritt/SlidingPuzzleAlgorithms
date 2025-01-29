package search_problems;

import core_search.Problem;
import core_search.Tuple;

import java.io.File;
import java.util.*;

public class SlidingPuzzle implements Problem<String,String> {

    public SlidingPuzzle() {
        buildTransitionModel(initialState());
    }

    private final Map<String, List<Tuple<String, String>>> transitionModel = new HashMap<>();



    public int calculateHeuristic(String state) {
        int heuristic = 0;
        for (int i = 0; i < state.length(); i++) {
            if (state.charAt(i) != '0' && state.charAt(i) != goalState().charAt(i)) {
                heuristic++;
            }
        }
        return heuristic;
    }

    public void buildTransitionModel(String state) {
        transitionModel.clear();
        int cost = 1;

        int[][] moves = {
                {-1, 0}, // Move up
                {1, 0},  // Move down
                {0, -1}, // Move left
                {0, 1}  // MOve right
        };

        String[] moveNames = {"toUP", "toDOWN", "toLEFT", "toRIGHT"};

        int row = state.indexOf('0') / 3;
        int col = state.indexOf('0') % 3;

        List<Tuple<String, String>> transitions = new ArrayList<>();

        for (int i = 0; i < moves.length; i++) {
            int newRow = row + moves[i][0];
            int newCol = col + moves[i][1];

            int newIndex = newRow * 3 + newCol;
            String newState = swap(state, state.indexOf('0'), newIndex);
            transitions.add(new Tuple<>(newState, moveNames[i], cost));
        }

        transitionModel.put(state, transitions);
    }


    public static void main(String[] args){
        SlidingPuzzle S = new SlidingPuzzle();
        StringBuilder builder = new StringBuilder();
        for(Map.Entry<String,List<Tuple<String,String>>> entry : S.transitionModel.entrySet()){
            builder.append(!builder.isEmpty() ? "\n" : "")
                    .append(entry.getKey())
                    .append(": ")
                    .append(entry.getValue());
        }
        System.out.println(builder);
    }


    public String initialState() {
        return "456780213";
    }

    public String goalState() {
        return "123456780";
    }
    private String swap(String state, int i, int j) {
        char[] arr = state.toCharArray();
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        return new String(arr);
    }

    public List<Tuple<String, String>> execution(String State) {
        return transitionModel.getOrDefault(State, null);
    }

    public void printState(String state) {
        for(int i = 0; i < 9; i = i+3) {
            System.out.println(state.substring(i, i+3));
        }
    }


}
