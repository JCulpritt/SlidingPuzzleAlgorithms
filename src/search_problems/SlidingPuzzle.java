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
            if (state.get(i) != 0 && !Objects.equals(state.get(i), goalState().get(i))) {
                heuristic++;
            }
        }
        System.out.println(heuristic);
        return heuristic;
    }

    public int calculateDistanceHeuristic(ArrayList<Integer> state) {
        int heuristic = 0;
        int dimensions = (int) Math.sqrt(state.size());

        for (int i = 0; i < state.size(); i++) {
            int valueOfTile = state.get(i);
            if (valueOfTile != 0) {  // Ignore empty tile (0)
                int row1 = i / dimensions, col1 = i % dimensions;  // Current position
                int row2 = (valueOfTile - 1) / dimensions, col2 = (valueOfTile - 1) % dimensions;  // Corrected goal position

                int distance = Math.abs(row1 - row2) + Math.abs(col1 - col2);
                heuristic += distance;
            }
        }
        return heuristic;
    }


    public void buildTransitionModel(ArrayList<Integer> state) {
        int cost = 1;
        int dimensions = (int) Math.sqrt(state.size());

        for (int i = 0; i < state.size(); i++) {
            if (state.get(i) == 0) {
                List<Tuple<ArrayList<Integer>, String>> transitions = new ArrayList<>();
                Set<ArrayList<Integer>> seenStates = new HashSet<>();

                int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
                String[] moveNames = {"toUP", "toDOWN", "toLEFT", "toRIGHT"};

                int row = i / dimensions, col = i % dimensions;
                for (int j = 0; j < moves.length; j++) {
                    int newRow = row + moves[j][0];
                    int newCol = col + moves[j][1];

                    if (newRow >= 0 && newRow < dimensions && newCol >= 0 && newCol < dimensions) {
                        int newIndex = newRow * dimensions + newCol;
                        ArrayList<Integer> newState = swap(state, i, newIndex);

                        if (seenStates.add(newState)) {  // Prevent duplicate states
                            transitions.add(new Tuple<>(newState, moveNames[j], cost));
                        }
                    }
                }
                transitionModel.put(new ArrayList<>(state), transitions);
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


//    public ArrayList<Integer> initialState() {
//        return new ArrayList<>(Arrays.asList(7, 2, 4, 5, 0, 6, 8, 3, 1));
//
//    }
//
//    public ArrayList<Integer> goalState() {
//        return new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8));
//    }

    public ArrayList<Integer> initialState() {
        return new ArrayList<>(Arrays.asList(12, 1, 2, 15, 11, 6, 5, 8, 7, 10, 9, 4, 0, 13, 14, 3));

    }

    public ArrayList<Integer> goalState() {
        return new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0));
    }


    private ArrayList<Integer> swap(ArrayList<Integer> state, int i, int j) {
        ArrayList<Integer> newState = new ArrayList<>(state);
        newState.set(i, newState.get(i) ^ newState.get(j));
        newState.set(j, newState.get(i) ^ newState.get(j));
        newState.set(i, newState.get(i) ^ newState.get(j));
        return newState;
    }


    public List<Tuple<ArrayList<Integer>, String>> execution(ArrayList<Integer> State) {
        buildTransitionModel(State);
        return transitionModel.getOrDefault(State, null);
    }

    public void printState(ArrayList<Integer> state) {
        int dimensions = (int) Math.sqrt(state.size());
        for (int i = 0; i < state.size(); i++) {
            if (state.get(i) == 0) {
                System.out.printf("%-3s", " "); // Print blank space for 0
            } else {
                System.out.printf("%-3d", state.get(i)); // Print the number with 3-character width
            }

            // Print a separator (|) unless it's the last element in the row
            if ((i + 1) % dimensions != 0) {
                System.out.print("| ");
            }

            // Print a new line after the last element in each row
            if ((i + 1) % dimensions == 0) {
                System.out.println();
            }
        }
    }


}
