package reflection.modifiers.modifiers_test.auction;

import java.io.Serializable;

public abstract class Bid implements Serializable {
    protected int price;
    protected String bidderName;

    public int getPrice() {
        return price;
    }

    public String getBidderName() {
        return bidderName;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int price;

        private String bidderName;

        public String getBidderName() {
            return bidderName;
        }

        public Builder setBidderName(String bidderName) {
            this.bidderName = bidderName;
            return this;
        }

        public int getPrice() {
            return price;
        }

        public Builder setPrice(int price) {
            this.price = price;
            return this;
        }

        public Bid build() {
            return new BidImpl();
        }

        private class BidImpl extends Bid {
            private BidImpl() {
                this.bidderName = Builder.this.bidderName;
                this.price = Builder.this.price;
            }
        }
    }
}
