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


        output.add(input.get(0));
        input.remove(0);

        while (!input.isEmpty()) {
            String lastCity = output.getLast();

            String city = getCity(input, getLastCharacter(lastCity));

            if (city.equals("")) {
                output.removeLast();
            } else {
                output.add(city);
                input.remove(city);
            }
        }
        return output;
    }

    private char getLastCharacter(String word) {
        return word.charAt(word.length() - 1);
    }

    private String getCity(List<String> cityList, Character character) {
        String finalCity = "";
        for (String city : cityList) {
            if (city.startsWith(character.toString().toUpperCase())) {
                finalCity = city;
                break;
            }
        }
        return finalCity;
    }
}
