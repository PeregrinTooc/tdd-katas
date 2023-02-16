import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TransitiveDependenciesTests {
    private String[][] dependencies;
    private String start;

    @Test
    void noDependencies() {
        givenTheDependencies(new String[][]{{"a"}});
        whenTheStartIs("a");
        ThenTheListOfTransitiveDependenciesIs(new String[]{});
    }

    private void ThenTheListOfTransitiveDependenciesIs(String[] expectedTransitiveDependencies) {
        assertArrayEquals(expectedTransitiveDependencies,new TransitiveDependencies(dependencies).calculateFor(start));
    }

    private void whenTheStartIs(String start ) {
        this.start = start;
    }

    private void givenTheDependencies(String[][] dependencies ) {
        this.dependencies = dependencies;
    }

}
