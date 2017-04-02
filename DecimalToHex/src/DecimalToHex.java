//Created by Matthew Hoinacki. Final version; 2017/03/24 @23:57. Initial version; 2017/03/07 @18:14.
import java.util.Scanner;
public class DecimalToHex {
    private static String DecToBinary (final int DEC_NUM_CONST) {
		int decNum = 0;
		int twoPow;
		int i;
		String binNum = ""; 
		for (i=0;i<4;i++) {        //set to loop whole thing 4 times
            switch (i) {
                case 0:
                    decNum = DEC_NUM_CONST / 16777216; //first byte
                    break;
                case 1:
                    decNum = (DEC_NUM_CONST / 65536) % 256; //second byte
                    break;
                case 2:
                    decNum = (DEC_NUM_CONST / 256) % 256; //third byte
                    break;
                case 3:
                    decNum = DEC_NUM_CONST % 256; //fourth byte
                    break;
            }
            twoPow = 128;
            while (twoPow > 0) {
                if ((decNum / twoPow) == 1) {
                    decNum = decNum % twoPow;
                    binNum = binNum + "1";
                } else {
                    binNum = binNum + "0";
                }
                twoPow = twoPow / 2;
            }
            if (i<3) {
                binNum += " ";
            }
        }
		return binNum;
    }
    private static String BinaryToHex (String binNum) {
        int i = 0;
        String hexNum = "";
        while (i < 8) {
            String subString = binNum.substring(4 * i + (i / 2), (4 * i + (i / 2)) + 4);
            switch (subString) {
                case "1111":
                    hexNum = hexNum + "F";
                    break;
                case "1110":
                    hexNum = hexNum + "E";
                    break;
                case "1101":
                    hexNum = hexNum + "D";
                    break;
                case "1100":
                    hexNum = hexNum + "C";
                    break;
                case "1011":
                    hexNum = hexNum + "B";
                    break;
                case "1010":
                    hexNum = hexNum + "A";
                    break;
                case "1001":
                    hexNum = hexNum + "9";
                    break;
                case "1000":
                    hexNum = hexNum + "8";
                    break;
                case "0111":
                    hexNum = hexNum + "7";
                    break;
                case "0110":
                    hexNum = hexNum + "6";
                    break;
                case "0101":
                    hexNum = hexNum + "5";
                    break;
                case "0100":
                    hexNum = hexNum + "4";
                    break;
                case "0011":
                    hexNum = hexNum + "3";
                    break;
                case "0010":
                    hexNum = hexNum + "2";
                    break;
                case "0001":
                    hexNum = hexNum + "1";
                    break;
                case "0000":
                    hexNum = hexNum + "0";
                    break;
            }
            if (((i % 2) == 1) && (i < 7)) {
                hexNum = hexNum + " ";
            }
            i++;
        }
        return hexNum;
    }

    public static void main(String[] args) {
        Scanner uIn = new Scanner(System.in);
		System.out.print("Enter an integer to be converted: ");
        String binNum;
        String hexNum;
        long rawInput;
        int decNum;
        boolean bigNum;
        boolean validInput;

		while (true) { //whole main loop
            while (true) { // valid input loop
                if (uIn.hasNext("Q") || uIn.hasNext("q")) {
                    System.out.print("Goodbye.");
                    System.exit(0);
                }
                validInput = uIn.hasNextLong();
                if (!(validInput)) {
                    System.out.print("Invalid input; Please enter a number: ");
                    uIn.next();
                    continue;
                } else {
                    rawInput = uIn.nextLong();
                }
                    if (rawInput >= 4294967296L || rawInput < 0) {
                        System.out.print("Value outside valid input range [0,4294967295]. Please try again: ");
                    } else {
                        break;
                    }
            }

            if ((rawInput / 2147483648L) == 1) {
                rawInput %= 2147483648L;
                bigNum = true;
            } else {
                bigNum = false;
            }
            decNum = (int) rawInput;
            binNum = DecToBinary(decNum);
            if (bigNum) {
                binNum = "1" + binNum.substring(1);
            }
            System.out.println(binNum);
            hexNum = BinaryToHex(binNum);
            System.out.println(hexNum);
            System.out.print("Enter another number to be converted, or send q to exit: ");
        }
    }
}
