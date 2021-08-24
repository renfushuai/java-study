package com.rfs.redundantcode.reflection;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;

public class BetterBankService {
    /**
     * @author: rfs
     * @create: 2021/8/20
     * @description: 创建用户接口
     **/
    public static String createUser(String name, String identity, String mobile, int age) {
        CreateUserAPI createUserAPI = new CreateUserAPI();
        createUserAPI.setName(name);
        createUserAPI.setIdentity(identity);
        createUserAPI.setAge(age);
        createUserAPI.setMobile(mobile);
        return remoteCall(createUserAPI);
    }

    /**
     * @author: rfs
     * @create: 2021/8/20
     * @description: 支付接口
     **/
    public static String pay(long userId, BigDecimal amount) {
        PayAPI payAPI = new PayAPI();
        payAPI.setUserId(userId);
        payAPI.setAmount(amount);
        return remoteCall(payAPI);
    }

    public static String remoteCall(AbstractAPI api) {
        BackAPI backAPI = api.getClass().getAnnotation(BackAPI.class);
        StringBuilder stringBuilder = new StringBuilder();
        Arrays.stream(api.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(BankAPIField.class))
                .sorted(Comparator.comparingInt(a -> a.getAnnotation(BankAPIField.class).order()))
                .peek(field -> field.setAccessible(true))//设置可以访问私有字段
                .forEach(field -> {
                    BankAPIField bankAPIField = field.getAnnotation(BankAPIField.class);
                    Object value = "";
                    try {
                        value = field.get(api);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    stringBuilder.append(bankAPIField.type().format(value, bankAPIField));
                });
        //签名逻辑
//        stringBuilder.append(DigestUtils.md2Hex(stringBuilder.toString()));
//        String param = stringBuilder.toString();
//        long begin = System.currentTimeMillis();
//        //发请求
//        String result = Request.Post("http://localhost:45678/reflection" + bankAPI.url())
//                .bodyString(param, ContentType.APPLICATION_JSON)
//                .execute().returnContent().asString();
//        log.info("调用银行API {} url:{} 参数:{} 耗时:{}ms", bankAPI.desc(), bankAPI.url(), param, System.currentTimeMillis() - begin);
        return "";
    }
}