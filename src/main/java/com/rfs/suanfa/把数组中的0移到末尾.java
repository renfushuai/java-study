package com.rfs.suanfa;

import com.alibaba.fastjson.JSON;

/**
 * @author renfushuai
 * @date 2022/1/27
 */
public class 把数组中的0移到末尾 {
    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 0, 3, 12};
        //int[] moveZeros = moveZeros(nums);
        int[] moveZeros = newMoveZeros(nums);
        System.out.println(JSON.toJSONString(moveZeros));
    }

    public static int[] moveZeros(int[] nums) {
        int[] newNums = new int[nums.length];
        int newNumsIndex = 0;
        for (int num : nums) {
            if (num != 0) {
                newNums[newNumsIndex] = num;
                newNumsIndex++;
            }
        }
        return newNums;
    }

    public static int[] newMoveZeros(int[] nums) {
        int idx = 0;
        for (int num : nums) {
            if (num != 0) {
                nums[idx++] = num;
            }
        }
        while (idx<nums.length){
            nums[idx++]=0;
        }
        return nums;
    }
}
