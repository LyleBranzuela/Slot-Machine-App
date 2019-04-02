package Question_1;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Random;
import java.util.Scanner;

/**
 * A Slot Machine program that simulates a real slot machine.
 * The user specifies his bets and can cash out the token inside the machine.
 * <p>
 * <b><u>CONTROLS:</u></b>
 * <br>
 * <b>P</b> - PULLLEVER<br> 
 * <b>T</b> - TOPUP<br> 
 * <b>N</b> - NEW SLOT MACHINE<br> 
 * <b>H</b> - HOUSE CREDIT<br> 
 * <b>Q</b> - LEAVE<br>
 * 
 * @author lyleb
 */
public class SlotMachine {
    private Random generator;
    private int tokenCredit;
    private static int houseCredit;
    
    /**
     * Constructor to initialize a new Slot Machine.
     */
    public SlotMachine()
    {
        this.tokenCredit = 0;
        this.generator = new Random();
    }
    
    /**
     * Tops up tokens to the current token credit of the user.
     * 
     * @param tokens Indicates how much tokens should be topped up.
     */
    public void topupTokens(int tokens)
    {
        houseCredit += tokens;
        this.tokenCredit += tokens;
    }
    
    /**
     * Cashes out the user's remaining token credit.
     * 
     * @return the current token credit.
     */
    public int cashoutTokens()
    {
        System.out.printf("YOU CASHED OUT: $" + this.tokenCredit + "\n");
        houseCredit -= this.tokenCredit;
        this.tokenCredit = 0;
        return this.tokenCredit;
    }
    
    /**
     * A method that simulates pulling a lever of a slot machine.
     * The method creates an array of 3 random generated numbers.
     * <p>
     * <b>The user wins depending on matching one of the four possible outcomes:</b>
     * <br>
     * <b>{0,0,0} - </b>Super Jackpot (500x Multiplier).<br>
     * <b>{X,X,X} - </b>Jackpot (50x Multiplier).<br>
     * <b>{X,X,Z} - </b>Free Spin.<br>
     * <b>{X,Y,Z} - </b>Bad Luck, Try Again.<br>
     */
    public void pullLever()
    {
        int[] numberArray = new int[3];
        int randomNumber = 0;

        if (this.tokenCredit != 0)
        {
            this.tokenCredit--;
            
            // Generating the random number of 0-10
            for (int counter = 0; counter < 3; counter++)
            {
                randomNumber = this.generator.nextInt(10);
                numberArray[counter] = randomNumber;
                
            }
            
            // Generates the Format of the Slot Machine 
            System.out.println("|===============================================================================================|");
            System.out.println("|***********************************|   " + numberArray[0]+ "   |   " + numberArray[1] + "   |   " + numberArray[2]+ "   |***********************************|");
            System.out.println("|===============================================================================================|");
            
            // Number Array Checker
            if (numberArray[0] == 0 && numberArray[1] == 0 && numberArray[2] == 0)
            {
                this.tokenCredit += 500;
                System.out.println("|************************************Super Jackpot Winner!!!************************************|");
                System.out.printf("Your Winnings: $500");
            }
            else if (numberArray[0] == numberArray[1] && numberArray[1] == numberArray[2])
            {
                this.tokenCredit += 50;
                System.out.println("|=======================================Jackpot Winner!!!=======================================|");
                System.out.printf("Your Winnings: 50");
            }
            else if (numberArray[0] == numberArray[1] || numberArray[0] == numberArray[2] || numberArray[1] == numberArray[2])
            {
                this.tokenCredit++;
                System.out.println("|-------------------------------------------Free Spin-------------------------------------------|");
            }
            else
            {
                System.out.println("|xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx Bad Luck, Try Again !!! xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx|");
            }
        }
        else
        {
            System.out.printf("Insufficient Token Balance! Minimum of 1 Token (Balance: " + this.tokenCredit + ")\n");
        }
        System.out.printf("|===============================================================================================|\n\n");
    }
    
