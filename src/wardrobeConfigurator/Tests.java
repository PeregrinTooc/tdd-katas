package wardrobeConfigurator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;


public class Tests {
    private static Money TWODOLLARS = new Money(2,Currency.getInstance("USD"));
    private static Money THREEDOLLARS = new Money(3,Currency.getInstance("USD"));
    private static Money FOURDOLLARS = new Money(4,Currency.getInstance("USD"));
    private static final WardrobeElement ONE = new WardrobeElement(1, TWODOLLARS);
    private static final WardrobeElement TWO = new WardrobeElement(2, THREEDOLLARS);
    private static final WardrobeElement THREE = new WardrobeElement(3,FOURDOLLARS);

    private int length;
    private WardrobeElementCombination[] actualWardrobeElementCombinations;
    private WardrobeElementCombination availableElements;
    private WardrobeElementCombination[] expectedCombinations = new WardrobeElementCombination[0];

    private static Money FIFTYNINEDOLLARS = new Money(59,Currency.getInstance("USD"));
    private static Money SIXTYTWODOLLARS = new Money(62,Currency.getInstance("USD"));
    private static Money NINETYDOLLARS = new Money(90,Currency.getInstance("USD"));
    private static Money HUNDREDELEVENDOLLARS = new Money(111,Currency.getInstance("USD"));

    private static final WardrobeElement FIFTY = new WardrobeElement(50, FIFTYNINEDOLLARS);
    private static final WardrobeElement SEVENTYFIVE = new WardrobeElement(75, SIXTYTWODOLLARS);
    private static final WardrobeElement HUNDRED = new WardrobeElement(100, NINETYDOLLARS);
    private static final WardrobeElement HUNDREDTWENTY = new WardrobeElement(120, HUNDREDELEVENDOLLARS);
    private WardrobeElementCombination expectedCheapestCombination = new WardrobeElementCombination();
    private WardrobeElementCombination actualCheapestCombination;

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
        var wardrobe = new Wardrobe(this.length, this.availableElements);
        this.actualWardrobeElementCombinations = wardrobe.calculatePossibleCombinations();
        this.actualCheapestCombination = wardrobe.calculateCheapestCombination();
    }

    private void thenOneCombinationIs(WardrobeElement... elements){
        var expected = new ArrayList<WardrobeElementCombination>(List.of(expectedCombinations));
        expected.add(new WardrobeElementCombination(elements));
        expectedCombinations = expected.toArray(expectedCombinations);
    }

    private void thenTheCheapestCombinationIs(WardrobeElement... cheapest) {
        this.expectedCheapestCombination = new WardrobeElementCombination(cheapest);
    }

    private void assertExpectations() {
        Arrays.sort(expectedCombinations);
        Arrays.sort(actualWardrobeElementCombinations);
        assertArrayEquals(expectedCombinations,actualWardrobeElementCombinations);
        assertEquals(expectedCheapestCombination, actualCheapestCombination);
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
        thenTheCheapestCombinationIs(ONE);
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
        thenTheCheapestCombinationIs(ONE,ONE);
        assertExpectations();
    }
    @Test
    void wallHasLengthTwoAndTheElementHaveLengthOneAndTwo(){
        givenTheLengthOfTheWallIs(2);
        givenTheAvailableElementsAre(ONE,TWO);
        whenTheCombinationsAreCalculated();
        thenOneCombinationIs(ONE,ONE);
        thenOneCombinationIs(TWO);
        thenTheCheapestCombinationIs(TWO);
        assertExpectations();
    }

    @Test
    void wallHasLengthFourAndTheElementHaveLengthTwoAndThree(){
        givenTheLengthOfTheWallIs(4);
        givenTheAvailableElementsAre(TWO,THREE);
        whenTheCombinationsAreCalculated();
        thenOneCombinationIs(TWO,TWO);
        thenTheCheapestCombinationIs(TWO,TWO);
        assertExpectations();
    }

    @Test
    void acceptance(){
        givenTheLengthOfTheWallIs(250);
        givenTheAvailableElementsAre(FIFTY,SEVENTYFIVE,HUNDRED,HUNDREDTWENTY);
        whenTheCombinationsAreCalculated();
        thenOneCombinationIs(HUNDRED,HUNDRED,FIFTY);
        thenOneCombinationIs(HUNDRED,SEVENTYFIVE,SEVENTYFIVE);
        thenOneCombinationIs(HUNDRED,FIFTY,FIFTY,FIFTY);
        thenOneCombinationIs(SEVENTYFIVE,SEVENTYFIVE,FIFTY,FIFTY);
        thenOneCombinationIs(FIFTY,FIFTY,FIFTY,FIFTY, FIFTY);
        thenTheCheapestCombinationIs();
        assertExpectations();
    }

    @Test
    void withPrices(){

    }

}
