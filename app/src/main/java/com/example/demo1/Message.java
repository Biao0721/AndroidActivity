package com.example.demo1;

public class Message {
    public static String LifecycleMethodList = "";
    public static String ActivityStatus = "";

    public static void AddMessage(String lifecycleMethodList) {
        LifecycleMethodList += lifecycleMethodList;
    }

    public static void AddActivity(String Activity, Boolean tmp) {
        if (tmp) {
            ActivityStatus += ( Activity +  ": Stoped\n");
        } else {
            ActivityStatus += ( Activity +  ": Destoryed\n");
        }
    }
}
