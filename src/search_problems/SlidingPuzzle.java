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

        for(int i = 0; i < state.length(); i++) {
            if(state.charAt(i) == '0') {
                if(i == 4) {
                    //4 possible moves
                    String temp1 = swap(state, i, i+3);
                    String temp2 = swap(state, i, i+1);
                    String temp3 = swap(state, i, i-1);
                    String temp4 = swap(state, i, i-3);

                    List<Tuple<String, String>> l = new ArrayList<>();
                    l.add(new Tuple<>(temp1, "toDOWN", cost));
                    l.add(new Tuple<>(temp2, "toRIGHT", cost));
                    l.add(new Tuple<>(temp3, "toLEFT", cost));
                    l.add(new Tuple<>(temp4, "toUP", cost));

                    transitionModel.put(state, l);

                }
                else if(i % 2 == 0) {
                    //2  possible moves
                    if(i == 0){
                        String temp1 = swap(state, i, i+1);
                        String temp2 = swap(state, i, i-3);

                        List<Tuple<String, String>> l = new ArrayList<>();
                        l.add(new Tuple<>(temp1, "toRIGHT", cost));
                        l.add(new Tuple<>(temp2, "toUP", cost));

                        transitionModel.put(state, l);
                    }
                    else if(i == 2){
                        String temp1 = swap(state, i, i-1);
                        String temp2 = swap(state, i, i-3);

                        List<Tuple<String, String>> l = new ArrayList<>();
                        l.add(new Tuple<>(temp1, "toLEFT", cost));
                        l.add(new Tuple<>(temp2, "toUP", cost));

                        transitionModel.put(state, l);
                    }
                    else if(i == 6){
                        String temp1 = swap(state, i, i+1);
                        String temp2 = swap(state, i, i+3);

                        List<Tuple<String, String>> l = new ArrayList<>();
                        l.add(new Tuple<>(temp1, "toRIGHT", cost));
                        l.add(new Tuple<>(temp2, "toDOWN", cost));

                        transitionModel.put(state, l);
                    }
                    else if(i == 8){
                        String temp1 = swap(state, i, i-1);
                        String temp2 = swap(state, i, i+3);

                        List<Tuple<String, String>> l = new ArrayList<>();
                        l.add(new Tuple<>(temp1, "toLEFT", cost));
                        l.add(new Tuple<>(temp2, "toDOWN", cost));

                        transitionModel.put(state, l);
                    }
                }
                else{
                    if(i == 1){
                        String temp1 = swap(state, i, i+1);
                        String temp2 = swap(state, i, i-3);
                        String temp3 = swap(state, i, i-1);

                        List<Tuple<String, String>> l = new ArrayList<>();
                        l.add(new Tuple<>(temp1, "toRIGHT", cost));
                        l.add(new Tuple<>(temp2, "toUP", cost));
                        l.add(new Tuple<>(temp3, "toLEFT", cost));

                        transitionModel.put(state, l);
                    }
                    else if(i == 3){
                        String temp1 = swap(state, i, i-1);
                        String temp2 = swap(state, i, i-3);
                        String temp3 = swap(state, i, i+1);

                        List<Tuple<String, String>> l = new ArrayList<>();
                        l.add(new Tuple<>(temp1, "toLEFT", cost));
                        l.add(new Tuple<>(temp2, "toDOWN", cost));
                        l.add(new Tuple<>(temp3, "toRIGHT", cost));

                        transitionModel.put(state, l);
                    }
                    else if(i == 5){
                        String temp1 = swap(state, i, i+1);
                        String temp2 = swap(state, i, i+3);
                        String temp3 = swap(state, i, i-1);

                        List<Tuple<String, String>> l = new ArrayList<>();
                        l.add(new Tuple<>(temp1, "toRIGHT", cost));
                        l.add(new Tuple<>(temp2, "toDOWN", cost));
                        l.add(new Tuple<>(temp3, "toLEFT", cost));

                        transitionModel.put(state, l);
                    }
                    else if(i == 7){
                        String temp1 = swap(state, i, i-1);
                        String temp2 = swap(state, i, i+3);
                        String temp3 = swap(state, i, i+1);

                        List<Tuple<String, String>> l = new ArrayList<>();
                        l.add(new Tuple<>(temp1, "toLEFT", cost));
                        l.add(new Tuple<>(temp2, "toDOWN", cost));
                        l.add(new Tuple<>(temp3, "toRIGHT", cost));

                        transitionModel.put(state, l);
                    }
                }
            }
        }
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
