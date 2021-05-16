package com;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

public class Test1 {

    public void newTest(){
        HashMap<String, Integer> map1 = new HashMap<String, Integer>();
        TreeMap<Integer, String> map2 = new TreeMap<Integer, String>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -o1.compareTo(o2);
            }
        });
        String str = "输入单词文件";
        int len = str.length();
        int firstIndex=  0;
        for(int i = 0; i < len; i++){
            if (str[i] == ' ') {
                String tempString = str.substring(firstIndex, i);
                if(map1.containsKey(tempString)) {
                    int temp = map1.get(tempString);
                    map1.put(tempString, 1 + temp);
                } else {
                    map1.put(tempString, 1);
                }
                firstIndex = i + 1;
            }
        }
        Iterator iterator = map1.keySet().iterator();
        while(iterator.hasNext()){
            Object obj = iterator.next();
            String key = (String) Obj;
            Integer value = map1.get(key);
            map2.put(value, key);
        }

        Iterator iterator1 = map2.keySet().iterator();
        int index = 1;
        while(iterator1.hasNext()){
            Object obj = iterator1.next();
            Integer key = (Integer) obj;
            System.out.println("排名第" + index + "的单词为" +map2.get(key) );
            index++;
        }

    }

}
