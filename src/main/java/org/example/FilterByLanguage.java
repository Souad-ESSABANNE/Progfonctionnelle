import java.util.List;
import java.util.stream.Collectors;

public class FilterByLanguage {
    public static List<Population> filter(List<Population> data) {
        return data.stream()
                .filter(p -> p.language().equals("English"))
                .collect(Collectors.toList());
    }
}