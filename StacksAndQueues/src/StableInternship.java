/*
Algoexpert : https://www.algoexpert.io/questions/stable-internships

Very good logic on matching 2 entitySet using the preferences set my members of each set
 */

import java.util.*;

public class StableInternship {
    // O(n^2) time and space
    public int[][] stableInternships(int[][] interns, int[][] teams) {
        int n = interns.length;
        // matches (team : intern)
        Map<Integer, Integer> matched = new HashMap<>();

        // free agent interns
        Queue<Integer> internsQueue = new ArrayDeque<>();
        for(int i = 0; i < n; i++)
            internsQueue.add(i);

        // interns current choices
        int[] internsChoice = new int[n];

        // teams choice map
        List<Map<Integer, Integer>> teamsChoice = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            Map<Integer, Integer> teamPrefs = new HashMap<>();
            for(int j = 0; j < n; j++) {
                teamPrefs.put(teams[i][j], j);
            }
            teamsChoice.add(teamPrefs);
        }

        while(!internsQueue.isEmpty()) {
            int intern = internsQueue.poll();
            int internPref = interns[intern][internsChoice[intern]];
            internsChoice[intern]++;

            if(matched.containsKey(internPref)) {
                int alreadyMatchedIntern = matched.get(internPref);

                // check which intern does internPref team prefers more
                if(teamsChoice.get(internPref).get(intern) < teamsChoice.get(internPref).get(alreadyMatchedIntern)) {
                    // old matching need to go, and new matching needs to be estd.
                    matched.remove(internPref);
                    internsQueue.add(alreadyMatchedIntern);
                } else {
                    internsQueue.add(intern); // intern could not be matched in this choice
                    continue;
                }
            }

            matched.put(internPref, intern);
        }

        int[][] result = new int[n][2];

        int ctr = 0;
        for(Map.Entry<Integer, Integer> entry : matched.entrySet()) {
            result[ctr][0] = entry.getValue();
            result[ctr][1] = entry.getKey();
            ctr++;
        }


        return result;
    }
}
