//
//  main.m
//  03-数组中重复的数字
//
//  Created by ybf on 2019/6/17.
//  Copyright © 2019 nius. All rights reserved.
//

#import <Foundation/Foundation.h>

/*
 面试题3：数组中重复的数字
 
 在一个长度为n的数组里的所有数字都在0~n-1的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了
 几次。请找出数组中任意一个重复的数字。
例如，如果输入长度为7的数组{2,3,1,0,2,5,3},那么对应的输出是重复的数字2或者3。
 
-------------------------------------------------
 题意：数组长度为n，每个元素大小都在0~n-1之间，找出重复数字

-------------------------------------------------
 解决：
 1. 排序O(nlogn)
 2. 用哈希表，哈希函数将键key映射为数组下标O(n),空间复杂度O(n)
 3. 没有重复且排好序时，下标和值应该是相等的，所以扫描数组：
    当该处的值不在其位时，把它放到它应在的位置上
    当该处的值在其位时，扫描下一个元素
    一句话：把不再其位的值放到它应在的位置上
 */

/// 1.用哈希表思想（集合）
/// 思想：遍历数组，假设遇到数字j，先看set中是否存在该数字，存在说明重复，直接返回。否则添加到set
/// 时间复杂度：O(n)，代价【空间复杂度O(n)】
BOOL duplicate_1(int arr[], int length, int *duplication) {
    if (arr == NULL) {
        return NO;
    }
    
    for (int i = 0; i < length; i++) {
        if (arr[i] < 0 || arr[i] > length-1) {
            return NO;
        }
    }
    
    NSMutableSet<NSNumber *> *set = [NSMutableSet set];
    for (int i = 0; i < length; ++i) {
        int j = arr[i];
        if ([set containsObject:@(j)]) {
            *duplication = j;
            return YES;
        }
        [set addObject:@(j)];
    }
    return NO;
}

/// 2.当数组拍好序时，对应下标位置的元素值和下标值相等
/// 每次扫描到一个下标i发现，取出j = arr[i]
/// >>j == i，继续扫描下一个（i++）
/// >>j != i，判断
///     >> j == arr[j]，i和j位置发生重复，直接返回
///     >> j != arr[j]，交换arr[i] 和 arr[j]
/// i下标不动，重复上述过程
/**
 {2, 3, 1, 0, 2, 5, 3} i == 0
 {1, 3, 2, 0, 2, 5, 3} i == 0
 {3, 1, 2, 0, 2, 5, 3} i == 0
 {0, 1, 2, 3, 2, 5, 3} i == 0，i++
 {0, 1, 2, 3, 2, 5, 3} i == 1，i++
 {0, 1, 2, 3, 2, 5, 3} i == 2，i++
 {0, 1, 2, 3, 2, 5, 3} i == 3，i++
 {0, 1, 2, 3, 2, 5, 3} i == 4，arr[4] == arr[arr[4]] 重复
 */

/**
 要想找到特定问题最优算法，一定要观察该特定问题的特点。
 而这个问题的特点是，数组中出现的数字都在0~n-1范围之内。
 那么根据这个特点，如果数组中没有重复数字，那么应该下标和数字是一一对应的关系。
 当数组中出现重复数字之后，我们可以想象一下，会出现多个数字对应一个下标，而有的下标不对应任何数字
 
 比如扫描到i下标，而arr[i] == m，如果m和i不相等 且 m和arr[m]不相等，交换，此时m到了下标为m的位置
 对于原来m位置的元素被调整到了i位置i，而该元素在下一轮交换中一定会回到自己对于的位置
 因此，对于下标m位置的元素，最多（最坏情况）经过两次交换就回到自己位置，良好情况就是交换一次后就刚好和i相等
 所以，对于数组中的任何一个元素而言，最多经过两次交换就回到自己应有的位置
 */
/// 虽然有两次循环，但是每个数字最多交换两次就能找到属于自己的位置
/// 时间复杂度：O(n)，空间复杂度O(1)
BOOL duplicate_2(int arr[], int length, int *duplication) {
    if (arr == NULL) {
        return NO;
    }
    
    for (int i = 0; i < length; ++i) {
        if (arr[i] < 0 || arr[i] > length-1) {
            return NO;
        }
    }
    
    for (int i = 0; i < length; ++i) {
        // 如果发现arr[i] == i，索引和对应的value相等，不做处理，继续扫描下一个
        while (arr[i] != i) {
            if (arr[i] == arr[arr[i]]) {
                // 找到value对应的下标位置，如果相等，说明重复
                *duplication = arr[i];
                return YES;
            }
            
            // 如果不相等，做交换后继续重复此过程
            int tmp = arr[i];
            arr[i] = arr[tmp];
            arr[tmp] = tmp;
        }
    }
    return NO;
}

int main(int argc, const char * argv[]) {
    @autoreleasepool {
        int arr[] = {2,3,1,0,2,5,3};
        int length = sizeof(arr)/sizeof(int);
        for (int i = 0; i < length; ++i) { printf("%d\t", arr[i]); }
        printf("\n");
        int tmp;
        if (duplicate_1(arr, length, &tmp)) {
            printf("重复数字：%d\n", tmp);
        } else {
            printf("未找到重复数字\n");
        }
        
        for (int i = 0; i < length; ++i) { printf("%d\t", arr[i]); }
        printf("\n");
        if (duplicate_2(arr, length, &tmp)) {
            printf("重复数字：%d\n", tmp);
        } else {
            printf("未找到重复数字\n");
        }
        for (int i = 0; i < length; ++i) { printf("%d\t", arr[i]); }
        printf("\n");
    }
    return 0;
}
