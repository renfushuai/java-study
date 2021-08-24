package com.rfs.design.builder;

import java.util.Objects;

public class NyPizza extends Pizza {
    public enum Size {SMALL, MEDIUM, LARGE}

    private final Size size;

    private NyPizza(Builder builder) {
        super(builder);
        size = builder.size;
    }

    public static class Builder extends Pizza.Builder<NyPizza.Builder> {
        private final Size size;

        public Builder(Size size) {
            this.size = Objects.requireNonNull(size);
        }

        @Override
        public NyPizza build() {
            return new NyPizza(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    public static void main(String[] args) {
        NyPizza nyPizza = new NyPizza.Builder(Size.SMALL).addTopping(Topping.ONION).addTopping(Topping.HAM).build();
        nyPizza.toppings.forEach(m-> System.out.println(m));
        System.out.println(nyPizza.size);
    }
}
