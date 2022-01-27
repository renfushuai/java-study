package com.rfs.suanfa;

/**
 * @author renfushuai
 * @date 2021/10/14
 */
public class 两数相加 {

    public static void main(String[] args) {

    }
    public ListNode addTwoNumber(ListNode l1,ListNode l2){
        ListNode pre = new ListNode(0);
        ListNode cur=pre;
        int carry=0;
        while (l1!=null||l2!=null){
            int x=l1==null?0:l1.data;
            int y=l2==null?0:l2.data;
            int sum=x+y+carry;
            //取商
            carry=sum/10;
            //取余数
            sum=sum%10;
            cur.next=new ListNode(sum);
            cur=cur.next;
            if (l1!=null){
                l1=l1.next;
            }
            if (l2!=null)
            {
                l2=l2.next;
            }
        }
        if (carry==1){
            cur.next=new ListNode(1);
        }
        return pre.next;
    }
}