    /**
     * Overloads the default method if a user inputs a specified bet.
     * 
     * @param tokenInput Amount of bet tokens the user specifies.
     * @see pullLever()
     */
    public void pullLever(int tokenInput)
    {
        int[] numberArray = new int[3];
        int randomNumber = 0;
        if (this.tokenCredit - tokenInput >= 0 && tokenInput != 0)
        {   
            this.tokenCredit -= tokenInput;
            
            // Generating the random number of 0-10
            for (int counter = 0; counter < 3; counter++)
            {
                randomNumber = this.generator.nextInt(10);
                numberArray[counter] = randomNumber;
            }
            
            // Generates the Format of the Slot Machine
            System.out.println("|===============================================================================================|");
            System.out.println("|***********************************|   " + numberArray[0]+ "   |   " + numberArray[1]+ "   |   " + numberArray[2]+ "   |***********************************|");
            System.out.println("|===============================================================================================|");
            
            // Number Array Checker
            if (numberArray[0] == 0 && numberArray[1] == 0 && numberArray[2] == 0)
            {
                tokenInput *= 500;
                this.tokenCredit += tokenInput;
                System.out.println("|************************************Super Jackpot Winner!!!************************************|");
                System.out.printf("Your Winnings: " + tokenInput);
            }
            else if (numberArray[0] == numberArray[1] && numberArray[1] == numberArray[2])
            {
                tokenInput *= 50;
                this.tokenCredit += tokenInput;
                System.out.println("|=======================================Jackpot Winner!!!=======================================|");
                System.out.printf("Your Winnings: " + tokenInput);
            }
            else if (numberArray[0] == numberArray[1] || numberArray[0] == numberArray[2] || numberArray[1] == numberArray[2])
            {
                this.tokenCredit += tokenInput;
                System.out.println("|-------------------------------------------Free Spin-------------------------------------------|");
            }
            else
            {
                System.out.println("|xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx Bad Luck, Try Again !!! xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx|");
            }
        }
        else
        {
            System.out.printf("Insufficient Token Balance! Minimum of 1 Token Set! (Set Token: " + tokenInput + " | Balance: " + this.tokenCredit + ")\n");
        }
        System.out.printf("|===============================================================================================|\n\n");
    }
    
    /**
     * Returns an integer of the user's token credit.
     * 
     * @return Returns the amount of token credit
     */
    public int getTokenBalance()
    {
        return this.tokenCredit;
    }
    
    /**
     * Used to get the total house credit, and save it to be retrieved if the user changes slot machines.
     * 
     * @return Returns it back after adding the slot machine's house credit. 
     */
    public int getHouseCredit() 
    {        
        return houseCredit;
    }

    public static void main(String[] args)
    {
        SlotMachine mySlotMachine = new SlotMachine();
        Scanner scan = new Scanner(System.in);
        Scanner numberScanner = new Scanner(System.in);
        
        System.out.println("*************************************************************************************************");
        System.out.println("|                              Welcome to the Slot Machine Game!!!                              |");
        char key = 'Q';
        do
        {
            System.out.println("*************************************************************************************************");
            System.out.println("| P - PULLLEVER | T - TOPUP | N - NEW SLOT MACHINE | H - HOUSE CREDIT | C - CASHOUT | Q - LEAVE |");
            System.out.println("=================================================================================================");
            System.out.printf("CHOOSE AN ACTION (BALANCE: " + mySlotMachine.getTokenBalance() + "): ");
            key = scan.nextLine().charAt(0);
            switch(key)
            {
                case 'P':
                case 'p':
                    System.out.printf("SPIN (1 TOKEN) OR SET BET(S/B)?: ");
                    char betKey = scan.nextLine().charAt(0);
                    switch(betKey)
                    {
                        // S: Pull Lever immediately with 1 Token
                        case 'S':
                        case 's':
                            mySlotMachine.pullLever();
                            break;
                            
                        // B: Specify bet played
                        case 'B':
                        case 'b':
                            System.out.printf("AMOUNT TO BET (BALANCE: " + mySlotMachine.getTokenBalance() + "): ");
                            int setUserBet = numberScanner.nextInt();
                            mySlotMachine.pullLever(setUserBet);
                            break;
                            
                        default:
                            System.out.printf("UNKNOWN ACTION\n\n");
                    }
                break;      
                    
                // T: Topup tokens to the machine
                case 'T':
                case 't':
                    System.out.printf("AMOUNT TO TOPUP: ");
                    int setTopUpAmount = numberScanner.nextInt();
                    mySlotMachine.topupTokens(setTopUpAmount);
                    System.out.printf("\n");
                    break;
                    
                // N: Switch Slot Machines 
                case 'N':
                case 'n':   
                    System.out.printf("SWITCHING SLOT MACHINES...\n\n");
                    System.out.println("*************************************************************************************************");
                    System.out.println("|                              Welcome to the Slot Machine Game!!!                              |");
                    mySlotMachine = new SlotMachine();
                    break;
                    
                //C: Cashout Current Tokens of the Machine
                case 'C':
                case 'c':
                    mySlotMachine.cashoutTokens();
                    System.out.printf("\n");
                    break;
                    
                //H: Check how much tokens the user has inserted in all machines
                case 'H':
                case 'h':
                    System.out.printf("HOUSE CREDIT: " + mySlotMachine.getHouseCredit() + "\n\n");
                    break;
  
                //Q: Leave the Casino
                case 'Q':
                case 'q':
                    mySlotMachine.cashoutTokens();
                    System.out.printf("HOUSE CREDIT: " + mySlotMachine.getHouseCredit() + "\n\n");
                    System.out.printf("\nLEAVING THE CASINO...");
                    break;
                    
                default:
                    System.out.printf("UNKNOWN ACTION\n\n");
            }
        }while(key != 'q' && key != 'Q');
    }
}
