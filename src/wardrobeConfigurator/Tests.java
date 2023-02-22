package wardrobeConfigurator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;


public class Tests {
    public static final WardrobeElement ONE = new WardrobeElement(1);
    public static final WardrobeElement TWO = new WardrobeElement(2);
    public static final WardrobeElement THREE = new WardrobeElement(3);
    private int length;
    private WardrobeElementCombination[] actualWardrobeElementCombinations;
    private WardrobeElementCombination availableElements;
    private WardrobeElementCombination[] expectedCombinations = new WardrobeElementCombination[0];

    private void givenTheLengthOfTheWallIs(int length) {
        this.length = length;
    }

    private void givenTheAvailableElementsAre(WardrobeElementCombination wardrobeElementCombination) {
        this.availableElements = wardrobeElementCombination;
    }

    private void givenTheAvailableElementsAre(WardrobeElement... elements) {
        this.availableElements = new WardrobeElementCombination(elements);
    }

    private void whenTheCombinationsAreCalculated() {
        this.actualWardrobeElementCombinations = new Wardrobe(this.length,this.availableElements).calculatePossibleCombinations();
    }

    private void thenOneCombinationIs(WardrobeElement... elements){
        var expected = new ArrayList<WardrobeElementCombination>(List.of(expectedCombinations));
        expected.add(new WardrobeElementCombination(elements));
        expectedCombinations = expected.toArray(expectedCombinations);
    }

    private void assertExpectations() {
        Arrays.sort(expectedCombinations);
        Arrays.sort(actualWardrobeElementCombinations);
        assertArrayEquals(expectedCombinations,actualWardrobeElementCombinations);
    }

    @Test
    void noWallNoCombinations() {
        givenTheLengthOfTheWallIs(0);
        givenTheAvailableElementsAre();
        whenTheCombinationsAreCalculated();
        assertExpectations();
    }

    @Test
    void wallHasLengthOneAndOnlyElementHasLengthOne() {
        givenTheLengthOfTheWallIs(1);
        givenTheAvailableElementsAre(ONE);
        whenTheCombinationsAreCalculated();
        thenOneCombinationIs(ONE);
        assertExpectations();
    }

    @Test
    void wallHasLengthOneAndOnlyElementHasLengthTwo() {
        givenTheLengthOfTheWallIs(1);
        givenTheAvailableElementsAre(TWO);
        whenTheCombinationsAreCalculated();
        assertExpectations();
    }

    @Test
    void wallHasLengthTwoAndOnlyElementHasLengthOne() {
        givenTheLengthOfTheWallIs(2);
        givenTheAvailableElementsAre(ONE);
        whenTheCombinationsAreCalculated();
        thenOneCombinationIs(ONE,ONE);
        assertExpectations();
    }

    @Test
    void wallHasLengthTwoAndTheElementHaveLengthOneAndTwo(){
        givenTheLengthOfTheWallIs(2);
        givenTheAvailableElementsAre(ONE,TWO);
        whenTheCombinationsAreCalculated();
        thenOneCombinationIs(ONE,ONE);
        thenOneCombinationIs(TWO);
        assertExpectations();
    }
    @Test
    void wallHasLengthFourAndTheElementHaveLengthTwoAndThree(){
        givenTheLengthOfTheWallIs(4);
        givenTheAvailableElementsAre(TWO,THREE);
        whenTheCombinationsAreCalculated();
        thenOneCombinationIs(TWO,TWO);
        assertExpectations();
    }

    @Test
    void acceptance(){
        givenTheLengthOfTheWallIs(250);
        givenTheAvailableElementsAre(new WardrobeElement(50),
                new WardrobeElement(100),
                new WardrobeElement(120),
                new WardrobeElement(75));
        whenTheCombinationsAreCalculated();
        thenOneCombinationIs(new WardrobeElement(100),new WardrobeElement(100),new WardrobeElement(50));
        thenOneCombinationIs(new WardrobeElement(100),new WardrobeElement(75),new WardrobeElement(75));
        thenOneCombinationIs(new WardrobeElement(100),new WardrobeElement(50),new WardrobeElement(50),new WardrobeElement(50));
        thenOneCombinationIs(new WardrobeElement(75),new WardrobeElement(75),new WardrobeElement(50),new WardrobeElement(50));
        thenOneCombinationIs(new WardrobeElement(50),new WardrobeElement(50),new WardrobeElement(50),new WardrobeElement(50),new WardrobeElement(50));
        assertExpectations();
    }

}
