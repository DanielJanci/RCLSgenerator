import java.util.Scanner;

/**
 * A class used for running experiments.
 */
public class TesterRCLS {
    private int attempt;

    public TesterRCLS() {
        this.attempt = 1;
    }

    public void generateRCLS(int n) {
        RCLS rcls = new RCLS(n);
        int remainingDuos = rcls.getNumberOfRemaining();
        RCLS bestLs = new RCLS(n);

        while(attempt<10000000 && !RCLSController.isFinished(rcls)){
            System.out.println("Attempt: " + attempt);

            while(!RCLSController.isFinished(rcls)) {
                boolean checker = RCLSController.addNewElement(rcls);

                if (!checker) {
                    attempt++;

                    if(remainingDuos > rcls.getNumberOfRemaining()){
                        remainingDuos = rcls.getNumberOfRemaining();
                        bestLs = rcls;
                    }

                    rcls = new RCLS(n);
                    break;
                }
            }
        }
        Helpers.printResults(rcls,bestLs,attempt);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Input n:");
        int n = input.nextInt();
        TesterRCLS gen = new TesterRCLS();

        long startTime = System.nanoTime();
        gen.generateRCLS(n);
        long endTime = System.nanoTime();
        double totalTime = (double)(endTime - startTime)/1000000000;

        System.out.println();
        System.out.println("Total runtime: " + totalTime + "s");

    }
}
