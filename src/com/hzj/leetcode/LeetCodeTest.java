package com.hzj.leetcode;

import org.junit.Test;

import java.util.*;

/**
 * @author hzj
 * @create 2022-02-06 16:21
 */
public class LeetCodeTest {
    /**
     * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
     * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
     * @param s
     */
    public void reverseString(char[] s) {
        StringBuilder str = new StringBuilder(s.length);
        for(char c:s){
            str.append(c);
        }
        str.reverse();
        System.out.println(str);
    }

    /**
     * 给定一个字符串 s ，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        String[] strings = s.split(" ");
        String answer = new String();
        for(String str:strings){
            StringBuilder stringBuilder = new StringBuilder(str);

            answer += stringBuilder.reverse().toString() + " ";
        }
        answer = answer.trim();
        return answer;
    }

    public boolean isHappy(int n) {
        if(1 < n && n < 7){
            return false;
        }
        n = sqaure(n);
        while(true){
            if(n == 1){
                return true;
            }else if(1 < n && n < 7) {
                return false;
            }else{
                    n = sqaure(n);
            }
        }


    }
    public boolean isHappy1(int n) {
            if(n == 1){
                return true;
            }else if(1 < n && n < 7) {
                return false;
            }
            int answer = 0;
            while(n != 0){
                answer += (n%10)*(n%10);
                n /= 10;
            }
            return isHappy1(answer);

    }
    public int sqaure(int n){
        int answer = 0;
        while(n != 0){
            answer += (n%10)*(n%10);
            n /= 10;
        }
        return answer;
    }








    public int majorityElement(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<>();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            int count = counts.getOrDefault(nums[i], 0) + 1;
            //如果某个数字出现的个数已经超过数组的一半，自己返回
            if (count > length / 2)
                return nums[i];
            counts.put(nums[i], count);
        }
        return -1;
    }



    public int lengthOfLongestSubstring(String s) {
        int length = s.length();
        int anwser = 0;
        if(length == 0 && length == 1){
            return length;
        }
        for(int begin = 0; begin < length; begin++){
            int end = 0;
            HashSet set = new HashSet(length);
            for (; end <length - begin ; end++) {
                if(set.contains(s.charAt(begin + end))){
                    break;
                }
                set.add(s.charAt(begin + end));
            }
            if(anwser < end){
                anwser = end;
            }
        }
        return anwser;
    }

    public int lengthOfLongestSubstring1(String s) {
        int length = s.length();
        HashSet<Character> set = new HashSet<>(length);
        int anwser = 0;
        if(length == 0 && length == 1){
            return length;
        }
        int end = 0;
        for(int begin = 0; begin < length; begin++){

            if(begin > 0 ){
                set.remove(s.charAt(begin  - 1));
            }

            for (; end <length - begin ; end++) {
                if(set.contains(s.charAt(begin + end))){
                    break;
                }
                set.add(s.charAt(begin + end));
            }
            if(anwser < end){
                anwser = end;
            }
            end--;

        }
        return anwser;

    }
    public boolean checkInclusion(String s1, String s2) {
        int length1 = s1.length();
        int length2 = s2.length();
        if(length2 < length1){
            return false;
        }
        int[] arr = new int[26];
        for(int i = 0; i < length1; i++){
            --arr[s1.charAt(i) - 'a'];

        }
        int left = 0;
        for(int right = 0; right < length2; right++){
            int x = s2.charAt(right) - 'a';
            ++arr[x];
            while(arr[x] > 0){
                --arr[s2.charAt(left) - 'a'];
                left++;
            }
            if((right - left + 1) == length1){
                return true;
            }
        }
        return false;
    }
    public int[] changearr (int[] arr){
        arr[0] = -1;
        return arr;
    }

    int[] dx = {1, 0, 0, -1};
    int[] dy = {0, 1, -1, 0};
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int currColor = image[sr][sc];
        if (currColor == newColor) {
            return image;
        }

        int n = image.length, m = image[0].length;
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(new int[]{sr, sc});
        image[sr][sc] = newColor;
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0], y = cell[1];
            for (int i = 0; i < 4; i++) {
                int mx = x + dx[i], my = y + dy[i];
                if (mx >= 0 && mx < n && my >= 0 && my < m && image[mx][my] == currColor) {
                    queue.offer(new int[]{mx, my});
                    image[mx][my] = newColor;
                }
            }
        }
        return image;
    }
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        List  ans = new ArrayList();
        if(n < 3){
            return ans;
        }
        Arrays.sort(nums);
        for(int i = 0; i < n - 2; i++){
            if(nums[i] > 0){
                break;
            }
            if(i > 0 && nums[i] == nums[i - 1]){
                continue;
            }
            for(int j = i + 1, k = n-1 ; j < k;){
                if( j > i + 1 && nums[j] == nums[j - 1]){
                    j++;
                    continue;
                }
                if(-nums[i] == nums[j] + nums[k]){
                    List<Integer> temp = new ArrayList<>(3);
                    temp.add(nums[i]);
                    temp.add(nums[j]);
                    temp.add(nums[k]);
                    j++;
                    k--;
                    if(!ans.contains(temp)){
                        ans.add(temp);
                    }

                }else if(-nums[i] < nums[j] + nums[k]){
                    k--;
                }else{
                    j++;
                }
            }

        }
        return ans;
    }
    public boolean isPerfectSquare(int num) {
        int left = 0, right = num - 1;
        if(num == 1){
            return true;
        }
        while(left < right){
            int mid = left + ((right - left) >> 1);
            if(num ==(long) mid * mid){
                return true;
            }else if( num > (long)mid * mid){
                left = mid + 1;
            }else{
                right = mid;
            }
        }
        return false;
    }
    public static String remove(String s){
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '#'){
                sb.deleteCharAt(sb.length() - 1);
            }else{
                sb.append(s.charAt(i));
            }

        }
        return sb.toString();
    }
    public boolean backspaceCompare(String s, String t) {
        int index_s = s.length() - 1, index_t = t.length() - 1;
        int skipS = 0, skipT = 0;
        while(index_s >=0 || index_t >= 0){
            while(index_s >= 0){
                if(s.charAt(index_s) == '#'){
                    skipS++;
                    index_s--;
                }else if(skipS > 0){
                    skipS--;
                    index_s--;
                }else{
                    break;
                }
            }
            while(index_t >= 0){
                if(s.charAt(index_t) == '#'){
                    skipT++;
                    index_t--;
                }else if(skipT > 0){
                    skipT--;
                    index_t--;
                }else{
                    break;
                }
            }
            if(index_s >= 0 && index_t >= 0){
                if(s.charAt(index_s) != t.charAt(index_t)){
                    return false;
                }
            }else {
                if(index_s >= 0 || index_t >= 0){
                    return false;
                }
            }
            index_s--;
            index_t--;

        }
        return true;
    }
    public void getNext(String s, int[] next){
        int j = 0;
        next[0] = 0;
        for(int i = 1; i < s.length();i++){
            while(j > 0 && s.charAt(i) != s.charAt(j)){
                j = next[j - 1];
            }
            if(s.charAt(i) == s.charAt(j)){
                j++;
            }
            next[i] = j;

        }
    }
    @Test
    public void test(){
        String lx = "issip";
        int[] next = new int[lx.length()];
        getNext(lx, next);
        for (int i : next) {
            System.out.println(i);
        }
        System.out.println("122222");
        System.out.println("hotfix");
        System.out.println("hotfix");
        System.out.println("maste test");
        System.out.println("hotfix-test");
        System.out.println("push-test");
//        }

    }

}