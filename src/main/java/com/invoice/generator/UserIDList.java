package com.invoice.generator;

import java.util.ArrayList;

public class UserIDList {

    public static String getUserList() {
        String userId = null;
        ArrayList<String> userIdList = new ArrayList<>();
        userIdList.add("user1");
        userIdList.add("user2");
        for (int i = 0; i < userIdList.size(); i++) {
            userId = userIdList.get(i);
        }
        return userId;
    }
}
