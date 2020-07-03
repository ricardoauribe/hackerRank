import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the activityNotifications function below.
    // https://www.hackerrank.com/challenges/fraudulent-activity-notifications/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=sorting
    static int activityNotifications(int[] expenditure, int d) {

        double median = 0;
        double limit = 0;
        int len = expenditure.length;
        int wSize = 201;
        int[] window = new int[wSize];
        int counter = 0;

        //Populate window with data from the fist d locations
        for(int j=0; j<d; j++){
            window[expenditure[j]]++;
        }

        //Traverse the array increasing using d to determine start and end positions
        for(int i=d; i<len; i++){
            median = getMedian(window, d);
            System.out.println(median);
            limit = median * 2;
            if(expenditure[i]>=limit){
                counter++;
            }

            window[expenditure[i]]++;
            window[expenditure[i-d]]--;
        }

        return counter;
    }

    static double getMedian(int[] incidences, int wSize){

        int iLen = incidences.length;
        double median = 0;
        int counter = 0;

        //If we need to consider 2 locations
        if(wSize % 2 ==0){
            int p1 = -1;
            int p2 = -1;
            for(int i=0; i<iLen; i++){
                counter += incidences[i];
                if(p1 == -1 && counter >= wSize/2){
                    p1 = i;
                }
                if(p2 == -1 && counter >= (wSize/2)+1){
                    p2 = i;
                    break;
                }
            }
            double result = p1 + p2;
            median = result/2.0;
            
        }
        //If we have a middle location
        else{
            int p = -1;
            for(int j=0; j<iLen; j++){
                counter += incidences[j];
                if(counter > wSize/2){
                    p = j;
                    break;
                }
            }
            median = p;
        }

        return median;
    }


    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nd = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nd[0]);

        int d = Integer.parseInt(nd[1]);

        int[] expenditure = new int[n];

        String[] expenditureItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int expenditureItem = Integer.parseInt(expenditureItems[i]);
            expenditure[i] = expenditureItem;
        }

        int result = activityNotifications(expenditure, d);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
