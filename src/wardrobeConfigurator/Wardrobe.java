package wardrobeConfigurator;

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
}
