package ru.technicalExcellence.codingDojo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DigitShould {

    private static Stream<Arguments> provideParameters() {
        return Stream.of(
                Arguments.of(" __ \n|  |\n|__|", 0),
                Arguments.of("    \n   |\n   |", 1),
                Arguments.of(" __ \n __|\n|__ ", 2),
                Arguments.of(" __ \n __|\n __|", 3),
                Arguments.of("    \n|__|\n   |", 4),
                Arguments.of(" __ \n|__ \n __|", 5),
                Arguments.of(" __ \n|__ \n|__|", 6),
                Arguments.of(" __ \n   |\n   |", 7),
                Arguments.of(" __ \n|__|\n|__|", 8),
                Arguments.of(" __ \n|__|\n __|", 9)
        );
    }

    @Test
    void throwExceptionWhenStringIsInvalid() {
        assertThrows(InputStringIsInvalidException.class, () -> Digit.of(""));
    }

    @ParameterizedTest
    @MethodSource("provideParameters")
    void convertToNumber(String arrange, Integer result) throws InputStringIsInvalidException {
        final var digit = Digit.of(arrange);
        assertEquals(result, digit.value());
        assertEquals(arrange, digit.toString());
    }

    @ParameterizedTest
    @MethodSource("provideParameters")
    void convertToString(String result, Integer arrange) throws InputStringIsInvalidException {
        final var digit = Digit.of(arrange);
        assertEquals(result, digit.toString());
        assertEquals(arrange, digit.value());
    }

    @Test
    void covertToNumbers() throws InputStringIsInvalidException {
        final var arrange = "     __  __      __  __  __  __  __  __ \n" +
                            "   | __| __||__||__ |__    ||__||__||  |\n" +
                            "   ||__  __|   | __||__|   ||__| __||__|";

        final var expected = 1234567890;

        final var digit = Digit.of(arrange);

        assertEquals(expected, digit.value());
        assertEquals(arrange, digit.toString());
    }

    @Test
    void covertToStrings() throws InputStringIsInvalidException {
        final var arrange = 1234567890;

        final var expected =    "     __  __      __  __  __  __  __  __ \n" +
                                "   | __| __||__||__ |__    ||__||__||  |\n" +
                                "   ||__  __|   | __||__|   ||__| __||__|";

        final var digit = Digit.of(arrange);

        assertEquals(arrange, digit.value());
        assertEquals(expected, digit.toString());
    }
}
