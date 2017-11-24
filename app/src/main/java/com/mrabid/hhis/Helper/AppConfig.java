package com.mrabid.hhis.Helper;

/**
 * Created by Mr.Abid on 6/2/2017.
 */

public class AppConfig {
    //Server getRiwyatPasien
    public static String DATA_URL="http://192.168.43.161/android_login_api/getdata.php?no_ktp=";

    //Server Artikel
    public static String ARTIKEL="http://hhis.tk/api/web/index.php/v1/artikel/index";

    public static String PASIEN="http://hhis.tk/api/web/index.php/v1/pasien/view?id_user=";

    public static String LOGIN = "http://hhis.tk/api/web/index.php/v1/auth/login";

    public static String REGISTER = "http://hhis.tk/api/web/index.php/v1/auth/signup";

    public static String KOTA = "http://hhis.tk/api_native/kabupaten/read.php";

    public static String RIWAYAT = "http://hhis.tk/api/web/index.php/v1/riwayat/view?id_user=";


}
