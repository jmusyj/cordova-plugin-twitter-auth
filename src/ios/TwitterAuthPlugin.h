//
//  TwitterAuthPlugin.h
//  Uforia
//
//  Created by Sava Savic on 1/26/15.
//
//

#import <Cordova/CDV.h>

@interface TwitterAuthPlugin : CDVPlugin

@property (nonatomic, weak) NSString            *consumerKey;
@property (nonatomic, weak) NSString            *consumerSecret;

- (void)Login:(CDVInvokedUrlCommand*)command;

@end
