//
//  Queue.m
//  AFNetworking
//
//  Created by 曾维俊 on 2019/5/8.
//

#import "Queue.h"

@implementation Queue {
    NSMutableArray *_array;
}

- (instancetype)init {
    self = [super init];
    if (self) {
        _array = [NSMutableArray array];
    }
    return self;
}

- (NSInteger)count {
    return _array.count;
}

- (BOOL)isEmpty {
    return _array.count == 0;
}

- (void)enQueue:(id)o {
    if (!o) NSAssert(o == nil, @"元素不能为nil");
    [_array addObject:o];
}

- (id)deQueue {
    if (self.isEmpty) return nil;
    id o = _array.firstObject;
    [_array removeObject:o];
    return o;
}

- (id)peekFront {
    if (self.isEmpty) return nil;
    return _array.firstObject;
}

- (void)clear {
    [_array removeAllObjects];
}

@end
