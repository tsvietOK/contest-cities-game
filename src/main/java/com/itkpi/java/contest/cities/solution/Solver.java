package com.itkpi.java.contest.cities.solution;

import java.util.*;

public class Solver {
    /**
     * This method should order cities names (strings) from allCitiesList argument
     * into longest chain possible following the rules of the "Cities game".
     *
     * <br><br>
     *
     * <b> Cities game rules: </b>
     * <li> Cities names should be ordered to the chain in which every next city name starting with the last letter of the previous city name. </li>
     * <li> The first city name can be any. </li>
     * <li> Each city name should be real. </li>
     * <li> All city names in the chain should be unique. </li>
     *
     * <br>
     *
     * <b>Example:</b> London, Naga, Aurora, Aswan
     *
     * <br><br>
     *
     * <b>Additional requirements:</b>
     * Time limit is 2 minutes
     */
    public List<String> solveCitiesGame(List<String> allCitiesList) {
        List<String> input = new ArrayList<>(allCitiesList);
        LinkedList<String> output = new LinkedList<>();
        LinkedList<String> unused = new LinkedList<>();

        output.add(input.get(0));
        input.remove(0);

        while (!input.isEmpty()) {
            String lastCity = output.getLast();

            String city = getCityStartsWith(input, getLastCharacter(lastCity));

            if (!city.equals("")) {
                output.add(city);
                input.remove(city);
            } else {
                unused.add(lastCity);
                output.removeLast();
            }
        }
        //reversed
        while (!unused.isEmpty()) {
            String firstCity = output.getFirst();

            String reversedCity = getCityEndsWith(unused, getFirstCharacter(firstCity));

            if (!reversedCity.equals("")) {
                output.addFirst(reversedCity);
                unused.remove(reversedCity);
            } else break;
        }
        return output;
    }

    private char getLastCharacter(String word) {
        return word.charAt(word.length() - 1);
    }

    private char getFirstCharacter(String word) {
        return word.charAt(0);
    }

    private String getCityEndsWith(List<String> cityList, Character firstCharacter) {
        String finalCity = "";
        for (String city : cityList) {
            if (city.endsWith(firstCharacter.toString().toLowerCase())) {
                finalCity = city;
                break;
            }
        }
        return finalCity;
    }

    private String getCityStartsWith(List<String> cityList, Character lastCharacter) {
        String finalCity = "";
        for (String city : cityList) {
            if (city.startsWith(lastCharacter.toString().toUpperCase())) {
                finalCity = city;
                break;
            }
        }
        return finalCity;
    }
}
