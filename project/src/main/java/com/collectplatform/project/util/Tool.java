package com.collectplatform.project.util;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DecimalFormat;

/**
 * description:
 * Created by YGP on 2020-12-29
 */
public class Tool {
    public String getNetFileSizeDescription(long size) {
        StringBuffer bytes = new StringBuffer();
        DecimalFormat format = new DecimalFormat("###.0");
        if (size >= 1024 * 1024 * 1024) {
            double i = (size / (1024.0 * 1024.0 * 1024.0));
            bytes.append(format.format(i)).append("GB");
        }
        else if (size >= 1024 * 1024) {
            double i = (size / (1024.0 * 1024.0));
            bytes.append(format.format(i)).append("MB");
        }
        else if (size >= 1024) {
            double i = (size / (1024.0));
            bytes.append(format.format(i)).append("KB");
        }
        else if (size < 1024) {
            if (size <= 0) {
                bytes.append("0B");
            }
            else {
                bytes.append((int) size).append("B");
            }
        }
        return bytes.toString();
    }

    public String getAddress(){
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        assert address != null;
        return "http://"+ address.getHostAddress();

    }

//    public static void main(String args []){
//        HashMap<String, String > titleMap  = new LinkedHashMap<String, String>(){{
//            put("index","序号");
//            put("id","id");
//            put("createTime","创建时间");
//            put("updatetime","更新时间");
//            put("firstIndicator","一级指标");
//            put("secondIndicator","二级指标");
//            put("indicator","指标项");
//            put("indicatorRank","分值");
//            put("minusRuler","扣分类型（扣分类型：0-只扣一次，1-出现一次扣除一级指标全部分数，2-可以重复扣除，直到二级指标分数全部扣除）");
//            put("status","状态（0 不启用，1 启用）");
//        }};
//        AtomicInteger col = new AtomicInteger();
//        titleMap.forEach((key, value) -> {
//
//            System.out.println( col.getAndIncrement());
//        });
//    }
}
