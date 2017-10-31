package com.lgc.lgcutillibrary.util.photo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Pattern;

public class StringUtill {

    public static String checkString(String string) {
        boolean isNum = false, isInfo = true;
        String[] temp = string.split("/(.*)\r\n|\n|\r/g");
        StringBuffer resoult = new StringBuffer();
        // Log.v("test", "temp.length:" + temp.length);
        if (temp.length > 1) {
            resoult.append("<div class='section_content'>");
            for (String str : temp) {
                // Log.e("test", "str:" + str);

                String reg1 = "^\\d.*?$"; // ^表示字符串开始，\\d表示数字
                if (Pattern.matches(reg1, str)) {
                    resoult.append("<div class='section_title'><p>" + str + "<p></div>");
                    // Log.e("test", "str:" + str);
                }
                else if (str.startsWith("●")) {
                    resoult.append("<div class='section_step'><p>" + str + "<p></div>");
                    // Log.e("test", "str:" + str);
                }
                else {
                    resoult.append("<div class='section_info'><p>" + str + "<p></div>");
                }
            }
            resoult.append("</div>");
        }
        else {

            resoult.append("<div class='section_info'>");
            resoult.append("<p>");
            resoult.append(string);
            resoult.append("</p>");
            resoult.append("</div>");
            // Log.v("test",resoult.toString());
            // Log.v("test", "str:" + resoult.toString());
        }

        return resoult.toString();

    }

    /**
     * 获取当前时间来命名，以免有重复的文件名,再加上3位的随机数
     *
     * @return
     */
    public static String getTimeName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_hhmmssSSS");
        String timeName = dateFormat.format(date) + "_" + new Random().nextInt(99999);
        return timeName;
    }

    public static String getTimeName(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_hhmmssSSS");
        String timeName = dateFormat.format(date) + "_" + new Random().nextInt(99999);
        return timeName;
    }

    public static String getTimeNameSql() {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#000");
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddhhmmssSSS");
        String timeName = dateFormat.format(date) + df.format(new Random().nextInt(999));
        return timeName;
    }

    public static String getTimeNameSql(Date date) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#000");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddhhmmssSSS");
        String timeName = dateFormat.format(date) + df.format(new Random().nextInt(999));
        return timeName;
    }

    public static String getRecordTime() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String timeName = dateFormat.format(date);
        return timeName;
    }

    /**
     * 将 8/29拿出两位数字
     */
    public static int[] getIntByString(String str, String str2) {
        if (str2.equals(".")) {
            str2 = "\\.";
        }
        String[] bpStrs = str.split(str2);
        int[] bps = new int[bpStrs.length];
        for (int i = 0; i < bpStrs.length; i++) {
            bps[i] = Integer.valueOf(bpStrs[i]);
        }
        return bps;
    }


    // 获取加密后的手机号码
    public static String getEncryptedPhoneNumber(String phoneNumber) {
        if (phoneNumber.length() == 11) {
            String starmobile = String.valueOf(phoneNumber.charAt(0)) + String.valueOf(phoneNumber.charAt(1))
                    + String.valueOf(phoneNumber.charAt(2)) + "****" + String.valueOf(phoneNumber.charAt(7))
                    + String.valueOf(phoneNumber.charAt(8)) + String.valueOf(phoneNumber.charAt(9))
                    + String.valueOf(phoneNumber.charAt(10));
            return starmobile;
        }
        return phoneNumber;
    }

    /**
     * 用于在搜索或插入时替换掉特殊字符
     *
     * @param keyWord
     * @return
     */
    public static String sqliteEncode(String keyWord) {
        keyWord = keyWord.replace("/", "//");
        keyWord = keyWord.replace("'", "''");
        keyWord = keyWord.replace("[", "/[");
        keyWord = keyWord.replace("]", "/]");
        keyWord = keyWord.replace("%", "/%");
        keyWord = keyWord.replace("&", "/&");
        keyWord = keyWord.replace("_", "/_");
        keyWord = keyWord.replace("(", "/(");
        keyWord = keyWord.replace(")", "/)");
        return keyWord;
    }

    public static boolean isSqliteEncode(String keyWord) {
        if (keyWord.contains("//") || keyWord.contains("''") || keyWord.contains("/[")
                || keyWord.contains("/]") || keyWord.contains("/%") || keyWord.contains("/&")
                || keyWord.contains("/_") || keyWord.contains("/(") || keyWord.contains("/"))
            return true;
        else
            return false;
    }

    public static String sqliteDecode(String keyWord){
        keyWord = keyWord.replace("//", "/");
        keyWord = keyWord.replace("''", "'");
        keyWord = keyWord.replace("/[", "[");
        keyWord = keyWord.replace("/]", "]");
        keyWord = keyWord.replace("/%", "%");
        keyWord = keyWord.replace("/&", "&");
        keyWord = keyWord.replace("/_", "_");
        keyWord = keyWord.replace("/(", "(");
        keyWord = keyWord.replace("/)", ")");
        return keyWord;
    }
}
