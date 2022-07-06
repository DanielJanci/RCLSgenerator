import java.util.ArrayList;
import java.util.List;

/**
 * A class representing Row Complete Latin Square
 */
public class RCLS {
    private List<List<Integer>> rcls;
    private final int n;
    private List<Integer> incompleteRows;
    private List<Integer> allElements;
    private List<Integer> tmpAvailableElements;
    private List<List<Integer>> dominoes;

    public RCLS(int n) {
        // RCLS constructor. Sets RCLS to a default state. Has a parameter n that is an order of RCLS.
        setRCLS(n);
        setAllElements(n);
        setIncompleteRows(n);
        this.n = n;
        setDominoes(n);
    }

    public void setDominoes(int n){
        // Generates all possible dominoes in RLCS.
        this.dominoes = new ArrayList<>();
        for(int i=0;i<n;i++){
            List<Integer> tmpList = new ArrayList<>();
            for(int j=0;j<n;j++){
                if(i!=j)
                    tmpList.add(j);
            }
            dominoes.add(tmpList);
        }
    }

    public void setRCLS(int n){
        // Creates empty Latin Square and fills the first column with values from 0 to n-1.
        this.rcls = new ArrayList<>();
        for(int i=0;i<n;i++){
            List<Integer> tmpList = new ArrayList<>();
            tmpList.add(i);
            rcls.add(tmpList);
        }
    }

    public void setIncompleteRows(int n){
        // Creates an array of indexes of rows, which are not completed. Contains all indexes by default.
        this.incompleteRows = new ArrayList<>();
        for(int i=0;i<n;i++){
            this.incompleteRows.add(i);
        }
    }

    public void setAllElements(int n){
        // Creates an array of all elements in RLCS (values from 0 to n-1).
        this.allElements = new ArrayList<>();
        for(int i=0;i<n;i++){
            this.allElements.add(i);
        }
    }

    public void setTmpAvailableElements(List<Integer> elements){
        // Creates a temporary array of elements that can be added during one cycle.
        this.tmpAvailableElements = new ArrayList<>(elements);
    }

    public int getN() {
        return n;
    }

    public List<List<Integer>> getDominoes() {
        return dominoes;
    }

    public List<Integer> getIncompleteRows() {
        return incompleteRows;
    }

    public List<Integer> getAllElements() {
        return allElements;
    }

    public List<Integer> getRow(int i){
        return rcls.get(i);
    }

    public List<Integer> getTmpAvailableElements() {
        return tmpAvailableElements;
    }

    public void removeTmpElement(int element){
        tmpAvailableElements.remove((Integer) element);
    }

    public void addElement(int row, int element){
        rcls.get(row).add(element);

    }

    public void deleteFullRow(int row){
        incompleteRows.remove((Integer) row);
    }

    public List<Integer> getColumn(int indexOfCol){
        List<Integer> col = new ArrayList<>();
        for(int i=0;i<n;i++){
            if(rcls.get(i).size()>=indexOfCol+1)
            col.add(rcls.get(i).get(indexOfCol));
        }

        return  col;
    }

    public int getColumnLength(int indexOfCol){
        int len = 0;
        for(int i=0;i<n;i++){
            if(rcls.get(i).size()>=indexOfCol+1)
                len++;
        }
        return len;
    }

    public int getIndexOfColumn(int element, int row){
        return rcls.get(row).indexOf(element);
    }

    public int getShortestRow(){
        int row = 0;
        int len = n;
        for(int i=0;i<n;i++){
            if(rcls.get(i).size()<len) {
                len = rcls.get(i).size();
                row = i;
            }
        }
        return row;
    }

    public void removeDomino(int left, int element){
        dominoes.get(left).remove((Integer) element);
    }

    public int getNumberOfRemaining(){
        int num = 0;
        for(List<Integer> l : dominoes){
            num = num + l.size();
        }
        return num;
    }

}
