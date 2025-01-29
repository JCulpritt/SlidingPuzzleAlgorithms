package search_problems;

import core_search.Problem;
import core_search.Tuple;
import java.util.*;

public class SlidingPuzzle implements Problem<String,String> {

    public SlidingPuzzle() {
        buildTransitionModel(initialState());
    }

    private final Map<String, List<Tuple<String, String>>> transitionModel = new HashMap<>();



    public int calculateMismatchHeuristic(String state) {
        int heuristic = 0;
        for (int i = 0; i < state.length(); i++) {
            if (state.charAt(i) != '0' && state.charAt(i) != goalState().charAt(i)) {
                heuristic++;
            }
        }
        return heuristic;
    }

    public int calculateDistanceHeuristic(String state) {
        int heuristic = 0;
        for(int i = 0; i < state.length(); i++) {
            int valueOfTile = Integer.parseInt(Character.toString(state.charAt(i)));
            if(valueOfTile != 0){
                int row1 = i / 3, col1 = i % 3;
                int row2 = valueOfTile / 3, col2 = valueOfTile % 3;
                int distance = Math.abs(row1 - row2) + Math.abs(col1 - col2);
                heuristic += distance;
            }

        }
        return heuristic;
    }

    public void buildTransitionModel(String state) {
        transitionModel.clear();
        int cost = 1;

        for (int i = 0; i < state.length(); i++) {
            if (state.charAt(i) == '0') {
                List<Tuple<String, String>> transitions = new ArrayList<>();
                Set<String> seenStates = new HashSet<>();  // Track unique child states

                int[][] moves = {
                        {-1, 0}, // Up
                        {1, 0},  // Down
                        {0, -1}, // Left
                        {0, 1}   // Right
                };
                String[] moveNames = {"toUP", "toDOWN", "toLEFT", "toRIGHT"};

                int row = i / 3, col = i % 3;
                for (int j = 0; j < moves.length; j++) {
                    int newRow = row + moves[j][0];
                    int newCol = col + moves[j][1];

                    if (newRow >= 0 && newRow < 3 && newCol >= 0 && newCol < 3) {
                        int newIndex = newRow * 3 + newCol;
                        String newState = swap(state, i, newIndex);

                        if (!seenStates.contains(newState)) {  // Prevent duplicate states
                            seenStates.add(newState);
                            transitions.add(new Tuple<>(newState, moveNames[j], cost));
                        }
                    }
                }
                transitionModel.put(state, transitions);
            }
        }
    }



    public static void main(String[] args){
        SlidingPuzzle S = new SlidingPuzzle();
        System.out.println(S.calculateDistanceHeuristic(S.initialState()));
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
        return "724506831";
    }

    public String goalState() {
        return "012345678";
    }
    private String swap(String state, int i, int j) {
        char[] arr = state.toCharArray();
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        return new String(arr);
    }

    public List<Tuple<String, String>> execution(String State) {
        buildTransitionModel(State);
        return transitionModel.getOrDefault(State, null);
    }

    public void printState(String state) {
        for(int i = 0; i < 9; i++) {
            if(state.charAt(i) == '0') {
                System.out.print("  ");
            }
            else {
                System.out.print(STR."\{state.charAt(i)} ");
            }
            if((i + 1) % 3 == 0) {
                System.out.print("\n");
            }

        }

    }


}
