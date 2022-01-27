package com.rfs.suanfa;

import lombok.ToString;

/**
 * @author renfushuai
 * @date 2022/1/27
 */
@ToString
public  class ListNode {
   public int data;
   public ListNode next;
   public ListNode(int data){
      this.data=data;
   }
   public ListNode(int data, ListNode next){
      this.data=data;
      this.next=next;
   }
}