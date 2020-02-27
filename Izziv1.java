
import java.util.concurrent.ThreadLocalRandom;

public class Izziv1 {

    public static int[] generateTable(int n) {
        int[] list = new int[n];
        for (int i = 1; i <= n; i++) {
            list[i - 1] = i;
        }
        return list;
    }

    public static int LinearSearch(int[] list, int n) {
        for(int i=0;i<list.length;i++) {
            if (list[i] == n) {
                return i;
            }
        }
        return -1;
    }

    public static int BinarySearch(int[] list,int left, int right, int n) {
        while (left<=right) {
            int mid = (left+right)/2;
            if (n == list[mid]) {
                return mid;
            }   else if (n < list[mid]) {
				right = mid-1;
			} else if (n > list[mid]) {
				left = mid + 1;
			}
		}
		return -1;
    }

    public static long timeLinear(int n){
        int[] table = generateTable(n);
        long time = 0;  
        for(int i=0; i<1000; i++){
            int rnd = ThreadLocalRandom.current().nextInt(0, table.length+1);
            long start = System.nanoTime();
            int item = LinearSearch(table, rnd);
            time += System.nanoTime() - start;
        }
        return time/1000;
    }

    public static long timeBinary(int n){
        int[] table = generateTable(n);
        long time = 0;  
        for(int i=0; i<1000; i++){
            int rnd = ThreadLocalRandom.current().nextInt(0, table.length+1);
            long start = System.nanoTime();
            int item = BinarySearch(table, 0, table.length-1, rnd);
            time += System.nanoTime() - start;
        }
        return time/1000;
    }

    
    public static void main(String[] args) {
        System.out.println(" n       |     linearno  |   dvojisko  |\n---------+--------------+------------------");
        int[] ctr = new int[91];
        long[] linear = new long[91];
        long[] binary = new long[91];
        int c = 0;
        for(int i=100000; i<=1000000; i+=10000){
            ctr[c] = i;
            linear[c] = timeLinear(i);
            binary[c] = timeBinary(i);
            System.out.println(String.format("%-9s|%15s|%13s" , i, linear[c], binary[c]));
            c++;
        }
       
    }
}

/*
e)
* Zakaj so na časi pri vas drugačni kot v zgornji tabeli?
    Drugačen hardware, procesi, ...
* Kateri algoritem je hitrejši?
    V primeru, da vemo da je število v seznamu, dvojiško. 
* Kdaj bi lahko bil počasnejši algoritem hitrejši?
    Kadar se iskano število nahaja na optimalnem mestu, v tem primeru, če iščemo 1. 
* Kako se čas odvisen od velikosti naloge (linearno, kvadratno, ...)?
    ???
* Je časovna odvisnost dvojiškega iskanja bližje linearni ali konstantni?
    konstantni*/