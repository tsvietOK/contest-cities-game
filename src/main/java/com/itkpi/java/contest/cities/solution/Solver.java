package com.itkpi.java.contest.cities.solution;

import com.itkpi.java.contest.cities.utils.StringUtils;

import java.util.LinkedList;
import java.util.List;


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
        LinkedList<String> input = new LinkedList<>(allCitiesList);
        LinkedList<String> output = new LinkedList<>();
        LinkedList<String> unused = new LinkedList<>();
        LinkedList<String> finalOutput = new LinkedList<>();

        output.add(input.getFirst());
        input.removeFirst();

        int maxScore = 0;

        while (!input.isEmpty()) {
            String lastCity = output.getLast();
            String city = getCityStartsWith(input, StringUtils.getLastCharacter(lastCity));

            if (!city.equals("")) {
                output.add(city);
                input.remove(city);
            } else {
                unused.add(lastCity);
                output.removeLast();
            }
            //reversed
            String firstCity = output.getFirst();
            String reversedCity = getCityEndsWith(unused, StringUtils.getFirstCharacter(firstCity));

            if (!reversedCity.equals("")) {
                output.addFirst(reversedCity);
                unused.remove(reversedCity);
            } else {
                unused.add(firstCity);
                output.removeFirst();
            }

            int score = getScore(output);
            if (score > maxScore) {
                maxScore = score;
                finalOutput = new LinkedList<>(output);
            }
        }
        return finalOutput;
    }

    private String getCityEndsWith(List<String> cityList, Character character) {
        String finalCity = "";
        for (String city : cityList) {
            if (city.endsWith(character.toString().toLowerCase()) && city.length() >= finalCity.length()) {
                finalCity = city;
            }
        }
        return finalCity;
    }

    private String getCityStartsWith(List<String> cityList, Character character) {
        String finalCity = "";
        for (String city : cityList) {
            if (city.startsWith(character.toString().toUpperCase()) && city.length() >= finalCity.length()) {
                finalCity = city;
            }
        }
        return finalCity;
    }

    private int getScore(LinkedList<String> list) {
        return list.stream().mapToInt(String::length).sum();
    }
}
