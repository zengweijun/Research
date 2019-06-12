//
//  main.m
//  BinaryHeap
//
//  Created by ybf on 2019/6/12.
//  Copyright © 2019 nius. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "BinaryHeap.h"

int main(int argc, const char * argv[]) {
    @autoreleasepool {
        BinaryHeap *heap = [BinaryHeap heapWithComparatorBlock:^NSComparisonResult(id  _Nonnull e1, id  _Nonnull e2) {
            return -1;
        }];
       
    }
    return 0;
}
