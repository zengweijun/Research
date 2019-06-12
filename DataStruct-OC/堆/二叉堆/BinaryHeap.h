//
//  BinaryHeap.h
//  DataStruct-OC
//
//  Created by ybf on 2019/6/12.
//  Copyright © 2019 nius. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface BinaryHeap : NSObject {
    int _size;
    NSComparisonResult(^_comparatorBlock)(id e1, id e2);
}

+ (instancetype)heap;
+ (instancetype)heapWithComparatorBlock:(NSComparisonResult(^)(id e1, id e2))comparatorBlock;
- (int)compareWithE1:(id)e1 e2:(id)e2;

/// 元素数量
- (int)size;

/// 是否为空
- (BOOL)isEmpty;

/// 清空
- (void)clear;

/// 添加元素
- (void)add:(id)o;

/// 获得堆顶元素
- (id)get;

/// 删除堆顶元素
- (id)remove;

/// 删除堆顶元素的同时插入一个新元素
- (id)replace:(id)o;

@end

NS_ASSUME_NONNULL_END
