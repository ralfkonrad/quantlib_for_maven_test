package io.github.ralfkonrad;

import org.junit.jupiter.api.Test;
import org.quantlib.*;

public class EuropeanOptionTest {
    @Test
    public void testEuropeanOption() {
        Settings.instance().setEvaluationDate(new Date(14, Month.July, 2023));
        var today = Settings.instance().getEvaluationDate();

        var dayCounter = new Actual360();
        var calendar = new NullCalendar();

        // Set up the option parameters
        var spotPrice = 100.0;
        var strikePrice = 105.0;
        var riskFreeRate = 0.05;
        var volatility = 0.2;
        var maturityDate = new Date(31, Month.December, 2023);

        // Create the option objects
        var spot = new QuoteHandle(new SimpleQuote(spotPrice));
        var rate = new QuoteHandle(new SimpleQuote(riskFreeRate));
        var vol = new QuoteHandle(new SimpleQuote(volatility));
        var yieldCurve = new YieldTermStructureHandle(new FlatForward(today, rate, dayCounter));
        var vola = new BlackVolTermStructureHandle(new BlackConstantVol(today, calendar, vol, dayCounter));

        var payoff = new PlainVanillaPayoff(Option.Type.Call, strikePrice);
        var exercise = new EuropeanExercise(maturityDate);
        var europeanOption = new EuropeanOption(payoff, exercise);

        // Calculate the option price
        var process = new BlackScholesProcess(spot, yieldCurve, vola);
        var engine = new AnalyticEuropeanEngine(process);
        europeanOption.setPricingEngine(engine);

        var npv = europeanOption.NPV();

        // Print the result
        System.out.println("Option price: " + npv);
    }
}