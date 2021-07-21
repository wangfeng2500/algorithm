package algorithm.Array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum
{
    /*
     * 给定一个无序数组，和一个目标值，在数组中查找两数之和==target的索引值
     * 解法：巧妙的使用map，遍历数组，然后找target-当前值是否在map中出现
     */
    public static int[] twoSum(int[] array, int target)
    {
        Map<Integer ,Integer> indexMap = new HashMap();
        for(int i = 0; i < array.length; ++i) {
            int value = array[i];
            if (indexMap.containsKey(target - value)) {
                return new int[]{indexMap.get(target-value), i};
            } else {
                indexMap.put(value, i);
            }
        }
        return new int[]{-1,-1};
    }

    /*
     * 给定一个升序的数组，和一个目标值，在数组中查找
     * 解法：因为是升序的数组，所以可以类似二分法来进行，一旦遇到有序的数组，第一时间想到双指针二分法
     * 参考：https://mp.weixin.qq.com/s/Nh6jxQtO-xOT_WuX-B5w3Q
     * FIXME::没想到这种做法
     */
    public static int[] twoSum2(int[] array, int target)
    {
        int left = 0;
        int right = array.length-1;
        while (left < right)
        {
            if(array[left] + array[right] == target)
            {
                return new int[]{left,right};
            }
            else if(array[left] + array[right] < target)
            {
                left++; // 让 sum 大一点
            }
            else
            {
                right--; // 让 sum 小一点
            }
        }
        return new int[]{-1,-1};
    }


    public static void main(String[] args) {
        int[] array = new int[]{1,2,3,7,9};
        int[] index = twoSum2(array,9);
        System.out.println(Arrays.toString(index));
    }
}
