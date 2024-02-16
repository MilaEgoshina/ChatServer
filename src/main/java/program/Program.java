package program;

import java.util.HashMap;

public class Program {

    private Integer value;

    public Program(Integer value){
        this.value = value;
    }

    public static void main(String[] args) {

        HashMap<Program,Integer> hashMap = new HashMap<>();
        for(int i = 0; i < 100_000_000; i++){

            Program p = new Program(2);
            System.out.println(p.hashCode());
            hashMap.put(p,i);
        }

        System.out.println(hashMap.size());
    }
}
