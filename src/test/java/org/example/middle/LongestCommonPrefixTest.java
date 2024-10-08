package org.example.middle;

import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

/**
 *  给你两个 正整数 数组 arr1 和 arr2
 *  正整数的 前缀 是其 最左边 的一位或多位数字组成的整数。例如，123 是整数 12345 的前缀，而 234 不是
 *  设若整数 c 是整数 a 和 b 的 公共前缀 ，那么 c 需要同时是 a 和 b 的前缀。例如，5655359 和 56554 有公共前缀 565 ，而
 *  1223 和 43456 没有 公共前缀
 *  你需要找出属于 arr1 的整数 x 和属于 arr2 的整数 y 组成的所有数对 (x, y) 之中最长的公共前缀的长
 *  返回所有数对之中最长公共前缀的长度。如果它们之间不存在公共前缀，则返回 0
 *  示例 1：
 *  输入：arr1 = [1,10,100], arr2 = [1000]
 *  输出：3
 *  解释：存在 3 个数对 (arr1[i], arr2[j])
 * - (1, 1000) 的最长公共前缀是 1
 * - (10, 1000) 的最长公共前缀是 10
 * - (100, 1000) 的最长公共前缀是 100
 * 最长的公共前缀是 100 ，长度为 3
 *  示例 2：
 * 输入：arr1 = [1,2,3], arr2 = [4,4,4]
 * 输出：0
 * 解释：任何数对 (arr1[i], arr2[j]) 之中都不存在公共前缀，因此返回 0
 * 请注意，同一个数组内元素之间的公共前缀不在考虑范围内。
 *  提示：
 *  1 <= arr1.length, arr2.length <= 5 * 10
 *  1 <= arr1[i], arr2[i] <= 10
 */
public class LongestCommonPrefixTest {

    /**
     * 	执行耗时:188 ms,击败了12.29% 的Java用户
     * 	内存消耗:54.1 MB,击败了86.07% 的Java用户
     */
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        int maxLen = 0;
        Set<String> s = new HashSet<>();
        for (int i : arr1) {
            String sub = String.valueOf(i);
            for (int j = 1; j <= sub.length(); j++) {
                s.add(sub.substring(0, j));
            }
        }
        for (int i : arr2) {
            String s2 = String.valueOf(i);
            for (int j = 1; j <= s2.length(); j++) {
                String subS2 = s2.substring(0, j);
                if (!s.contains(subS2)) {
                    continue;
                }
                if (subS2.length() > maxLen) {
                    maxLen = subS2.length();
                }
            }
        }
        return maxLen;
    }

    /**
     * 	执行耗时:81 ms,击败了54.10% 的Java用户
     * 	内存消耗:55.1 MB,击败了42.62% 的Java用户
     */
    public int longestCommonPrefix2(int[] arr1, int[] arr2) {
        int max = 0;
        Set<Integer> s = new HashSet<>();
        for (int x : arr1) {
            for (;x > 0;x = x / 10) {
                s.add(x);
            }
        }
        for (int x : arr2) {
            for (;x > 0 && !s.contains(x);x = x / 10);
            max = Math.max(max, x);
        }
        return max == 0 ? 0 : String.valueOf(max).length();
    }

    @Test
    public void fun() {
        System.out.println(Charset.defaultCharset().displayName());

        LongestCommonPrefixTest a = new LongestCommonPrefixTest();
        System.out.println("target is 3, result is " + a.longestCommonPrefix(new int[]{1, 10, 100}, new int[]{1000}));
        System.out.println("target is 0, result is " + a.longestCommonPrefix(new int[]{1, 2, 3}, new int[]{4, 4, 4}));
        System.out.println("target is 1, result is " + a.longestCommonPrefix(new int[]{1, 2}, new int[]{12}));
        System.out.println("target is 1, result is " + a.longestCommonPrefix(new int[]{1}, new int[]{17}));
        System.out.println("target is 3, result is " + a.longestCommonPrefix2(new int[]{1, 10, 100}, new int[]{1000}));
        System.out.println("target is 0, result is " + a.longestCommonPrefix2(new int[]{1, 2, 3}, new int[]{4, 4, 4}));
        System.out.println("target is 1, result is " + a.longestCommonPrefix2(new int[]{1, 2}, new int[]{12}));
        System.out.println("target is 1, result is " + a.longestCommonPrefix2(new int[]{1}, new int[]{17}));
    }
}
