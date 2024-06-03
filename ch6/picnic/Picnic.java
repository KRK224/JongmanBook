package ch6.picnic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Picnic {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt();

        while (testCase != 0) {

            int person = sc.nextInt();
            int pair = sc.nextInt();
            List<Set<Integer>> pairSet = new ArrayList<>();
            int idx = 0;
            while (idx < pair) {
                Set<Integer> tmpSet = new HashSet();
                int f = sc.nextInt();
                tmpSet.add(f);
                int l = sc.nextInt();
                tmpSet.add(l);
                pairSet.add(tmpSet);
                idx++;
            }
            System.out.println(pairSet);
            solution(pairSet);
            testCase--;
        }
    }

    public static List<List<Set<Integer>>> solution(List<Set<Integer>> setList) {
        List<List<Set<Integer>>> answer = new ArrayList<>();

        for (Set<Integer> tempSet : setList) {
            // person -= 2;
            List<Set<Integer>> interList = new ArrayList<>();
            // shallow copy
            for (Set<Integer> ts : setList) {
                interList.add(ts);
            }
            interList = interList.stream().filter(s -> {
                Iterator it = tempSet.iterator();
                if (s.contains(it.next())) {
                    return false;
                } else if (s.contains(it.next()))
                    return false;
                else
                    return true;
            }).collect(Collectors.toList());
            System.out.println("current pick" + tempSet);
            System.out.println("interList" + interList);
            System.out.println("setList" + setList);

            if (interList.size() > 1) {
                List<List<Set<Integer>>> sol = solution(interList);
                for (List<Set<Integer>> solList : sol) {
                    solList.add(tempSet);
                    answer.add(solList);
                }
            } else {
                if (interList.size() == 1) {
                    List<Set<Integer>> solList = new ArrayList<>();
                    solList.add(tempSet);
                    solList.add(interList.get(0));
                    answer.add(solList);
                }
            }
            System.out.println("answer" + " " + answer);
        }

        return answer;
    }
}
