package com.rfs.javastudy.suanfa;

public class ListNodeIterate {
}
class  ReverseList{
    static class ListNode{
        int val;
        ListNode next;
        public ListNode(int val,ListNode next){
            this.val=val;
            this.next=next;
        }
    }
    public static ListNode  iterate(ListNode head){
        ListNode prev=null,next;
        ListNode curr=head;
        while (curr!=null){
             next=curr.next;
             curr.next=prev;
             prev=curr;
             curr=next;
        }
        return prev;
    }
}