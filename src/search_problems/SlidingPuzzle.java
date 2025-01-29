package search_problems;

import core_search.Problem;
import core_search.Tuple;
import java.util.*;

public class SlidingPuzzle implements Problem<ArrayList<Integer>, String> {

    public SlidingPuzzle() {
        buildTransitionModel(initialState());
    }

    private final Map<ArrayList<Integer>, List<Tuple<ArrayList<Integer>, String>>> transitionModel = new HashMap<>();



    public int calculateMismatchHeuristic(ArrayList<Integer> state) {
        int heuristic = 0;
        for (int i = 0; i < state.size(); i++) {
            if (state.get(i) != '0' && state.get(i) != goalState().get(i)) {
                heuristic++;
            }
        }
        return heuristic;
    }

    public int calculateDistanceHeuristic(ArrayList<Integer> state) {
        int heuristic = 0;
        for(int i = 0; i < state.size(); i++) {
            int valueOfTile =(state.get(i));
            if(valueOfTile != 0){
                int row1 = i / 3, col1 = i % 3;
                int row2 = valueOfTile / 3, col2 = valueOfTile % 3;
                int distance = Math.abs(row1 - row2) + Math.abs(col1 - col2);
                heuristic += distance;
            }

        }
        return heuristic;
    }

    public void buildTransitionModel(ArrayList<Integer> state) {
        transitionModel.clear();
        int cost = 1;  // Move cost

        for (int i = 0; i < state.size(); i++) {
            if (state.get(i) == 0) {  // Fix incorrect character comparison
                List<Tuple<ArrayList<Integer>, String>> transitions = new ArrayList<>();
                Set<ArrayList<Integer>> seenStates = new HashSet<>();  // Prevent duplicate states

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
                        ArrayList<Integer> newState = swap(state, i, newIndex);

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
        for(Map.Entry<ArrayList<Integer>,List<Tuple<ArrayList<Integer>, String>>> entry : S.transitionModel.entrySet()){
            builder.append(!builder.isEmpty() ? "\n" : "")
                    .append(entry.getKey())
                    .append(": ")
                    .append(entry.getValue());
        }
        System.out.println(builder);

    }


    public ArrayList<Integer> initialState() {
        return new ArrayList<>(Arrays.asList(7, 2, 4, 5, 0, 6, 8, 3, 1));

    }

    public ArrayList<Integer> goalState() {
        return new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8));
    }
    private ArrayList<Integer> swap(ArrayList<Integer> state, int i, int j) {
        ArrayList<Integer> newState = new ArrayList<>(state);  // Create a copy
        int temp = newState.get(i);
        newState.set(i, newState.get(j));
        newState.set(j, temp);
        return newState;
    }


    public List<Tuple<ArrayList<Integer>, String>> execution(ArrayList<Integer> State) {
        buildTransitionModel(State);
        return transitionModel.getOrDefault(State, null);
    }

    public void printState(ArrayList<Integer> state) {
        for(int i = 0; i < 9; i++) {
            if(state.get(i) == 0) {
                System.out.print("  ");
            }
            else {
                System.out.print(STR."\{state.get(i)} ");
            }
            if((i + 1) % 3 == 0) {
                System.out.print("\n");
            }

        }

    }


}
