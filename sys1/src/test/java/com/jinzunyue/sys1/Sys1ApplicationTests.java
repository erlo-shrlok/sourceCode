package com.jinzunyue.sys1;

import com.jinzunyue.share.tools.OriginCode;
import com.jinzunyue.share.tools.OriginCode4;
import com.jinzunyue.share.tools.OriginDecode;
import com.jinzunyue.share.tools.ZoneBitCode;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

@SpringBootTest(classes = Sys1ApplicationTests.class)
public
class Sys1ApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("hello");
    }

    @Test
    public void addLocation() {
        String url = "jdbc:mysql://localhost:3306/mulsys2";
        String user = "root"; // replace with your username
        String password = "root"; // replace with your password

        String locations =
                "北京市\t110000000000\n" +
                        "天津市\t120000000000\n" +
                        "河北省\t130000000000\n" +
                        "山西省\t140000000000\n" +
                        "内蒙古自治区\t150000000000\n" +
                        "辽宁省\t210000000000\n" +
                        "吉林省\t220000000000\n" +
                        "黑龙江省\t230000000000\n" +
                        "上海市\t310000000000\n" +
                        "江苏省\t320000000000\n" +
                        "浙江省\t330000000000\n" +
                        "安徽省\t340000000000\n" +
                        "福建省\t350000000000\n" +
                        "江西省\t360000000000\n" +
                        "山东省\t370000000000\n" +
                        "河南省\t410000000000\n" +
                        "湖北省\t420000000000\n" +
                        "湖南省\t430000000000\n" +
                        "广东省\t440000000000\n" +
                        "广西壮族自治区\t450000000000\n" +
                        "海南省\t460000000000\n" +
                        "重庆市\t500000000000\n" +
                        "四川省\t510000000000\n" +
                        "贵州省\t520000000000\n" +
                        "云南省\t530000000000\n" +
                        "西藏自治区\t540000000000\n" +
                        "陕西省\t610000000000\n" +
                        "甘肃省\t620000000000\n" +
                        "青海省\t630000000000\n" +
                        "宁夏回族自治区\t640000000000\n" +
                        "新疆维吾尔自治区\t650000000000";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            String sql = "INSERT INTO location (location_name, location_code) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            String[] locationArray = locations.split("\n");
            for (String location : locationArray) {
                String[] parts = location.split("\t");
                pstmt.setString(1, parts[0]);
                pstmt.setString(2, parts[1]);
                pstmt.executeUpdate();
            }

            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void quweima() throws UnsupportedEncodingException {
        String s = "锦绣大地";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            try {
                byte[] bytes = ("" + c).getBytes("GB2312");
                String quweiCode = "" + (bytes[0] & 0xff - 160) + (bytes[1] & 0xff - 160);
                System.out.println(c + "的区位码是：" + quweiCode);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void quweima2(){
        String s = ZoneBitCode.getCode("锦绣大地");
        System.out.println(s);
    }

    // 加密方法
    @Test
    public void chanpinma(){
        String str = OriginCode.GenerateOriginCode("110101","锦绣大地");
        System.out.println(str);
    }

    // 解密方法
    @Test
    public void decode(){
        String str = OriginDecode.DecryptOriginCode("0367892608");
        System.out.println(str);
    }

    @Test
    public void lenth(){
        String str = "1866748425918416186674841971866748471942164";
        System.out.println("Length of the string is: " + str.length());
    }

    @Test
    public void toSijinzhi(){
        String str = OriginCode.convertStringToQuaternary("1101012985806920832188");
        System.out.println(str);
    }

    @Test
    public void fenduan(){
        List<String> s = OriginCode.divideIntoSections("010100010002022120112000122102002003020120");
        for (String str:s){
            System.out.println(str);
        }
    }

    @Test
    public void huff(){
        OriginCode4.huff();
    }
}
