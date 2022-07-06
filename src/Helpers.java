public class Helpers {
    public static void printDuos(RCLS rcls){
        for(int i = 0; i< rcls.getN(); i++){
            for(int j = 0; j< rcls.getDominoes().get(i).size(); j++){
                System.out.print("(" + i + "," + rcls.getDominoes().get(i).get(j) + ") ");
            }
            System.out.println();
        }
    }

    public static void printLatinSqr(RCLS rcls){
        for(int i = 0; i< rcls.getN(); i++){
            for(int j = 0; j< rcls.getRow(i).size(); j++){
                System.out.print(rcls.getRow(i).get(j) + "  ");

            }
            for(int k = 0; k < rcls.getN()-rcls.getRow(i).size(); k++){
                System.out.print("-  ");
            }
            System.out.println();
        }
    }

    public static void printResults(RCLS rcls, RCLS bestLs, int attempt){
        if(!RCLSController.isFinished(rcls)){
            System.out.println();
            System.out.println("Attempt: " + attempt);
            System.out.println("Best result of Row-Complete Latin square:");
            Helpers.printLatinSqr(bestLs);
            System.out.println("Remaining duos:");
            Helpers.printDuos(bestLs);
        }
        else{
            System.out.println();
            System.out.println("Attempt: " + attempt);
            System.out.println("Row-Complete Latin square:");
            Helpers.printLatinSqr(rcls);
        }
    }

    public static void printForExample(RCLS rcls, int attempt){
        System.out.println();
        //System.out.println("Attempt: " + attempt);
        System.out.println("Štvorec:");
        Helpers.printLatinSqr(rcls);
        System.out.println("Voľné dominá:");
        Helpers.printDuos(rcls);
    }

}
