package com.feicuiedu.gitdroid.github.repoinfo;

/**
 * 获取readme响应结果
 *
 */
public class RepoContentResult {

    private String content;
    private String encoding;

    public String getContent() {
        return content;
    }

    public String getEncoding() {
        return encoding;
    }
    //    {
//        "encoding": "base64",
//            "content": "encoded content ..."
//    }
}
