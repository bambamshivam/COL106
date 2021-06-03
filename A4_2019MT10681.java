import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class A4_2019MT10681{

    public static void main(String args[])throws IOException{
        A4_2019MT10681 obj=new A4_2019MT10681();
        HashMap<String, Integer> nodes0=obj.getnodes(args[0],args[1],args[2]);
        HashMap<String, ArrayList<String>> nodes=new HashMap<String, ArrayList<String>>();
    	HashMap<String, Integer> label=new HashMap<String, Integer>();
    	obj.nodes_label(args[0],args[1],nodes,label);
        if(args[2].equals("average"))
            obj.average(args[0],args[1],nodes0);
        else if(args[2].equals("rank"))
            obj.rank(args[0],args[1],nodes0);
        else if(args[2].equals("independent_storylines_dfs"))
            obj.independent_storylines_dfs(args[0],args[1],nodes,label);
    }

    void average(String n, String e, HashMap<String, Integer> nodes)throws IOException{
        A4_2019MT10681 obj=new A4_2019MT10681();
        float s=0;
        for(Map.Entry<String, Integer> m:nodes.entrySet())
            s+=m.getValue();
        String a=String.format("%.2f",s/nodes.size());
    	System.out.println(a);
    }

    void rank(String n, String e, HashMap<String, Integer> nodes)throws IOException{
    	A4_2019MT10681 obj=new A4_2019MT10681();
        Chars ar[]=new Chars[nodes.size()];
        int p=0;
        for(Map.Entry<String, Integer> m:nodes.entrySet()){
        	ar[p]=new Chars(m.getKey(),m.getValue());
            p++;
        }
        obj.mergeS(ar, 0, ar.length-1);
        for(int i=ar.length-1;i>0;i--)
            System.out.print(ar[i].str+",");
        System.out.print(ar[0].str);
    }

    void independent_storylines_dfs(String n, String e, HashMap<String, ArrayList<String>> nodes, HashMap<String, Integer> label)throws IOException{
        A4_2019MT10681 obj=new A4_2019MT10681();
        HashMap<String, ArrayList<String>> ccs=new HashMap<String, ArrayList<String>>();
        for(Map.Entry<String, Integer> m:label.entrySet()){
            if(m.getValue()==0){
                ArrayList<String> arr=new ArrayList<String>();
                arr.add(m.getKey());
                ccs.put(m.getKey(),arr);
                obj.DFS(m.getKey(), nodes, label, ccs, arr);
                ccs.put(m.getKey(),arr);
            }
        }
        Chars tot[]=new Chars[ccs.size()];
        int t=0;
        for(Map.Entry<String, ArrayList<String>> m:ccs.entrySet()){
            String a[]=new String[m.getValue().size()];
            for(int i=0;i<m.getValue().size();i++)
                a[i]=m.getValue().get(i);
            Chars c=new Chars(m.getValue().size(), a);
            tot[t]=c;
            t++;
        }
        for(int i=0;i<tot.length;i++)
            obj.mergeS2(tot[i].ar, 0, tot[i].ar.length-1);
        obj.mergeS3(tot, 0, tot.length-1);
        
        for(int i=tot.length-1;i>=0;i--){
            for(int j=tot[i].ar.length-1;j>=0;j--){
            	if(j!=0)
                	System.out.print(tot[i].ar[j]+",");
                else
                	System.out.print(tot[i].ar[j]);
            }
            System.out.println();
        }
    }

    void DFS(String s, HashMap<String, ArrayList<String>> nodes, HashMap<String, Integer> label, HashMap<String, ArrayList<String>> ccs, ArrayList<String> arr){
        A4_2019MT10681 obj=new A4_2019MT10681();
        label.put(s, 1);
        ArrayList<String> ar=nodes.get(s);
        for(int i=0;i<ar.size();i++){
            if(label.get(ar.get(i))==0){
                arr.add(ar.get(i));
                obj.DFS(ar.get(i), nodes, label, ccs, arr);
            }
        }
    }

    HashMap<String, Integer> getnodes(String n, String e, String func)throws IOException{
    	A4_2019MT10681 obj=new A4_2019MT10681();
        HashMap<String, Integer> nodes=new HashMap<String, Integer>();
        BufferedReader br1 = new BufferedReader(new FileReader(n));
        String line1 = br1.readLine();
        while((line1=br1.readLine())!=null){
            String b[] = line1.split(",");
            String[] bT=new String[2];
            bT=obj.divide(b,bT);
            nodes.put(bT[1],0);
        }
        br1.close();
        BufferedReader br2 = new BufferedReader(new FileReader(e));
        String line2 = br2.readLine();
        while((line2=br2.readLine())!=null){
            String b[] = line2.split(",");
            String[] bT=new String[3];
            bT=obj.divide(b,bT);
            int val1=nodes.get(bT[0]);
            int val2=nodes.get(bT[1]);
            int num=0;
            if(func.equals("average"))
            	num=1;
            else
            	num=Integer.parseInt(bT[2]);
            nodes.replace(bT[0],val1+num);
            nodes.replace(bT[1],val2+num);
        }
        br2.close();
        return nodes;
    }

    String[] divide(String b[], String bT[]){
    	if(b.length>bT.length){
            int k1=0;
            int k2=0;
            int p=0;
            while(k2<b.length){
                if(b[k2].charAt(b[k2].length()-1)=='\"'){
                    bT[k1]=bT[k1]+","+b[k2].substring(0,b[k2].length()-1);
                    k2++;
                    k1++;
                    p=0;
                }
                else if(b[k2].charAt(0)=='\"'){
                    bT[k1]=b[k2].substring(1);
                    k2++;
                    p=1;
                }
                else if(p==0){
                    bT[k1]=b[k2];
                    k1++;
                    k2++;
                }
                else{
                    bT[k1]=bT[k1]+","+bT[k2];
                    k2++;
                }
            }
        }
        else
            bT=b;
        return bT;
    }

    void nodes_label(String n, String e, HashMap<String, ArrayList<String>> nodes, HashMap<String, Integer> label)throws IOException{
    	A4_2019MT10681 obj=new A4_2019MT10681();
        BufferedReader br1 = new BufferedReader(new FileReader(n));
        String line1 = br1.readLine();
        while((line1=br1.readLine())!=null){
            String b[] = line1.split(",");
            String[] bT=new String[2];
            bT=obj.divide(b,bT);
            nodes.put(bT[1], new ArrayList<String>());
            label.put(bT[1],0);
        }
        br1.close();
        BufferedReader br2 = new BufferedReader(new FileReader(e));
        String line2 = br2.readLine();
        while((line2=br2.readLine())!=null){
            String b[] = line2.split(",");
            String[] bT=new String[3];
            bT=obj.divide(b,bT);
            ArrayList<String> arl1=nodes.get(bT[0]);
            ArrayList<String> arl2=nodes.get(bT[1]);
            arl1.add(bT[1]);
            arl2.add(bT[0]);
            nodes.replace(bT[0],arl1);
            nodes.replace(bT[1],arl2);
        }
        br2.close();
    }

    void mergeS3(Chars ar[], int l, int r){
        if (l<r){
            int m=(l+r)/2;
            mergeS3(ar, l, m);
            mergeS3(ar, m + 1, r);
            merge3(ar, l, m, r);
        }
    }

    void merge3(Chars ar[], int l, int m, int r)
    {
        int n1=m-l+1;
        int n2=r-m;
        Chars L[]=new Chars[n1];
        Chars R[]=new Chars[n2];
        for(int i=0;i<n1;i++)
            L[i]=ar[l+i];
        for (int i=0;i<n2;i++)
            R[i]=ar[m+1+i];
        int i=0, j=0;
        int k=l;
        while(i<n1 && j<n2){
            if(L[i].occur==R[j].occur){
                if(L[i].ar[0].compareTo(R[j].ar[0])<0){
                    ar[k]=L[i];
                    i++;
                }
                else{
                    ar[k]=R[j];
                    j++;
                }
            }
            else if(L[i].occur<R[j].occur){
                ar[k]=L[i];
                i++;
            }
            else{
                ar[k]=R[j];
                j++;
            }
            k++;
        }
        while(i<n1){
            ar[k]=L[i];
            i++;
            k++;
        }
        while(j<n2){
            ar[k]=R[j];
            j++;
            k++;
        }
    }

    void mergeS2(String ar[], int l, int r){
        if (l<r){
            int m=(l+r)/2;
            mergeS2(ar, l, m);
            mergeS2(ar, m + 1, r);
            merge2(ar, l, m, r);
        }
    }

    void merge2(String ar[], int l, int m, int r)
    {
        int n1=m-l+1;
        int n2=r-m;
        String L[]=new String[n1];
        String R[]=new String[n2];
        for(int i=0;i<n1;i++)
            L[i]=ar[l+i];
        for (int i=0;i<n2;i++)
            R[i]=ar[m+1+i];
        int i=0, j=0;
        int k=l;
        while(i<n1 && j<n2){
            if(L[i].compareTo(R[j])<0){
                ar[k]=L[i];
                i++;
            }
            else{
                ar[k]=R[j];
                j++;
            }
            k++;
        }
        while(i<n1){
            ar[k]=L[i];
            i++;
            k++;
        }
        while(j<n2){
            ar[k]=R[j];
            j++;
            k++;
        }
    }

    void mergeS(Chars ar[], int l, int r){
        if (l<r){
            int m=(l+r)/2;
            mergeS(ar, l, m);
            mergeS(ar, m + 1, r);
            merge(ar, l, m, r);
        }
    }

    void merge(Chars ar[], int l, int m, int r)
    {
        int n1=m-l+1;
        int n2=r-m;
        Chars L[]=new Chars[n1];
        Chars R[]=new Chars[n2];
        for(int i=0;i<n1;i++)
            L[i]=ar[l+i];
        for (int i=0;i<n2;i++)
            R[i]=ar[m+1+i];
        int i=0, j=0;
        int k=l;
        while(i<n1 && j<n2){
            if(L[i].occur==R[j].occur){
                if(L[i].str.compareTo(R[j].str)<0){
                    ar[k]=L[i];
                    i++;
                }
                else{
                    ar[k]=R[j];
                    j++;
                }
            }
            else if(L[i].occur<R[j].occur){
                ar[k]=L[i];
                i++;
            }
            else{
                ar[k]=R[j];
                j++;
            }
            k++;
        }
        while(i<n1){
            ar[k]=L[i];
            i++;
            k++;
        }
        while(j<n2){
            ar[k]=R[j];
            j++;
            k++;
        }
    }

    class Chars{
        String str;
        String ar[];
        int occur;
        Chars(String str, int occur){
            this.str=str;
            this.occur=occur;
        }

        Chars(int occur, String ar[]){
            this.occur=occur;
            this.ar=ar;
        }
    }
}