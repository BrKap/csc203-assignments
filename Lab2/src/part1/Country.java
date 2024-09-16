package part1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
