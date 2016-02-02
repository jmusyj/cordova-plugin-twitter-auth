/**
 * cordova TwitterAuthPlugin
 * Copyright (c) Avramovic Uros 2014
 * Cordova API
 */
(function (cordova) {
    var TwitterAuthPlugin = function () {
    };

    TwitterAuthPlugin.prototype.includeSystemApps = false;

    TwitterAuthPlugin.prototype.Login = function (success, fail) {
        return cordova.exec(function (args) {
            success(args);
        }, function (args) {
            fail(args);
        }, 'TwitterAuthPlugin', 'Login', []);
    };

    TwitterAuthPlugin.install = function () {
        if (!window.plugins) {
            window.plugins = {};
        }

        window.plugins.TwitterAuthPlugin = new TwitterAuthPlugin();
    };

    TwitterAuthPlugin.install();

})(window.cordova);