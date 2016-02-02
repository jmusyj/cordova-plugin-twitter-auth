//
//  TwitterAuthPlugin.m
//  Uforia
//
//  Created by Sava Savic on 1/26/15.
//
//

#import "TwitterAuthPlugin.h"
#import "AppDelegate.h"
#import "TwitterClient.h"

@implementation TwitterAuthPlugin

-(void)Login:(CDVInvokedUrlCommand *)command
{
    /*self.accountStore = [[ACAccountStore alloc] init];
    
    self.consumerKey = @"rf0TfX650MY1WBY9maItlHmVR";
    self.consumerSecret = @"KjdkqZ7tv3c1SeK3Y7GOBEQ6Ss0UxsPhVus0wcUwNfEbQOGeu8";
    
    self.twitter = [STTwitterAPI twitterAPIWithOAuthConsumerKey:self.consumerKey
                                                 consumerSecret:self.consumerSecret];
    
    
    [self.twitter postTokenRequest:^(NSURL *url, NSString *oauthToken) {
        NSLog(@"-- url: %@", url);
        NSLog(@"-- oauthToken: %@", oauthToken);
        
        [[UIApplication sharedApplication] openURL:url];

    } authenticateInsteadOfAuthorize:NO
                    forceLogin:@(YES)
                    screenName:nil
                 oauthCallback:@"Uforia://twitter_access_tokens/"
                    errorBlock:^(NSError *error) {
                        NSLog(@"-- error: %@", error);
                    }];*/
    
    [[TwitterClient sharedInstance].requestSerializer removeAccessToken];
    [[TwitterClient sharedInstance] fetchRequestTokenWithPath:@"oauth/request_token" method:@"GET" callbackURL:[NSURL URLWithString:@"Uforia://twitter_access_tokens/"] scope:nil success:^(BDBOAuth1Credential *requestToken) {
        NSURL *authURL = [NSURL URLWithString:[NSString stringWithFormat:@"https://api.twitter.com/oauth/authorize?oauth_token=%@", requestToken.token]];
        [[UIApplication sharedApplication] openURL:authURL];
    } failure:^(NSError *error) {
        NSLog(@"Failed to get access token!");
    }];
}


@end
