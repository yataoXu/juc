package com.evan.core.listsource;

/**
 * @Description
 * @ClassName Huiwen
 * @Author Evan
 * @date 2020.04.14 20:06
 */
public class Huiwen {

    public static boolean test(String str) {
        boolean tmp = false;
        StringBuilder sb = new StringBuilder(str);
        String string1 = sb.reverse().toString();
        System.out.println(string1);

        if(str.equals(string1)){
            tmp = true;
        }
        return tmp;
    }

    public static boolean test1(String str1){
        boolean tmp = false;
        if (str1.length() ==1){
            return true;
        }
        char[] chars = str1.toCharArray();

        int i = 0;
        int j = chars.length -1;

        while(i>=j){
            if (chars [i] == chars [j]){
                i++;
                j--;
            }else {
                return false;
            }
        }
        return true;
    }




    public static void main(String[] args) {
        System.out.println(test1("abcba"));
    }
}
