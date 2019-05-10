package com.bawei.demo2;

import java.util.List;

public class Bean {

    private List<Result> result;

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public static class Result{
        private String name;
        private String summary;

        public Result(String name, String summary) {
            this.name = name;
            this.summary = summary;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }
    }

}
