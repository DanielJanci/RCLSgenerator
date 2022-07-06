import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A class that contains methods that add elements into the RCLS.
 */
public class RCLSController {
    /**
     * If element is in the row or the column, method returns false. Returns true otherwise
     * @param rcls Instance of class RCLS
     * @param row Index of a row
     * @param element Element to be added
     * @return boolean
     */
    public static boolean isLatinSqr(RCLS rcls, int row, int element){
       if(rcls.getRow(row).contains(element) || rcls.getColumn(rcls.getRow(row).size()).contains(element)) {
            return false;
        }

       return true;
    }

    /**
     * Adds last element into a row. If adding was successful, method returns true. Returns false otherwise.
     * @param rcls Instance of class RCLS
     * @param row Index of a row
     * @return boolean
     */
    public static boolean addLastRowElement(RCLS rcls, int row){
        List<Integer> remainingElement = new ArrayList<>(rcls.getAllElements());
        remainingElement.removeAll(rcls.getRow(row));

        int lastElement = getLastElement(rcls,row);
        int newElement = remainingElement.get(0);

        if(isLatinSqr(rcls,row,newElement) && containsDomino(rcls,lastElement,newElement)) {
            rcls.addElement(row, newElement);
            rcls.deleteFullRow(row);
            rcls.removeDomino(lastElement, newElement);
            return true;
        }
        return false;
    }

    /**
     * Adds last element into a column. If adding was successful, method returns true. Returns false otherwise.
     * @param rcls Instance of class RCLS
     * @param indexOfCol Index of column
     * @return boolean
     */
    public static boolean addLastColumnElement(RCLS rcls, int indexOfCol){
        List<Integer> remainingElement = new ArrayList<>(rcls.getAllElements());
        remainingElement.removeAll(rcls.getColumn(indexOfCol));

        int row = rcls.getShortestRow();
        int newElement = remainingElement.get(0);
        int lastElement = getLastElement(rcls,row);

       if(isLatinSqr(rcls,row,newElement) && containsDomino(rcls, lastElement, newElement)) {
            rcls.addElement(row,newElement);
            rcls.removeDomino(lastElement,newElement);
            return true;
        }

        return false;
    }

    /**
     * Returns a random row in RCLS that is missing elements.
     * @param rcls Instance of class RCLS
     * @return Row as a list of integers
     */
    public static int selectRow(RCLS rcls){
        Random rand = new Random();
        int tmp = rcls.getIncompleteRows().size();
        int index = rand.nextInt(tmp);
        return rcls.getIncompleteRows().get(index);
    }

    /**
     * Returns last element in a row.
     * @param rcls Instance of class RCLS
     * @param row index of a row
     * @return Element as a integer
     */
    public static int getLastElement(RCLS rcls, int row){
        int len = rcls.getRow(row).size()-1;
        return rcls.getRow(row).get(len);
    }

    /**
     * Return true if RCLS contains domino (left, right). Returns false otherwise.
     * @param rcls Instance of class RCLS
     * @param left Left side fo a domino
     * @param right Rigth side of a domino
     * @return boolean
     */
    public static boolean containsDomino(RCLS rcls, int left, int right){
        return rcls.getDominoes().get(left).contains(right);
    }

    /**
     * Returns random element from temporary available elements in RCLS. If there are no available elements, returns -1.
     * @param rcls Instance of class RCLS
     * @return Element as a integer or -1
     */
    public static int getRightOne(RCLS rcls){
        if(rcls.getTmpAvailableElements().isEmpty()){
            return -1;
        }
        Random rand = new Random();
        int tmp = rcls.getTmpAvailableElements().size();
        int index = rand.nextInt(tmp);
        return rcls.getTmpAvailableElements().get(index);
    }

    /**
     * Adds new element to RCLS while there are available elements. Returns true if adding was successful. Returns false
     * otherwise.
     * @param rcls Instance of class RCLS
     * @return boolean
     */
    public static boolean addNewElement(RCLS rcls){
        int row = RCLSController.selectRow(rcls);
        int lastElement = getLastElement(rcls, row);


        rcls.setTmpAvailableElements(rcls.getDominoes().get(lastElement));

        int newElement = getRightOne(rcls);

        while(newElement!=-1){
            if(isLatinSqr(rcls,row,newElement)){

                rcls.addElement(row,newElement);
                rcls.removeDomino(lastElement,newElement);

                // try adding last element to a row
                if(rcls.getRow(row).size()== rcls.getN()-1){
                    return addLastRowElement(rcls,row);
                }

                // try adding last element to a column
                int indexOfCol = rcls.getIndexOfColumn(newElement,row);
                if(rcls.getColumnLength(indexOfCol)== rcls.getN()-1){
                    return addLastColumnElement(rcls,indexOfCol);
                }

                return true;
            }
            // if it's not possible to add currently selected element, select a new one
            rcls.removeTmpElement(newElement);
            newElement = getRightOne(rcls);
        }
        return false;
    }

    /**
     * Returns true if all rows in RLCS are full, i.e. have size of N. Returns false otherwise.
     * @param rcls Instance of class RCLS
     * @return boolean
     */
    public static boolean isFinished(RCLS rcls){
        for(int i = 0; i< rcls.getN();i++){
            if(rcls.getRow(i).size()!=rcls.getN()){
                return false;
            }
        }
        return true;
    }

}
