package wardrobeConfigurator;

import java.util.Currency;
import java.util.Objects;

public class Money implements Comparable<Money> {
    private final MoneyRecord theMoney;

    Money(int amount, Currency currency ){
        this.theMoney = new MoneyRecord(amount,currency);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        return Objects.equals(theMoney, ((Money) o).theMoney);
    }

    @Override
    public int hashCode() {
        return theMoney != null ? theMoney.hashCode() : 0;
    }

    @Override
    public int compareTo(Money that) {
        assert(this.theMoney.currency.equals(that.theMoney.currency));
        return theMoney.amount()-that.theMoney.amount();
    }

    public Money add(Money money) {
        return new Money(theMoney.amount+money.theMoney.amount,money.theMoney.currency());
    }

    public int amount(){
        return theMoney.amount();
    }

    private record MoneyRecord(int amount, Currency currency) {
    }
}
