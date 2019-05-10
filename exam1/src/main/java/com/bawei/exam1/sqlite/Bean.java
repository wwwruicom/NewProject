package com.bawei.exam1.sqlite;

import java.util.List;

public class Bean {
    public Data data;

    public Data getData() {
        return data;
    }
    public void setData(Data data) {
        this.data = data;
    }

    public static class Data{
        public List<Forecast> forecast;

        public Data(List<Forecast> forecast) {
            this.forecast = forecast;
        }

        public List<Forecast> getForecast() {
            return forecast;
        }

        public void setForecast(List<Forecast> forecast) {
            this.forecast = forecast;
        }

        public static class Forecast {
            String date;
            String high;
            String fengxiang;
            String low;
            String type;

            public Forecast(String date, String high, String fengxiang, String low, String type) {
                this.date = date;
                this.high = high;
                this.fengxiang = fengxiang;
                this.low = low;
                this.type = type;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getFengxiang() {
                return fengxiang;
            }

            public void setFengxiang(String fengxiang) {
                this.fengxiang = fengxiang;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}
