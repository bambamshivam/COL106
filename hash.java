import java.io.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;

public class A4_2019CS50437 
    {


        public static void main(String args[])throws Exception{
       A4_2019CS50437 object =new A4_2019CS50437();
            if(args[2].equals("average")){
           A4_2019CS50437.average(args[0],args[1]);
            }
        }

public static void average(String nodescsv,String edgescsv) throws Exception {

    BufferedReader br = new BufferedReader(new FileReader(nodescsv));
    String line =  br.readLine();
    HashMap<String,Integer> map =new HashMap<String, Integer>();

    while((line=br.readLine())!=null){
        String str[] = line.split(",");
        if(str[0].charAt(0)=='\"'){

            map.put(str[0].substring(1,str[0].length())+","+str[1].substring(0,str[1].length()-1), 0);
        }
        else{
            map.put(str[0],0);
        }
        
    }

    BufferedReader br2 = new BufferedReader(new FileReader(edgescsv));
    String line2 =  br2.readLine();

    while((line2=br2.readLine())!=null){
        String str[] = line2.split(",");
        String str1="";
        String str2="";
        if(str[0].charAt(0)=='\"'){
            str1=str[0].substring(1,str[0].length())+","+str[1].substring(0,str[1].length()-1);
            if(str[2].charAt(0)=='\"'){
                str2=str[2].substring(1,str[2].length())+","+str[3].substring(0,str[3].length()-1);
            }
            else{
                str2=str[2];
            }
        }
        else{
            str1=str[0];
            if(str[1].charAt(0)=='\"'){
                str2=str[1].substring(1,str[1].length())+","+str[2].substring(0,str[2].length()-1);
            }
            else{
                str2=str[1];
            }
        } 
        map.put(str1,map.get(str1)+1);
        map.put(str2,map.get(str2)+1);
        
    }
    float sumtotal=0;
        for(Map.Entry<String, Integer> nodes:map.entrySet()){
            sumtotal=sumtotal+nodes.getValue();
        }
        String average=String.format("%.2f",sumtotal/map.size());
        System.out.println(average);




    }
}