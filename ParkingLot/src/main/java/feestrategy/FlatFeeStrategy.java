package feestrategy;

public class FlatFeeStrategy implements FeeStrategy {

    final int rate;

    public FlatFeeStrategy(int rate) {
        this.rate = rate;
    }

    public FlatFeeStrategy() {
        this.rate = 20;
    }

    @Override
    public double calculateFee(long startTime, long endTime) {
        long timeInHours = (endTime - startTime) / (1000*60*60);
        return timeInHours*rate;
    }
}
