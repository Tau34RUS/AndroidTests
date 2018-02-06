package JavaParallelTests;

import org.openqa.selenium.Dimension;

@SuppressWarnings({"WeakerAccess", "CanBeFinal"})
public class Variables {
    //TODO add random values
    @SuppressWarnings("RedundantStringConstructorCall")
    static String userlogin = new String();
    static String userpass = "12345678";
    static String petname="Alessia";
    static String breed;
    static String petweight;
    static String petheight;
    static String birthmonth;
    static String birthyear;
    static String petage;
    static Dimension screensize;
    static String devicename;
    static Integer port;

    static {
        petweight="50";
        petheight="43";
        birthmonth="5";
        birthyear="2014";
        petage="3";
        breed="овчарка";
    }
}
