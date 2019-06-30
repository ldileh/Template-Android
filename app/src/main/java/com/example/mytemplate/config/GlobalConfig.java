package com.example.mytemplate.config;

public class GlobalConfig {

    private static final boolean isDebug = true;
    private static final String baseUrlProduction = "";
    private static final String baseUrlDev = "";
    private static final String baseUrlExample = "https://api.github.com/";
    private static final String sharePreferenceSession = "template_sp_session";

    public static String getBaseUrl(){
        return isDebug ? baseUrlDev : baseUrlProduction;
    }

    public static String getBaseUrlExample(){
        return baseUrlExample;
    }

    public static String getSharePreferenceSession() {
        return sharePreferenceSession;
    }
}
