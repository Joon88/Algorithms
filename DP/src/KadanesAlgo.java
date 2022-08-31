import java.util.*;
/*
Used to find the maximum sum contiguous subarray in O(n) time and O(1) space
Recursive equations :---

maxSubstringSumEndingHere(n) = max {
                                      maxSubstringSumEndingHere(n-1) + arr[n],
                                      arr[n]
                                   }

maxSubstringSumSoFar = max {
                              maxSubstringSumSoFar,
                              maxSubstringSumEndingHere(n)
                           }

Example implementation of Kadane's Algo : LC-918(https://leetcode.com/problems/maximum-sum-circular-subarray/)
*/

// O(n) time & O(1) space
class KadanesAlgo {
    public static int kadanesAlgorithm(int[] array) {
        // Write your code here.

        int maxSoFar = array[0];
        for(int i = 1 ; i < array.length ; i++) {
            array[i] = Math.max(array[i-1] + array[i], array[i]);
            maxSoFar = Math.max(maxSoFar, array[i]);
        }
        return maxSoFar;
    }
}

/*
// Another approach
maxEndingHere = 0;
maxSoFar = Integer.MIN_VALUE;

for(all array elts){
        maxEndingHere = maxEndingHere + arr[i];
        if(arr[i] > maxEndingHere){
            maxEndingHere = arr[i];
        }
        if(maxSoFar < maxEndingHere){
            maxSoFar = maxEndingHere;
        }
}
*/
