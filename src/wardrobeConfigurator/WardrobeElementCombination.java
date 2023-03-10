package wardrobeConfigurator;

import java.util.*;
import java.util.stream.*;

public class WardrobeElementCombination implements Comparable<WardrobeElementCombination>{
    public static final WardrobeElementCombination[] EMPTY = new WardrobeElementCombination[0];
    private final List<WardrobeElement> elements = new ArrayList<WardrobeElement>();

    public WardrobeElementCombination(WardrobeElement... wardrobeElements) {
        elements.addAll((List.of(wardrobeElements)));
        elements.sort(null);
    }

    public WardrobeElementCombination(Stream<WardrobeElement> elements) {
        elements.forEach(wardrobeElement -> {this.elements.add(wardrobeElement);});
    }

    public static Comparator<? super WardrobeElementCombination> byPriceSorter() {
        return new Comparator<WardrobeElementCombination>() {
            @Override
            public int compare(WardrobeElementCombination one, WardrobeElementCombination other) {
                return one.price()-other.price();
            }
        };
    }

    private int price() {
       var moneyAccumulator = new MoneyAccumulatorImpl();
       this.elements.forEach(wardrobeElement -> wardrobeElement.price(moneyAccumulator));
       return moneyAccumulator.amount();

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WardrobeElementCombination that = (WardrobeElementCombination) o;
        return this.elements.containsAll(that.elements)
                && that.elements.containsAll(this.elements)
                &&this.elements.size() == that.elements.size();
    }

    public boolean cannotFit(int length) {
       return elements.stream().noneMatch( wardrobeElement -> {return wardrobeElement.fits(length);});
    }

    public WardrobeElementCombination[] combineTo(int length) {
        if(cannotFit(length)) {
            return EMPTY;
        }

        var combinationsWithSmallestElements = calculateCombinationsWithSmallestElement(length);
        var combinationsWithoutSmallestElement = calculateCombinationsWithoutSmallestElement(length);

        var allCombinations = new ArrayList<WardrobeElementCombination>(List.of(combinationsWithSmallestElements));
        allCombinations.addAll(List.of(combinationsWithoutSmallestElement));
        return allCombinations.toArray(EMPTY);
    }

    private WardrobeElementCombination[] calculateCombinationsWithoutSmallestElement(int length) {
        if(elements.size()==0)
            return EMPTY;
        return new WardrobeElementCombination(elements.stream().skip(1)).combineTo(length);}


    private WardrobeElementCombination[] calculateCombinationsWithSmallestElement(int length) {
        var combinationsWithSmallestElements = combineTo(elements.get(0).takeFrom(length));

        for( var combination : combinationsWithSmallestElements){
            combination.elements.add(elements.get(0));
        }
        if(combinationsWithSmallestElements.length == 0 && elements.get(0).hasLength(length))
            combinationsWithSmallestElements = new WardrobeElementCombination[]{
                    new WardrobeElementCombination(elements.get(0))};
        return combinationsWithSmallestElements;
    }

    @Override
    public int compareTo(WardrobeElementCombination that) {
        if(this.elements.size() == that.elements.size()){
            this.elements.sort(null);
            that.elements.sort(null);
            for (int i = 0; i < elements.size() ; i++) {
                if(!this.elements.get(i).equals(that.elements.get(i))) {
                    return this.elements.get(i).compareTo(that.elements.get(i));
                }
            }
            return 0;}
        return this.elements.size()-that.elements.size();
    }

    private class MoneyAccumulatorImpl implements  MoneyAccumulator{
        private Money money = null;
        @Override
        public void accumulate(Money money) {
            if(this.money == null){
                this.money = money;
            }else{
                this.money = this.money.add(money);
            }
        }

        public int amount() {
            return money == null ? 0 : money.amount();
        }
    }
}
