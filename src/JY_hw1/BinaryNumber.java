package JY_hw1;

import java.util.Arrays;

/**
 * Assignment : 1
 * Name: Jiayin Huang
 * CWID: 10477088
 * Course: CS-570
 */

public class BinaryNumber {
    private int data[];
    private boolean overflow;

    // for creating a binary number of length, length and consisting only of zeros.
    public BinaryNumber(int length){
        if(length <= 0){
            throw new IllegalArgumentException("Length should be greater 0");
        }
        data = new int[length];
        for(int i = 0; i < length; i++){
            data[i] = 0;
        }
    }

    // for creating a binary number given a string
    public BinaryNumber(String str){
        data = new int[str.length()];
        // convert each char base on str to int and store in data
        for(int i = 0; i < str.length(); i++){
            char ch = str.charAt(i);
            int num = Character.getNumericValue(ch);
            if(num == 1 || num == 0){
                data[i] = num;
            }else{
                throw new IllegalArgumentException("Illegal input str, please only use '0' and '1' as str.");
            }
        }
    }

    public int[] getData() {
        return data;
    }

    // for determining the length of a binary number
    public int getLength(){
       return data.length;
    }

    // for obtaining a digit of a binary number given an index
    public int getDigit(int index){
        if(index < 0 || index >= data.length){
            throw new ArrayIndexOutOfBoundsException("The index is out of bounds, please retry.");
        }else{
            return data[index];
        }
    }

//     shifting all digits in a binary number any number of places to the right, the new digit will be 0;
    public void shiftR(int amount){
        if(amount < 0){
            throw new IllegalArgumentException("Amount cannot be negative number.");
        }
        //create a new array with reallocate length
        int[] newData = new int[this.getLength() + amount];
        for(int i = amount; i < newData.length; i++){
            newData[i] = data[i-amount];
        }
        for(int i = 0; i < amount; i++){
            newData[i] = 0;
        }
        data = newData;
    }

    // for adding two binary numbers, one is the binary number that receives the message,
    // and the other is given as a parameter
    // store the result in the data array
    public void add(BinaryNumber aBinaryNumber){
        // check if the lengths of the binary numbers do not coincide
        if(data.length != aBinaryNumber.getLength()){
            System.out.println("Two binary numbers' length do not coincide");
        }else{
            int[] aBinary = aBinaryNumber.getData();
            int carry = 0;
            for(int i = 0; i < data.length; i++){
                int sumOfDigit = carry + data[i] + aBinary[i];
                if(sumOfDigit == 0){
                    data[i] = 0;
                    carry = 0;
                }else if(sumOfDigit == 1){
                        data[i] = 1;
                        carry = 0;
                }else if(sumOfDigit == 2){
                        data[i] = 0;
                        carry = 1;
                }else{
                        data[i] = 1;
                        carry = 1;
                }
            }
            if(carry == 1){
                overflow = true;
            }
        }
    }

    // for transforming a binary number to a String.
    // If the number is the result of an overflow, the string “Overflow” should be returned
    public String toString(){
        if(overflow){
            return "Overflow";
        }else{
            String string = " "; // declare a empty string to store data;
            for(int i = 0; i < data.length; i++){
                string += data[i]; // string = num + " ", convert int to string;
            }
            return string;
        }
    }

    //Transform a Binary Number to its Decimal
    public int toDecimal(){
        int decimal = 0;
        for(int i = 0; i < data.length; i++){
            decimal += (int) (data[i]*Math.pow(2,i));
        }
        return decimal;
    }

    // Operation that clears the overflow flag
    public void clearOverflow(){
        overflow = false;
    }


//
//    public static void main(String[] args) {
////        BinaryNumber b1 = new BinaryNumber("10110");
////        BinaryNumber b2 = new BinaryNumber("11100");
////        System.out.println(b1.getDigit(3));
//////        b1.shiftR(3);
//////        b2.shiftR(5);
//////        System.out.println(b1.toString());
//////        System.out.println(b2.toString());
////        System.out.println();
////        System.out.println(b1.toDecimal());
////        System.out.println( b1.toString());
////        System.out.println(b2.toDecimal());
////        b1.add(b2);
////        System.out.println(b1.toString());
////        System.out.println(b1.toDecimal());
//
//    }
}
