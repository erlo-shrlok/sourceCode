package com.jinzunyue.sys1.Math;

import com.jinzunyue.share.tools.*;
import com.jinzunyue.sys1.Sys1ApplicationTests;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Sys1ApplicationTests.class)
public class OriginCode2Tests {
        @Test
        public void getCode() throws Exception {
            OriginCode2.getCode();
        }

        @Test
        public void getCode2() throws Exception {
            OriginCode3.makeCode();
        }


        @Test
        public void yasuo(){
            String str = DataUtils.encode("010100010001022120112000122102002003020120");
            System.out.println(str);
        }

        @Test
        public void origincode5(){
            String s = "测试果园测试厂测试渠道";// 测试数据
            OriginCode5.GenerateOriginCode(s);
        }

        @Test
        public void decode(){
            String str = ZoneBitCode.deCode("18664252259152161866425201971866425239942132");
            System.out.println(str);
        }

    @Test
    public void code(){
        String str = OriginCode5.convertChineseToNumber("某个果园梁山厂诸葛渠道");
        String str2 = OriginCode5.convertNumberToChinese(str);
        System.out.println("转换后："+str+"\n解码后："+str2);
    }
    // 十进制与二进制的转换
    @Test
    public void code2(){
        String str = OriginCode5.convertDecimalToBinary("526576520010526524522253526753523665521378535832533883528192536947");
        String str2 = OriginCode5.convertBinaryToDecimal(str);
        System.out.println("原数据：526576520010526524522253526753523665521378535832533883528192536947"+"\n转换后："+str+"\n解码后："+str2);
    }


    }
