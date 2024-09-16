package part1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * THIS CLASS WON'T COMPILE UNTIL YOU CREATE YOUR COUNTRY AND SECTOR CLASSES
 */
public class PartOneTestCases {

    @Test
    public void testEmission() {
        Emission emission = new Emission(1.1, 2.2, 3.3);
        assertEquals(emission.getCH4(), 3.3);
        assertEquals(emission.getCO2(), 1.1);
        assertEquals(emission.getN2O(), 2.2);
    }

    @Test
    public void testCountry() {
        Map<Integer, Emission> emissions = new HashMap<>();
        emissions.put(1970, new Emission(288000.0, 684000.0, 4690000.0));
        emissions.put(1971, new Emission(290000.0, 689000.0, 4560000.0));
        Country country = new Country("United States", emissions);

        assertEquals(country.getName(), "United States");
        assertEquals(country.getEmissions(), emissions);
    }

    @Test
    public void testSector() {
        Map<Integer, Double> emissions = new HashMap<>();
        emissions.put(1970, 2278.8);
        emissions.put(1971, 2358.43);
        Sector sector = new Sector("Transport", emissions);

        assertEquals(sector.getName(), "Transport");
        assertEquals(sector.getEmissions(), emissions);
    }

    @Test
    public void testYearWithHighestEmissionCountry() {
        Map<Integer, Emission> emissions = new HashMap<>();
        emissions.put(1970, new Emission(288000.0, 684000.0, 4690000.0));
        emissions.put(1971, new Emission(290000.0, 689000.0, 4560000.0));
        Country country = new Country("United States", emissions);

        assertEquals(Util.getYearWithHighestEmissions(country), 1970);
    }

    @Test
    public void testYearWithHighestEmissionSector() {
        Map<Integer, Double> emissions = new HashMap<>();
        emissions.put(1970, 2278.8);
        emissions.put(1971, 2358.43);
        Sector sector = new Sector("Transport", emissions);

        assertEquals(Util.getYearWithHighestEmissions(sector), 1971);
    }


    /**
     * Tests the implementation of the Emission class.
     *
     * TO-DO: Examine this test case to know what you should name your public methods.
     *
     * @throws NoSuchMethodException
     */
    @Test
    public void testEmissionImplSpecifics() throws NoSuchMethodException {
        final List<String> expectedMethodNames = Arrays.asList("getCO2", "getN2O", "getCH4");

        final List<Class> expectedMethodReturns = Arrays.asList(double.class, double.class, double.class);

        final List<Class[]> expectedMethodParameters = Arrays.asList(new Class[0], new Class[0], new Class[0]);

        verifyImplSpecifics(Emission.class, expectedMethodNames, expectedMethodReturns,
                expectedMethodParameters);
    }

    /**
     * Tests the implementation of the Country class.
     *
     * TO-DO: Examine this test case to know what you should name your public methods.
     *
     * @throws NoSuchMethodException
     */
    @Test
    public void testCountryImplSpecifics() throws NoSuchMethodException {
        final List<String> expectedMethodNames = Arrays.asList("getName", "getEmissions");

        final List<Class> expectedMethodReturns = Arrays.asList(String.class, Map.class);

        final List<Class[]> expectedMethodParameters = Arrays.asList(new Class[0], new Class[0]);

        verifyImplSpecifics(Country.class, expectedMethodNames, expectedMethodReturns,
                expectedMethodParameters);
    }

    /**
     * Tests the part1 implementation of the Sector class.
     *
     * TO-DO: Examine this test to know what you should name your public methods.
     *
     * @throws NoSuchMethodException
     */
    @Test
    public void testSectorImplSpecifics() throws NoSuchMethodException {
        final List<String> expectedMethodNames = Arrays.asList("getName", "getEmissions");

        final List<Class> expectedMethodReturns = Arrays.asList(String.class, Map.class);

        final List<Class[]> expectedMethodParameters = Arrays.asList(new Class[0], new Class[0]);

        verifyImplSpecifics(Sector.class, expectedMethodNames, expectedMethodReturns,
                expectedMethodParameters);
    }

    private static void verifyImplSpecifics(final Class<?> clazz, final List<String> expectedMethodNames,
            final List<Class> expectedMethodReturns, final List<Class[]> expectedMethodParameters)
            throws NoSuchMethodException {
        assertEquals(0, clazz.getFields().length, "Unexpected number of public fields");

        final List<Method> publicMethods = Arrays.stream(clazz.getDeclaredMethods())
                .filter(m -> Modifier.isPublic(m.getModifiers())).collect(Collectors.toList());

        assertEquals(expectedMethodNames.size(), publicMethods.size(),
            "Unexpected number of public methods");

        assertTrue(expectedMethodNames.size() == expectedMethodReturns.size(),
            "Invalid test configuration");
        assertTrue(expectedMethodNames.size() == expectedMethodParameters.size(),
            "Invalid test configuration");

        for (int i = 0; i < expectedMethodNames.size(); i++) {
            Method method = clazz.getDeclaredMethod(expectedMethodNames.get(i), expectedMethodParameters.get(i));
            assertEquals(expectedMethodReturns.get(i), method.getReturnType());
        }
    }

}
