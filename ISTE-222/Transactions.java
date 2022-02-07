import javax.management.openmbean.ArrayType;
import java.awt.List;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.TransferQueue;

public class Transactions {
    //    Class values
    private int numbersShares;
    private int price;
    private int action;

    //    CONSTRUCTOR FOR CLASS
    Transactions(int numbersShares, int price, int action) {
        this.numbersShares = numbersShares;
        this.price = price;
        this.action = action;
    }

    //    GETTERS
    public int getNumbersShares() {
        return this.numbersShares;
    }

    public int getPrice() {
        return this.price;
    }

    public int getAction() {
        return this.action;
    }

    public int doAction() {
        int shares = this.numbersShares;
        int price = this.price;
        int totalPrice = price * shares;
        return totalPrice;
    }

    public String toString(){
        return "Transaction {Shares: " + this.numbersShares + ", Price: " + this.price + ", Action: " + this.action + "}";
    }

    //    MAIN METHOD
    public static void main(String args[]) {

        //LIST FOR THE TRANSACTION MADE.
        LinkedList listOfActions = new LinkedList<Transactions>();
        Transactions Tx1 = new Transactions(100, 20, 0);
        Transactions Tx2 = new Transactions(20, 25, 0);
        Transactions Tx3 = new Transactions(50, 35, 0);
        Transactions Tx4 = new Transactions(10, 10, 0);
        Transactions Tx5 = new Transactions(150, 30, 1);
        Transactions Tx6 = new Transactions(80, 15, 0);
        Transactions Tx7 = new Transactions(70, 25, 1);
        listOfActions.add(Tx1);
        listOfActions.add(Tx2);
        listOfActions.add(Tx3);
        listOfActions.add(Tx4);
        listOfActions.add(Tx5);
        listOfActions.add(Tx6);
        listOfActions.add(Tx7);




        //THIS IS WHERE THE QUEUE Implementation starts
        Queue stocksBoughtQueue = new LinkedList();
        int joey = 0;
        for(int y = 0; y < listOfActions.size(); y++){
            Transactions transactionInUse = (Transactions) listOfActions.get(y);
            if (transactionInUse.getAction() == 0){
                stocksBoughtQueue.add(transactionInUse);
            }
            else {
                int sellingStockAmountQueue = transactionInUse.getNumbersShares();
                int sellingStockPriceQueue = transactionInUse.getPrice();

                while (sellingStockAmountQueue != 0){
                    if (!stocksBoughtQueue.isEmpty()){
                        Transactions queuePeek = (Transactions) stocksBoughtQueue.peek();
                        int stockSoldsAmount = queuePeek.getNumbersShares();
                        int stockSoldsPrice = queuePeek.getPrice();
                        stocksBoughtQueue.remove();

                        if (stockSoldsAmount <= sellingStockAmountQueue){
                            sellingStockAmountQueue = sellingStockAmountQueue - stockSoldsAmount;
                            int beaFranchesca = sellingStockPriceQueue - stockSoldsPrice;
                            joey = stockSoldsAmount * beaFranchesca + joey;
                        }
                        else {
                            int newAmount = sellingStockAmountQueue - stockSoldsAmount;
                            int Joanna = sellingStockPriceQueue - stockSoldsPrice;
                            int Kevin = stockSoldsAmount + newAmount;
                            sellingStockAmountQueue = 0;
                            joey = Kevin * Joanna + joey;
                            Transactions Emma = new Transactions(Math.abs(newAmount), stockSoldsPrice, 0);
                            stocksBoughtQueue.add(Emma);
                        }
                    }
                }
            }
        }
        //PRINT OUT QUEUE IMPLEMENTATION
        System.out.println("------------------------------------- Queue -------------------------------------\n");
        System.out.println("Total capital gain for transactions: $" + joey + "\n");
        int totalWorth = 0;
        System.out.println("Stock remaining in the queue:");
        while (!stocksBoughtQueue.isEmpty()){
            Transactions currentQueueItem = (Transactions) stocksBoughtQueue.peek();
            System.out.println(currentQueueItem.getNumbersShares() + " shares at $" +currentQueueItem.getPrice());
            totalWorth = currentQueueItem.getNumbersShares() * currentQueueItem.getPrice() + totalWorth;
            stocksBoughtQueue.remove();
        }
        System.out.println("Total worth of remaining stock: $" + totalWorth + "\n");






        //THIS IS WHERE THE STACK METHOD WORKS.
        Stack stocksBought = new Stack();
        int Quynh = 0;
        for (int i = 0; i < listOfActions.size(); i++){
            Transactions currentTransaction = (Transactions) listOfActions.get(i);
            //If the current transaction is buying stocks.
            if (currentTransaction.getAction() == 0) {
                stocksBought.push(currentTransaction);
            }
            //If the current transaction is selling stocks.
            else {
                //TODO Code goes here.
                int sellingStockAmount = currentTransaction.getNumbersShares();
                int sellingStockPrice = currentTransaction.getPrice();

                while(sellingStockAmount != 0 ){
                    if (!stocksBought.empty()){
                        Transactions stackPop = (Transactions) stocksBought.pop();
                        int stockAmounts = stackPop.getNumbersShares();
                        int stockPrices = stackPop.getPrice();
                        if (stockAmounts <= sellingStockAmount) {
                            sellingStockAmount = sellingStockAmount - stockAmounts;
                            int KhaThy = sellingStockPrice - stockPrices;
                            Quynh = stockAmounts * KhaThy + Quynh;
                        }
                        else {
                            int leftStocks = sellingStockAmount - stockAmounts;
                            int value = sellingStockPrice - stockPrices;
                            int multiplyValue = stockAmounts + leftStocks;
                            sellingStockAmount = 0;
                            Quynh = multiplyValue * value + Quynh;

                            Transactions newInsert = new Transactions(Math.abs(leftStocks), stockPrices, 0);
                            stocksBought.push(newInsert);
                        }
                    }
                }
            }
        }


//      PRINT OUT OF THE STACK IMPLEMENTATION
        System.out.println("------------------------------------- STACK -------------------------------------\n");
        int valueRemainingStocks = 0;
        System.out.println("Total capital gain for transaction: $" + Quynh + "\n");
        System.out.println("Stock remaining in the stack:");
        while (!stocksBought.empty()){
            Transactions currentItem = (Transactions) stocksBought.pop();
            System.out.println(currentItem.getNumbersShares() + " shares at $" + currentItem.getPrice());
            valueRemainingStocks = currentItem.getNumbersShares() * currentItem.getPrice() + valueRemainingStocks;
        }
        System.out.println("Total worth of remaining stock: $" + valueRemainingStocks);
    }
}
