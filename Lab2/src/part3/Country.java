package part3;

import java.util.List;
import java.util.Map;

import static java.lang.Math.abs;

public class Country {
    private String name;

    private Map<Integer, Emission> emissions;

    public Country(String name, Map<Integer, Emission> emissions) {
        this.name = name;
        this.emissions = emissions;
    };

    public Map<Integer, Emission> getEmissions() {
        return emissions;
    }

    public String getName() {
        return name;
    }

    public int getYearWithHighestEmissions() {
        Map<Integer, Emission> emissions = this.emissions;
        double max = 0.0;
        int highestYear = 0;

        for (Map.Entry<Integer, Emission> current : emissions.entrySet()) {
            Integer year = current.getKey();
            Emission currentEmission = current.getValue();
            double emissionMax = currentEmission.getCH4() + currentEmission.getCO2() + currentEmission.getN2O();
            if (emissionMax > max) {
                max = emissionMax;
                highestYear = year;
            }
        }

        return highestYear;
    }

    public static Country countryWithHighestCH4InYear(List<Country> countries, int year) {
        double highestCH4 = 0;
        Country highestCountry = null;

        for (Country current : countries) {
            Map<Integer, Emission> emissions = current.emissions;
            double CH4 = emissions.get(year).getCH4();

            if (CH4 > highestCH4) {
                highestCH4 = CH4;
                highestCountry = current;
            }
        }
        System.out.println("The country with the highest methane gas emissions is " + highestCountry.name + " at " + highestCH4);
        return highestCountry;
    }

    public static Country countryWithHighestChangeInEmissions(List<Country> countries, int startYear, int endYear) {
        double highestEmissionChange = 0;
        Country highestCountry = null;

        for (Country current : countries) {
            Map<Integer, Emission> emissions = current.emissions;
            Emission emissionStart = emissions.get(startYear);
            Emission emissionEnd = emissions.get(endYear);


            double totalStart = emissionStart.getCH4() + emissionStart.getCO2() + emissionStart.getN2O();
            double totalEnd = emissionEnd.getCH4() + emissionEnd.getCO2() + emissionEnd.getN2O();

            double emissionChange = abs(totalStart - totalEnd);

            if (emissionChange > highestEmissionChange) {
                highestEmissionChange = emissionChange;
                highestCountry = current;
            }
        }
        System.out.println(highestCountry.name + " has the highest change in emissions at " + highestEmissionChange);
        return highestCountry;
    }
}
