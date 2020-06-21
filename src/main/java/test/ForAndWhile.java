package test;

import org.junit.Test;

/**
 * @Description
 * @ClassName ForAndWhile
 * @Author Evan
 * @date 2020.06.11 17:27
 */
public class ForAndWhile {

    @Test
    public void test01() {
        for (int i = 0; i < 10; i++) {
            if (i == 2) {
                break;
            }
            System.out.println(i);
        }
    }

    @Test
    public void test02() {
        int i = 0;
        while (true) {
            System.out.println(i);
            if (i == 2) {
                break;
            }
            i++;
        }
    }

    @Test
    public void test03() {
        int i = 0;
        do {
            System.out.println(i);
            if (i == 2) {
                break;
            }
            i++;
        } while (true);
    }
}
