import java.util.List;
import java.util.stream.Collectors;

public class FilterByPopulation {
    public static List<Population> filter(List<Population> data) {
        return data.stream()
                .filter(p -> p.population() > 30000000)
                .collect(Collectors.toList());
    }
}