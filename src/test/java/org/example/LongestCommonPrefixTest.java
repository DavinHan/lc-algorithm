package org.example;

import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

/**
 * �������� ������ ���� arr1 �� arr2 �� 
 *  �������� ǰ׺ ���� ����� ��һλ���λ������ɵ����������磬123 ������ 12345 ��ǰ׺���� 234 ���� ��
 *  �������� c ������ a �� b �� ����ǰ׺ ����ô c ��Ҫͬʱ�� a �� b ��ǰ׺�����磬5655359 �� 56554 �й���ǰ׺ 565 ����
 *  1223 �� 43456 û�� ����ǰ׺�� 
 *  ����Ҫ�ҳ����� arr1 ������ x ������ arr2 ������ y ��ɵ��������� (x, y) ֮����Ĺ���ǰ׺�ĳ��ȡ�
 *  ������������֮�������ǰ׺�ĳ��ȡ��������֮�䲻���ڹ���ǰ׺���򷵻� 0 ��
 *  ʾ�� 1��
 * ���룺arr1 = [1,10,100], arr2 = [1000]
 * �����3
 * ���ͣ����� 3 ������ (arr1[i], arr2[j]) ��
 * - (1, 1000) �������ǰ׺�� 1 ��
 * - (10, 1000) �������ǰ׺�� 10 ��
 * - (100, 1000) �������ǰ׺�� 100 ��
 * ��Ĺ���ǰ׺�� 100 ������Ϊ 3 ��
 *  ʾ�� 2��
 * ���룺arr1 = [1,2,3], arr2 = [4,4,4]
 * �����0
 * ���ͣ��κ����� (arr1[i], arr2[j]) ֮�ж������ڹ���ǰ׺����˷��� 0 ��
 * ��ע�⣬ͬһ��������Ԫ��֮��Ĺ���ǰ׺���ڿ��Ƿ�Χ�ڡ�
 *  ��ʾ��
 *  1 <= arr1.length, arr2.length <= 5 * 10?
 *  1 <= arr1[i], arr2[i] <= 10? 
 */
public class LongestCommonPrefixTest {

    /**
     * 	ִ�к�ʱ:188 ms,������12.29% ��Java�û�
     * 	�ڴ�����:54.1 MB,������86.07% ��Java�û�
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
     * 	ִ�к�ʱ:81 ms,������54.10% ��Java�û�
     * 	�ڴ�����:55.1 MB,������42.62% ��Java�û�
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
