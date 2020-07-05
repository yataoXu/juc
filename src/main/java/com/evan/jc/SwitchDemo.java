package com.evan.jc;

/**
 * @Description
 * @ClassName SwitchDemoo
 * @Author Evan
 * @date 2020.06.30 16:39
 */
public class SwitchDemo {
    public static void main(String[] args) {
        String  s = "a";

        switch (s) {
            case "a": //a分支
                System.out.println("匹配成功1");

            case "b": //b分支
                System.out.println("匹配成功2");

            case "c": //c分支
                System.out.println("匹配成功3");
                break;
            case "d": //d分支
                System.out.println("匹配成功4");
                break;
            default:
                break;
        }
    }
}
