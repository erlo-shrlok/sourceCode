package com.jinzunyue.sys1.Math;

import com.jinzunyue.share.entity.MyCode;
import com.jinzunyue.share.tools.NumberStringEncrypter;
import com.jinzunyue.share.tools.ShuffleCipher;
import com.jinzunyue.share.tools.ShuffleCipher2;
import com.jinzunyue.share.tools.enCodeUtil;
import com.jinzunyue.sys1.Sys1ApplicationTests;
import com.sun.org.apache.bcel.internal.classfile.Code;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Sys1ApplicationTests.class)
public class enCodeUtilTests {

    // 凯撒算法
    @Test
    public void codeAndDeCode(){
        String encrypted = enCodeUtil.encrypt("1234567890123456789012345",3);
        System.out.println("Encrypted: " + encrypted);
        String decrypted = enCodeUtil.decrypt(encrypted, 3);
        System.out.println("Decrypted: " + decrypted);
    }

    // 洗牌算法
    @Test
    public void code(){
        NumberStringEncrypter cipher = new NumberStringEncrypter();
        String original = "1234567890123451111022";

        String shuffled = cipher.encrypt(original);
        System.out.println("Shuffled: " + shuffled);

        String unshuffled = cipher.decrypt(shuffled);
        System.out.println("Unshuffled: " + unshuffled);
    }

    @Test
    public void GenerateCode(){
        MyCode code = new MyCode();
        code.setArea("996064");
        code.setKind("0101");
        code.setDate("190203");
        code.setType("1");
        code.setN(6);
        List<String> list = enCodeUtil.Code(code);
        for (String c:list){
            System.out.println(c);
        }
    }

    @Test
    public void Decode(){
        MyCode code = enCodeUtil.DeCode("648205099061010593101070");
        System.out.println(code);
    }
}
