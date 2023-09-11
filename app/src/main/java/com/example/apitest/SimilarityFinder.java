package com.example.apitest;

import java.util.ArrayList;

public class SimilarityFinder {
    public static int levenshteinDistance(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    int cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + cost);
                }
            }
        }

        return dp[m][n];
    }

    public static String findSimilarStyle(String targetStyle, ArrayList<String> beerStyles) {
        String closestMatch = null;
        int minDistance = Integer.MAX_VALUE;

        for (String style : beerStyles) {
            int distance = levenshteinDistance(targetStyle, style);
            if (distance < minDistance) {
                minDistance = distance;
                closestMatch = style;
            }
        }

        return closestMatch;
    }
}
