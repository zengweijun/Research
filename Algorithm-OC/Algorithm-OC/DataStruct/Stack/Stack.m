//
//  Stack.m
//  AFNetworking
//
//  Created by 曾维俊 on 2019/5/8.
//

#import "Stack.h"

@implementation Stack {
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

- (id)peek {
    if (self.isEmpty) return nil;
    return _array.lastObject;
}

- (void)push:(id)o {
    if (!o) NSAssert(o == nil, @"元素不能为nil");
    [_array addObject:o];
}

- (id)pop {
    if (self.isEmpty) return nil;
    id o = _array.lastObject;
    [_array removeObject:o];
    return o;
}

- (void)clear {
    [_array removeAllObjects];
}

@end
