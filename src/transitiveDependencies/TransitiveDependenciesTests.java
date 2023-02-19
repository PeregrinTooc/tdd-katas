package transitiveDependencies;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TransitiveDependenciesTests {
    public static final String[] EMPTY = {};
    private String[][] dependencies;
    private String start;

    @Test
    void noDependencies() {
        givenTheDependencies(new String[][]{{"a"}});
        whenTheStartIs("a");
        ThenTheListOfTransitiveDependenciesIs(EMPTY);
    }
    @Test
    void singleDependency() {
        givenTheDependencies(new String[][]{{"a","b"}});
        whenTheStartIs("a");
        ThenTheListOfTransitiveDependenciesIs(new String[]{"b"});
        whenTheStartIs("b");
        ThenTheListOfTransitiveDependenciesIs(EMPTY);
    }

    @Test
    void simpleTransitiveDependency(){
        givenTheDependencies(new String[][]{{"a","b"},{"b","c"}});
        whenTheStartIs("a");
        ThenTheListOfTransitiveDependenciesIs(new String[]{"b","c"});
    }

    @Test
    void moreDirectDependencies(){
        givenTheDependencies(new String[][]{{"a","b","c"},{"b","c"}});
        whenTheStartIs("a");
        ThenTheListOfTransitiveDependenciesIs(new String[]{"b","c"});
    }

    @Test
    void acceptance(){
        givenTheDependencies(new String[][]{{"a","b","c"},{"b","c", "e"},{"c","g"},{"d","a","f"},{"e","f"},{"f","h"}});
        whenTheStartIs("a");
        ThenTheListOfTransitiveDependenciesIs(new String[]{"b","c","e","f","g","h"});
        whenTheStartIs("b");
        ThenTheListOfTransitiveDependenciesIs(new String[]{"c","e","f","g","h"});
        whenTheStartIs("c");
        ThenTheListOfTransitiveDependenciesIs(new String[]{"g"});
        whenTheStartIs("d");
        ThenTheListOfTransitiveDependenciesIs(new String[]{"a","b","c","e","f","g","h"});
        whenTheStartIs("f");
        ThenTheListOfTransitiveDependenciesIs(new String[]{"h"});
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
