package algorithms.search;

import java.lang.reflect.Array;

/**
 * @Description
 * @ClassName BinarySearch
 * @Author Evan
 * @date 2020.04.14 11:03
 */
public class BinarySearch {

    public static int binarySearch(int[] array, int key) {

//        int[] ints = blub_sort(array);
        int start = 0;
        int tail = array.length - 1;

        while (start <= tail) {
            int mid = (tail - start) / 2 + start;
            if (key == array[mid]) {
                return mid;
            }
            // key 比 mind 大。说明key在mind的右边
            if (key > array[mid]) {
                start = mid + 1;

            }
            // key 比 mind 小。说明key在mind的左边
            if (key < array[mid]) {
                tail = mid - 1;
            }

        }
        return -1;
    }

    public static int[] blub_sort(int[] array) {
        if (array.length > 1) {
            return array;
        }
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < array.length - i; j++) {
                if (array[j] > array[j + 1]) {
                    array[j] = array[j] ^ array[j + 1];
                    array[j + 1] = array[j] ^ array[j + 1];
                    array[j] = array[j] ^ array[j + 1];
                }
            }
        }
        return array;
    }

    public static void main(String[] args) {

        int[] array = new int[]{8,11,19,23,27,33,45,55,67,98};
        int i = binarySearch(array, 33);
        System.out.println(i);


    }
}
