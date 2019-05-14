//
//  Queue.h
//  AFNetworking
//
//  Created by 曾维俊 on 2019/5/8.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface Queue : NSObject

- (NSInteger)count;
- (BOOL)isEmpty;
- (void)enQueue:(id)o;
- (id)deQueue;
- (id)peekFront;
- (void)clear;

@end

NS_ASSUME_NONNULL_END
