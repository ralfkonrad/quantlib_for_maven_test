package org.quantlib;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class LocalDateToQuantLibDateTest {
    @Test
    public void testLocalDate() {
        var qlDate = new Date(26, Month.June, 2023);
        var localDate = LocalDate.of(2023, 6, 26);
        Assertions.assertEquals(localDate, qlDate.toLocalDate());
    }
}
