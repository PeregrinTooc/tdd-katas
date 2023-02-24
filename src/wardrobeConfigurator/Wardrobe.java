package wardrobeConfigurator;

import java.util.*;

public class Wardrobe {

    private final WardrobeElementCombination availableElements;
    private int length;

    public Wardrobe(int length, WardrobeElementCombination availableElements) {
        this.length = length;
        this.availableElements = availableElements;
    }

    public WardrobeElementCombination[] calculatePossibleCombinations() {
        if(length==0)
            return new WardrobeElementCombination[0];
        return availableElements.combineTo(length);
    }

    public WardrobeElementCombination calculateCheapestCombination() {
        var combinations = calculatePossibleCombinations();
        Arrays.stream(combinations).sorted((o1, o2) -> {return o1.isCheaperThan(o2);});
    }
}
