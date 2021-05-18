/**
 * 查到驻数组中第K大的数字，算法思想参考快速排序中的基准值可以让数组分区，小于基准值的在左边，大于基准值的在右边
 * i. 当基准值的下标 = k-1时，则说明基准值就是第K大
 * ii.当基准值的下标 > k-1时，则继续从左半部分查找
 * iii.当基准值的下标 < k-1时，则继续从右半部分查找
 *
 */
package algorithm.Sort;

import java.util.Arrays;

public class findNumberKSort {

    public static int findKNumber(int[] array, int left, int right, int k){

        int baseIndex = left;
        int baseValue = array[baseIndex];
        int i = left;
        int j = right;
        while (i < j)
        {
            while (array[j] >= baseValue && j > baseIndex){
                j--;
            }
            if(j > baseIndex){
                array[baseIndex] = array[j];
                baseIndex = j;
            }

            while (array[i] <= baseValue && i < baseIndex){
                i++;
            }
            if (i < baseIndex){
                array[baseIndex] = array[i];
                baseIndex = i;
            }
        }
        array[baseIndex] = baseValue;
        if(baseIndex == k-1){
            return array[baseIndex];
        }
        else if(baseIndex < k-1)
        {
            return findKNumber(array,baseIndex+1, right,k);
        }
        else {
            return findKNumber(array,left,baseIndex-1,k);
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{6,5,7,9,10,3,1,2,4,8,11};
        System.out.println(Arrays.toString(array));
        System.out.println(findKNumber(array, 0, array.length-1, 11));
    }
}
