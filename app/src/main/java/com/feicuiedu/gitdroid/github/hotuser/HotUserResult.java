package com.feicuiedu.gitdroid.github.hotuser;

import com.feicuiedu.gitdroid.github.login.modle.User;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class HotUserResult {

    @SerializedName("total_count")
    private int totalCount;

    @SerializedName("incomplete_results")
    private boolean incompleteResults;

    @SerializedName("items")
    private List<User> userList;

    public int getTotalCount() {
        return totalCount;
    }

    public boolean isIncompleteResults() {
        return incompleteResults;
    }

    public List<User> getRepoList() {
        return userList;
    }
    
//    "total_count": 603,
//            "incomplete_results": false,
//            "items": [
}
