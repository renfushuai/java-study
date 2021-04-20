package com.rfs.javastudy.demo.design.factorymethod;

public class ProductFactory extends FactoryMethod{
    @Override
    protected Product creatProduct(EnumProductType name) {
        if (EnumProductType.activityOne==name){
            return new OneProduct();
        }
        switch (name){
            case activityOne:
                OneProduct oneProduct = new OneProduct();
                oneProduct.setOneName("haha");
                return oneProduct;
            case activityTwo:
                return new TwoProduct();
        }
        return null;
    }

    public static void main(String[] args) {
        FactoryMethod factoryMethod = new ProductFactory();
        Product product = factoryMethod.product(EnumProductType.activityOne, "手机");
        product.setPrice(111L);
        System.out.println(product.getName());
    }
}
