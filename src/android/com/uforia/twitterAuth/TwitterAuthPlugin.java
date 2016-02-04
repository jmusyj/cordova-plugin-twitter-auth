package com.uforia.twitterAuth;

import android.content.Intent;
import android.net.Uri;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterAuthPlugin extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {        
        if (action.equals("Login")) {
            try {
                twitterAuth(callbackContext);
            } catch (TwitterException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    void twitterAuth(CallbackContext callbackContext) throws TwitterException, IOException {
        ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.setOAuthConsumerKey("rf0TfX650MY1WBY9maItlHmVR");
        builder.setOAuthConsumerSecret("KjdkqZ7tv3c1SeK3Y7GOBEQ6Ss0UxsPhVus0wcUwNfEbQOGeu8");
        twitter4j.conf.Configuration configuration = builder.build();
        TwitterFactory factory = new TwitterFactory(configuration);
        Twitter twitter = factory.getInstance();
        final RequestToken requestToken = twitter.getOAuthRequestToken("http://com.uforia/twitter-access-tokens");
        cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                cordova.getActivity().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(requestToken.getAuthenticationURL())));
            }
        });
        PluginResult result = new PluginResult(PluginResult.Status.OK);
        callbackContext.sendPluginResult(result);
    }

    @Override
    public void onNewIntent(Intent intent) {
        this.cordova.getActivity().setIntent(intent);
    }
}
