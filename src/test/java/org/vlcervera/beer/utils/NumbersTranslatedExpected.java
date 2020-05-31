package org.vlcervera.beer.utils;

import org.assertj.core.util.Lists;

import java.util.List;

public class NumbersTranslatedExpected {

    public static List<String> getNumbersTranslatedFor15() {
        return
                Lists.newArrayList(
                        "1",
                        "2",
                        "fizz",
                        "4",
                        "buzz",
                        "fizz",
                        "7",
                        "8",
                        "fizz",
                        "buzz",
                        "11",
                        "fizz",
                        "13",
                        "14",
                        "fizzbuzz"
                                  );
    }
}
