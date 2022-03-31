package algorithm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Algo {
    public static void main(String[] args) {
        PriorityQueue<A> pq = new PriorityQueue<>((o1, o2) ->  o1.getS().compareTo(o2.getS()));

        // 빼기는
        pq.add(new A(1,"z"));
        pq.add(new A(5,"a"));
        pq.add(new A(3,"y"));
        pq.add(new A(7,"b"));

        System.out.println(pq.toString());

        A a =  pq.poll();
        System.out.println(String.format("%s %s", a.getI(), a.getS()));

        a =  pq.poll();
        System.out.println(String.format("%s %s", a.getI(), a.getS()));

        a =  pq.poll();
        System.out.println(String.format("%s %s", a.getI(), a.getS()));

        a =  pq.poll();
        System.out.println(String.format("%s %s", a.getI(), a.getS()));




//        Solution s = new Solution();
//        s.solution(new String[] {"a","b","c", "a"}, new String[] {"a","b","c"});
    }
}

@Setter
@Getter
@AllArgsConstructor
class A {
    int i;
    String s;
}

class Solution {
    public String solution(String[] participant, String[] completion) {
        HashMap<String, Integer> list = new HashMap<>();
        for (String p : participant) {
            if (list.keySet().contains(p)) {
                list.put(p, list.get(p)+1);
            } else {
                list.put(p, 1);
            }
        }

        for (String c : completion) {
            list.put(c, list.get(c) - 1);
        }

        for (Map.Entry<String, Integer> l : list.entrySet()) {
            if (l.getValue() != 0) {
                return l.getKey();
            }
        }

        return "not found";
    }
}
