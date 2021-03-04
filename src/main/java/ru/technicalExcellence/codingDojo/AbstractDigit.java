package ru.technicalExcellence.codingDojo;

import java.util.Map;
import java.util.stream.Collectors;

public class AbstractDigit {

    protected static final Map<String, Integer> STRING_2_DIGIT = Map.of(
            " __ \n|  |\n|__|", 0,
            "    \n   |\n   |", 1,
            " __ \n __|\n|__ ", 2,
            " __ \n __|\n __|", 3,
            "    \n|__|\n   |", 4,
            " __ \n|__ \n __|", 5,
            " __ \n|__ \n|__|", 6,
            " __ \n   |\n   |", 7,
            " __ \n|__|\n|__|", 8,
            " __ \n|__|\n __|", 9
    );
    protected static final Map<Integer, String> DIGIT_2_STRING = STRING_2_DIGIT.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));

    protected final static int DIGIT_WIDTH = 4;

    protected final static int DIGIT_HEIGHT = 3;

    protected final static String EOF = "\n";


}
