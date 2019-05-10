package com.bawei.newproject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Weixin implements Serializable {
    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public static class Result{
        List<AllList> list=new ArrayList<>();

        public List<AllList> getList() {
            return list;
        }

        public void setList(List<AllList> list) {
            this.list = list;
        }

        public static class AllList{
            String title;
            String source;

            public AllList(String title, String source) {
                this.title = title;
                this.source = source;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }
        }

    }
}
