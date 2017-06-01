package main;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import db.LinkDao;
import http.HttpRequest;
import po.Link;

/**
 * Created by yuwc on 2017/6/1.
 */

public class Duba {

    private String newest = "http://news.duba.com/json2/xianguo_96439_1.txt";
    private String tech = "http://news.duba.com/json2/xianguo_18754_1.txt";
    private String international = "http://news.duba.com/json2/xianguo_18754_1.txt";
    private String entertainment = "http://news.duba.com/json2/xianguo_1_1.txt";
    private String military = "http://news.duba.com/json2/xianguo_1546_1.txt";
    private String finance = "http://news.duba.com/json2/xianguo_7_1.txt";
    private String sport = "http://news.duba.com/json2/xianguo_186_1.txt";

    public String getNewest() {
        return newest;
    }

    public void setNewest(String newest) {
        this.newest = newest;
    }

    public String getTech() {
        return tech;
    }

    public void setTech(String tech) {
        this.tech = tech;
    }

    public String getInternational() {
        return international;
    }

    public void setInternational(String international) {
        this.international = international;
    }

    public String getEntertainment() {
        return entertainment;
    }

    public void setEntertainment(String entertainment) {
        this.entertainment = entertainment;
    }

    public String getMilitary() {
        return military;
    }

    public void setMilitary(String military) {
        this.military = military;
    }

    public String getFinance() {
        return finance;
    }

    public void setFinance(String finance) {
        this.finance = finance;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public static void main(String[] args) {
        Duba duba = new Duba();
        duba.getOneWithPagination(duba.newest);
        duba.getOneWithPagination(duba.tech);
        duba.getOneWithPagination(duba.international);
        duba.getOneWithPagination(duba.entertainment);
        duba.getOneWithPagination(duba.military);
        duba.getOneWithPagination(duba.finance);
        duba.getOneWithPagination(duba.sport);
    }

    public void getOneWithPagination(String url) {
//        File file = FileUtils.getFile("file/link.txt");
        LinkDao linkDao = new LinkDao();
        int ind = url.lastIndexOf("_");
        int count = 0;
        for (int i = 1; count < 3; i++) {
//            System.out.println("正在抓取第" + i + "页");

            String nextPageUrl = url.substring(0, ind) + "_" + i + url.substring(ind + 2);

            String result = HttpRequest.sendGet(nextPageUrl, "");

            if ("".equals(result)) {
                count++;
                continue;
            }
            JSONArray jsonArray = JSONArray.parseArray(result);
            for (int j = 0; j < jsonArray.size(); j++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(j);
                if (jsonObject.getString("referer").contains("凤凰") && !jsonObject.getString("link").contains("ifeng.com")) {
//                        FileUtils.writeStringToFile(file, jsonObject.getString("link") + "\r\n", true);
                    if (linkDao.findIdByField(String.class, "url", jsonObject.getString("link")) == 0L) {//新的url
                        System.out.println("--------------------------------发现新的符合条件的url:" + jsonObject.getString("link") + ",准备入库！");
                        Link link = new Link();
                        link.setUrl(jsonObject.getString("link"));
                        link.setTitle(jsonObject.getString("title"));
                        linkDao.add(link);
                    }
                }
            }
        }
    }
}
