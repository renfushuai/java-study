package com.rfs.suanfa;

/**
 * @author renfushuai
 * @date 2021/10/14
 */
public class 寻找两个正序数组的中位数 {
    public static void main(String[] args) {

    }
    public double findMedianSortedArrays(int[] num1,int[]num2){
        int[]nums;
        int m=num1.length;
        int n=num2.length;
        nums=new int[m+n];
        if (m==0){
            if (n%2==0){
                return (num2[n/2-1]+num2[n/2])/2.0;
            }
            return num2[n/2];
        }
        if (n==0){
            if (m%2==0){
                return (num1[m/2-1]+num1[m/2])/2.0;
            }
            return num1[m/2];
        }
        int count=0;
        int i=0,j=0;
        while (count!=(m+n)){
            //说明第一个数组已经遍历完了
            if (i==m){
                while (j!=n){
                    nums[count++]=num2[j++];
                }
                break;
            }
            //第二个数组遍历完了
            if (j==n){
                while (i!=m){
                    nums[count++]=num1[i++];
                }
                break;
            }
           if (num1[i]<num2[j]){
               nums[count++]=num1[i++];
           }else {
               nums[count++]=num2[j++];
           }
        }
        if (count%2==0){
            return (nums[count/2-1]+nums[count/2])/2.0;
        }
        return nums[count/2];
    }
}
