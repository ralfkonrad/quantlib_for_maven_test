package io.github.ralfkonrad;

import org.junit.jupiter.api.Test;
import org.quantlib.*;

public class EuropeanOptionTest {
    @Test
    public void testEuropeanOption() {
        // Set up the option parameters
        var spotPrice = 100.0;
        var strikePrice = 105.0;
        var riskFreeRate = 0.05;
        var volatility = 0.2;
        var maturityDate = new Date(31, 12, 2023);

        // Create the option objects
        var spot = new SimpleQuote(spotPrice);
        var rate = new SimpleQuote(riskFreeRate);
        var vol = new SimpleQuote(volatility);
        var yieldCurve = new FlatForward(0, new TARGET(), rate.value(), new Actual365Fixed());

        var payoff = new PlainVanillaPayoff(Option.Type.Call, strikePrice);
        var exercise = new EuropeanExercise(maturityDate);
        var europeanOption = new EuropeanOption(payoff, exercise);

        // Calculate the option price
        var calculator = new BlackCalculator(europeanOption, spot, yieldCurve, vol);
        var optionPrice = calculator.value();

        // Print the result
        System.out.println("Option price: " + optionPrice);
    }
}