package transitiveDependencies;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TransitiveDependencies {
    private final String[][] dependencies;

    public TransitiveDependencies(String[][] dependencies) {
        this.dependencies = dependencies;
    }
    public String[] calculateFor(String start) {
        var result = new HashSet<String>();
        for (var dependency: this.dependencies) {
            if(dependency[0].equals(start)){
                for (var i = 1; i<dependency.length; i++) {
                    result.add(dependency[i]);
                    result.addAll(List.of(calculateFor(dependency[i])));
                }
            }
        }
        return result.toArray(new String[0]);
    }
}
