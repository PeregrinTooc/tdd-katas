package wardrobeConfigurator;

import java.util.*;

public class Wardrobe {

    private final WardrobeElementCombination availableElements;
    private final int length;

    public Wardrobe(int length, WardrobeElementCombination availableElements) {
        this.length = length;
        this.availableElements = availableElements;
    }

    public WardrobeElementCombination[] calculatePossibleCombinations() {
        if(length==0)
            return WardrobeElementCombination.EMPTY;
        return availableElements.combineTo(length);
    }

    public WardrobeElementCombination calculateCheapestCombination() {
        var combinations = calculatePossibleCombinations();
        var combinationsByPrice = new ArrayList<>(List.of(combinations));
        combinationsByPrice.sort(WardrobeElementCombination.byPriceSorter());
        return combinations.length == 0 ? new WardrobeElementCombination() : combinationsByPrice.get(0);
    }
}
