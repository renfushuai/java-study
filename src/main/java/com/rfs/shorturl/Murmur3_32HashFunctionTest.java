package com.rfs.shorturl;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.rfs.utils.ConversionUtil;

import java.nio.charset.Charset;
/**
* @author: rfs
* @create: 2021/4/22
* @description: MurmurHash 提供了两种长度的哈希值，32 bit，128 bit，为了让网址尽可通地短，我们选择 32 bit 的哈希值，
 * 32 bit 能表示的最大值近 43 亿，对于中小型公司的业务而言绰绰有余。对上文提到的极客长链做 MurmurHash 计算，得到的哈希值为 3002604296，
 * 于是我们现在得到的短链为 固定短链域名+哈希值 = http://gk.link/a/3002604296
 * **********************************************************************************************
 * 301代表什么？
 * 301代表的是永久重定向。什么意思呢?
 * 对于GET请求, 301跳转会默认被浏览器cache。也就是说，用户第一次访问某个短链接后，如果服务器返回301状态码，则这个用户在后续多次访问同一短链接地址，浏览器会直接请求跳转地址，而不会再去短链接系统上取！
 *
 * 这么做优点很明显，降低了服务器压力，但是无法统计到短链接地址的点击次数。
 *
 * 302代表什么？
 * 302代表的是临时定向。什么意思呢?
 * 对于GET请求, 302跳转默认不会被浏览器缓存，除非在HTTP响应中通过 Cache-Control 或 Expires 暗示浏览器缓存。因此，用户每次访问同一短链接地址，浏览器都会去短链接系统上取。
 *
 * 这么做的优点是，能够统计到短地址被点击的次数了。但是服务器的压力变大了。
**/
public class Murmur3_32HashFunctionTest {
    /*
    * 1.将长链（lurl）经过 MurmurHash 后得到短链。(10进制转换为62进制)
    * 2.再根据短链去 short_url_map 表中查找看是否存在相关记录，如果不存在，将长链与短链对应关系插入数据库中，存储。
    * 3. 步骤2是瓶颈（用所有生成的短网址构建布隆过滤器，当一个新的长链生成短链后，先将此短链在布隆过滤器中进行查找，如果不存在，说明 db 里不存在此短网址，可以插入！）
    * 4.如果存在，说明已经有相关记录了，此时在长串上拼接一个自定义好的字段，比如「DUPLICATE」，然后再对接接的字段串「lurl + DUPLICATE」做第一步操作，如果最后还是重复呢，再拼一个字段串啊，只要到时根据短链取出长链的时候把这些自定义好的字符串移除即是原来的长链。
   */
    public static void main(String[] args) {
        HashFunction function = Hashing.murmur3_32();
        HashCode hascode = function.hashString("https://www.cnblogs.com/xiekun/p/12500822.html", Charset.forName("utf-8"));
        System.out.println(hascode.asInt());
        System.out.println(ConversionUtil.encode(hascode.asInt()));
        HashCode hascode2 = function.hashString("hello2", Charset.forName("utf-8"));
        System.out.println(hascode2.asInt());
        HashCode hascode3 = function.hashString("hello3", Charset.forName("utf-8"));
        System.out.println(hascode3.padToLong());

    }

}
