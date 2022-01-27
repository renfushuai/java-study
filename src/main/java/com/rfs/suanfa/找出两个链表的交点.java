package com.rfs.suanfa;

/**
 * @author renfushuai
 * @date 2022/1/27
 * 著作权归https://pdai.tech所有。
 * 链接：https://pdai.tech/md/algorithm/alg-basic-linklist.html
 *
 * 要求: 时间复杂度为 O(N)，空间复杂度为 O(1)
 * 设 A 的长度为 a + c，B 的长度为 b + c，其中 c 为尾部公共部分长度，可知 a + c + b = b + c + a。
 * 当访问 A 链表的指针访问到链表尾部时，令它从链表 B 的头部开始访问链表 B；同样地，当访问 B 链表的指针访问到链表尾部时，
 * 令它从链表 A 的头部开始访问链表 A。这样就能控制访问 A 和 B 两个链表的指针能同时访问到交点。
 */
public class 找出两个链表的交点 {
    public static void main(String[] args) {
        int a1=1,a2=2,b1=3,b2=4,b3=5,c1=7,c2=8,c3=9;
        ListNode c3n=new ListNode(c3,null);
        ListNode c2n=new ListNode(c2,c3n);
        ListNode c1n=new ListNode(c1,c2n);

        ListNode a2n=new ListNode(a2,c1n);
        ListNode a1n=new ListNode(a1,a2n);

        ListNode b3n=new ListNode(b3,c1n);
        ListNode b2n=new ListNode(b2,b3n);
        ListNode b1n=new ListNode(b1,b2n);

        ListNode intersectionNode = getIntersectionNode(a1n, b1n);
        System.out.println(intersectionNode);

    }
    public static ListNode getIntersectionNode(ListNode headA,ListNode headB){
        ListNode l1=headA;
        ListNode l2=headB;
        while (l1!=l2){
            l1=(l1==null)?headB:l1.next;
            l2=(l2==null)?headA:l2.next;
        }
        return l1;
    }
}
