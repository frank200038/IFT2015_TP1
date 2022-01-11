import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Main class that receives commands and executed correspondent operations
 */
public class CardGame {
    static Scanner sc = new Scanner(System.in);
    static CardHand cards = new CardHand();

    public static void processIncomingCommand(String cmd) throws EmptyHandException {

        switch (cmd.toLowerCase()) {
            case "add":{
                System.out.print("\tPlease enter a Suit:\t");

                try{
                    CardSuit s = CardSuit.valueOf(sc.nextLine().toUpperCase().replaceAll("\\s", ""));

                    System.out.print("\tPlease Enter A Value (Between 2 to 10, or the correpondent name like ace):\t");
                    String r = sc.nextLine().toLowerCase().replaceAll("\\s","");

                    int number;

                    switch (r){
                        case "ace" : number = 1; break;
                        case "jack": number = 11; break;
                        case "queen" : number = 12; break;
                        case "king" : number = 13; break;
                        default: {
                            number =  Integer.parseInt(r);
                            if (number < 2 || number > 10){
                                System.out.println("Not a valid number");
                                main(null);
                            }
                        }
                    }
                    cards.addCard(number,s);
                } catch (IllegalArgumentException e){
                    System.out.println("Not a valid type of suit. Try Again");

                    main(null);
                }
                break;
            }
            case "play":{
                System.out.print("\tPlease enter a Suit:\t");
                try{
                    CardSuit s = CardSuit.valueOf(sc.nextLine().toUpperCase().replaceAll("\\s", ""));

                    try{
                        Card removed = cards.play(s);
                        System.out.println(removed);
                    } catch (EmptyHandException e){
                        System.out.println(e.getMessage());
                    }

                } catch (IllegalArgumentException e){
                    System.out.println("Not a valid type of suit. Try Again");

                    main(null);
                }

                break;
            }
            case "print":{

                Iterator<Card> it = cards.iterator();
                it.forEachRemaining(System.out::println);

                break;
            }
            case "suit":{

                System.out.print("\tPlease enter a Suit:\t");

                try{
                    CardSuit s = CardSuit.valueOf(sc.nextLine().toUpperCase().replaceAll("\\s", ""));

                    Iterator<Card> it = cards.suitIterator(s);
                    it.forEachRemaining(System.out::println);

                }
                catch (IllegalArgumentException e){
                    System.out.println("Not a valid type of suit. Try Again");

                    main(null);
                }
                catch(NoSuchElementException e){
                    System.out.println(e.getMessage());
                    main(null);
                }


                break;
            }
            default : {
                System.out.println("Commande invalide");
            }
        }

        main(null);
    }

    public static void main(String[] args) throws EmptyHandException {

        System.out.println("-----------------Menu----------------------");
        System.out.println("\tAdd Card (add)");
        System.out.println("\tPlay Cards (play)");
        System.out.println("\tPrint All Cards (print)");
        System.out.println("\tsuit iterator (suit)");

        System.out.print("Command> ");

        processIncomingCommand(sc.nextLine());
        sc.close();
    }
}
