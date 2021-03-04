package ru.technicalExcellence.codingDojo;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Digit extends AbstractDigit {

    private final Integer value;
    private final String strValue;

    private Digit(String digitAsString) throws InputStringIsInvalidException {
        strValue = Optional.ofNullable(digitAsString).orElseThrow(InputStringIsInvalidException::new);
        if (strValue.isBlank()) {
            throw new InputStringIsInvalidException("Input is " + strValue);
        }

        if (STRING_2_DIGIT.containsKey(strValue)) {
            value = STRING_2_DIGIT.getOrDefault(strValue, null);
        } else {
            value = Integer.valueOf(IntStream.range(0, (strValue.length() - DIGIT_HEIGHT + 1) / (DIGIT_HEIGHT * DIGIT_WIDTH)).mapToObj((i) -> String.valueOf(Digit.of(Arrays.stream(strValue.split(EOF)).map(l -> l.substring(i * DIGIT_WIDTH, (i + 1) * DIGIT_WIDTH)).collect(Collectors.joining(EOF))).value)).collect(Collectors.joining()));
        }

        if (value == null) {
            throw new InputStringIsInvalidException("Unknown digit");
        }

    }

    public Digit(Integer digitAsNumber) throws InputStringIsInvalidException {
        value = digitAsNumber;

        if (DIGIT_2_STRING.containsKey(value)) {
            strValue = DIGIT_2_STRING.get(value);
        } else {
            final var list = Arrays.stream(String.valueOf(digitAsNumber).split("")).map((s) -> Digit.of(Integer.valueOf(s)).toString()).collect(Collectors.toList());
            strValue = IntStream.range(0, DIGIT_HEIGHT).mapToObj((i) -> list.stream().map((s) -> s.split(EOF)[i]).collect(Collectors.joining())).collect(Collectors.joining(EOF));
        }

        if (strValue == null) {
            throw new InputStringIsInvalidException("Input is " + value);
        }
    }

    public static Digit of(String digitAsString) throws InputStringIsInvalidException {
        return new Digit(digitAsString);
    }

    public static Digit of(Integer digitAsNumber) throws InputStringIsInvalidException {
        return new Digit(digitAsNumber);
    }

    public int value() {
        return value;
    }

    @Override
    public String toString() {
        return strValue;
    }
}
