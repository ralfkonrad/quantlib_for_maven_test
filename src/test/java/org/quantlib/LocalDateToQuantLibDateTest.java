package org.quantlib;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class LocalDateToQuantLibDateTest {
    @Test
    public void testLocalDate() {
        var localDate = LocalDate.of(2023, 6, 26);
        try (var qlDate = new Date(26, Month.June, 2023);
             var qlDateFromLocalDate = Date.of(localDate)) {
            Assertions.assertEquals(localDate, qlDate.toLocalDate());
            Assertions.assertEquals(qlDate.ISO(), qlDateFromLocalDate.ISO());
        }
    }
}
