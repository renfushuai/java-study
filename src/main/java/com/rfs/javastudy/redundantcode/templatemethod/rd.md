## 普通用户需要收取运费，运费是商品价格的 10%，无商品折扣；
## VIP 用户同样需要收取商品价格 10% 的快递费，但购买两件以上相同商品时，第三件开始享受一定折扣；
## 内部用户可以免运费，无商品折扣。


#### 模板方法模式。我们在父类中实现了购物车处理的流程模板，然后把需要特殊处理的地方留空白也就是留抽象方法定义，让子类去实现其中的逻辑。由于父类的逻辑不完整无法单独工作，因此需要定义为抽象类。