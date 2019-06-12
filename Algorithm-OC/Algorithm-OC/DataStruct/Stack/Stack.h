//
//  Stack.h
//  AFNetworking
//
//  Created by 曾维俊 on 2019/5/8.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface Stack : NSObject

- (NSInteger)count;
- (BOOL)isEmpty;
- (id)peek;
- (void)push:(id)o;
- (id)pop;
- (void)clear;

@end

NS_ASSUME_NONNULL_END
