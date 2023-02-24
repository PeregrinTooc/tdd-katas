package wardrobeConfigurator;

import java.util.Currency;

public class WardrobeElement implements Comparable<WardrobeElement> {

    private final WardrobeElementRecord theElement;
    private final Money cost;

    WardrobeElement(int length, Money cost){
        this.theElement = new WardrobeElementRecord(length);
        this.cost = cost;
    }
    WardrobeElement(int length){
        this.theElement = new WardrobeElementRecord(length);
        this.cost = new Money(0, Currency.getInstance("EUR"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WardrobeElement that = (WardrobeElement) o;
        return theElement.equals(that.theElement);
    }

    @Override
    public int hashCode() {
        return theElement.hashCode();
    }

    public boolean fits(int length) {
        return length >= theElement.length();
    }

    @Override
    public int compareTo(WardrobeElement that) {
        if(this.equals(that))
            return 0;
        return this.theElement.length()>that.theElement.length ? 1 :-1;
    }

    public int takeFrom(int length) {
        return length - theElement.length();
    }

    public boolean hasLength(int length) {
        return length == theElement.length();
    }

    public int price() {
    }


    private record WardrobeElementRecord (int length){}
}
