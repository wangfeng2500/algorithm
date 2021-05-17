/*
 *  参考https://time.geekbang.org/column/article/270342写的快速排序
 *  Desc：不同于网上全部将index=0当作基准值，这里可以随意选取，结合文章看，会相当清晰
 */

package algorithm.Sort;

import java.util.Arrays;

public class quickSort {
    public static void sort(int[] array, int begin, int end){
        if(begin >= end)
            return;

        int i = begin;
        int j = end;
        int baseIndex = end;//(begin+end)/2;  //基准值索引
        int baseValue = array[baseIndex];

        while (i < j)
        {
            while (j > baseIndex && array[j] >= baseValue)
            {
                j--;
            }
            if (j > baseIndex){
                array[baseIndex] = array[j];
                baseIndex = j;
            }

            while(i < baseIndex && array[i] <= baseValue)
            {
                i++;
            }
            if(i < baseIndex){
                array[baseIndex] = array[i];
                baseIndex = i;
            }
        }
        array[baseIndex] = baseValue;

        sort(array, begin, baseIndex-1);
        sort(array, baseIndex+1, end);

    }

    public static void main(String[] args) {
        // int array[] = new int[]{8,3,10,2,7,6,9,12};
         int array[] = new int[]{8,3,10,2,7,6,9,12,11};
        // int array[] = new int[]{1,2,3,4,5,6,7,8,9,10,11};
        // int array[] = new int[]{1,2,3,4,5,6,7,8,9,10};
        // int array[] = new int[]{10,9,8,7,6,5,4,3,2,1};
        //int array[] = new int[]{11,10,9,8,7,6,5,4,3,2,1};
        System.out.println("排序前：" + Arrays.toString(array));
        sort(array,0, array.length-1);
        System.out.println("排序后：" + Arrays.toString(array));
    }
}
