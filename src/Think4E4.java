import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

/*
    Bruce Eckel "Think in Java": Chapter 4 Exercise 4
    Prime numbers search
*/

public class Think4E4 {

    // Run point
    public static void main(String[] args) {

        String userAnswer = "y";
        do {
            // Ask user for search range
            int maxInt;
            int minInt;
            Scanner con = new Scanner(System.in);
            try {
                do {
                    System.out.print("Enter start number of prime search: ");
                    minInt = con.nextInt();
                } while (minInt < 2);
                do {
                    System.out.print("Enter end number of prime search: ");
                    maxInt = con.nextInt();
                } while (maxInt <= minInt );
            } catch (Exception e) {
                System.out.println("Input error!");
                continue;
            }
            System.out.println("Please wait...");

            // List of prime numbers
            ArrayList<Integer> primeList = new ArrayList<>();

            // Check numbers from 2 to maxInt for prime
            Instant startTime = Instant.now();
            for (int dividend = minInt; dividend <= maxInt; dividend++) {
                boolean maybePrime = true;
                // Try to find divisor with reminder = 0
                for (int divisor = 2; divisor < dividend; divisor++) {
                    // if reminder = 0 - number not prime, skip it
                    if ((dividend % divisor) == 0) {
                        maybePrime = false;
                        break;
                    }
                }
                // It's prime after check - add to list
                if (maybePrime)
                    primeList.add(dividend);
            }

            // Calculate time of searching
            Instant endTime = Instant.now();
            Long periodSec = ChronoUnit.SECONDS.between(startTime, endTime);
            Long periodMil = ChronoUnit.MILLIS.between(startTime, endTime) - periodSec * 1000;
            float speed = -1;
            if (ChronoUnit.MILLIS.between(startTime, endTime) != 0)
                speed = primeList.size() / ChronoUnit.MILLIS.between(startTime, endTime);

            // Print results
            for (int prime : primeList) {
                System.out.println(prime);
            }
            System.out.println("Prime numbers found: " + primeList.size() +
                    " out of " + (maxInt - minInt) +
                    " in " + periodSec + " sec. " + periodMil + " ms.");
            if (speed >= 0)
                System.out.println("Speed: " + speed + " primes/ms.");

            // Ask user for another search
            System.out.print("Enter 'y' to start another search: ");
            userAnswer = con.next();
            primeList.clear();
        } while (userAnswer.equals("y"));

    }

}
