package search_problems;

import core_search.Problem;
import core_search.Tuple;

import java.io.File;
import java.util.*;

public class Travel implements Problem<String,String> {

    private final String MAP_FILE;
    private final String ESTIMATE_FILE;

    public Travel(String mapFile) {
        this.MAP_FILE = mapFile;
        this.ESTIMATE_FILE = null;
        buildTransitionModel();
    }

    public Travel(String mapFile, String estimateFile) {
        // a second constructor that additionally includes straight-line
        // distances as estimated forward costs, which are needed by
        // greedy best-first search (GBFS) and A* search
        this.MAP_FILE = mapFile;
        this.ESTIMATE_FILE = estimateFile;
        buildTransitionModel();
        buildEstimatedDistances();
    }
    private final Map<String, List<Tuple<String, String>>> transitionModel = new HashMap<>();
    private final Map<String, Integer> estimatedDistances = new HashMap<>();

    private void buildTransitionModel(){
        try(Scanner sc = new Scanner(new File(MAP_FILE))){
            while(sc.hasNext()) {
                String[] a = sc.nextLine().split(":");
                String cityA = a[0];
                String cityB = a[1];
                int cost = Integer.parseInt(a[2]);
                if (transitionModel.containsKey(cityA)) {
                    //first time seeing this city, add a new entry to map
                    List<Tuple<String, String>> l = transitionModel.get(cityA);
                    l.add(new Tuple<>(cityB, STR."to\{cityB}", cost));
                    transitionModel.replace(cityA, l);
                } else {
                    // the city exits in map, add a new action to it
                    List<Tuple<String, String>> l = new ArrayList<>();
                    l.add(new Tuple<>(cityB, STR."to\{cityB}", cost));
                    transitionModel.put(cityA, l);
                }
                if (transitionModel.containsKey(cityB)) {
                    List<Tuple<String, String>> l = transitionModel.get(cityB);
                    l.add(new Tuple<>(cityA, STR."to\{cityA}", cost));
                    transitionModel.replace(cityB, l);
                } else {
                    List<Tuple<String, String>> l = new ArrayList<>();
                    l.add(new Tuple<>(cityA, STR."to\{cityA}", cost));
                    transitionModel.put(cityB, l);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void buildEstimatedDistances(){
        try(Scanner sc = new Scanner(new File(ESTIMATE_FILE))){
            while(sc.hasNext()) {
                String[] a = sc.nextLine().split(":");
                String city = a[0];
                int estimate = Integer.parseInt(a[1]);
                estimatedDistances.put(city, estimate);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getEstimatedDistance (String s){
        return estimatedDistances.get(s);
    }

    public String initialState() {
        return "Arad";
    }
    public String goalState() {
        return "Bucharest";
    }
    public List<Tuple<String,String>> execution(String State) {
        return transitionModel.getOrDefault(State, null);
    }
    public void printState(String city) {
        System.out.print(city);
    }

    //for testing only
    public static void main(String[] args){
        Travel t = new Travel("RomaniaMap.txt","RomaniaMapEstimates.txt");
        StringBuilder builder = new StringBuilder();
        for(Map.Entry<String,List<Tuple<String,String>>> entry : t.transitionModel.entrySet()){
            builder.append(!builder.isEmpty() ? "\n" : "")
                    .append(entry.getKey())
                    .append(": ")
                    .append(entry.getValue());
        }
        System.out.println(builder);
        StringBuilder builder2 = new StringBuilder();
        for(Map.Entry<String, Integer> entry : t.estimatedDistances.entrySet()){
            builder2.append(!builder.isEmpty() ? "\n" : "")
                    .append(entry.getKey())
                    .append(": ")
                    .append(entry.getValue());
        }
        System.out.println(builder2);
    }

}
