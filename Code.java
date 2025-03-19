/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assembler;

/**
 *
 * @author PROXD
 */
public class Code
{
    String Dest(String str)
    {
        String out = "000";
        
        if(str.equals("M"))
            out = "001";
        else if(str.equals("D"))
            out = "010";
        else if(str.equals("MD"))
            out = "011";
        else if(str.equals("A"))
            out = "100";
        else if(str.equals("AM"))
            out = "101";
        else if(str.equals("AD"))
            out = "110";
        else if(str.equals("AMD"))
            out = "111";
        
        return out;
                        
    }
    
    String Comp(String str)
    {
        String out = null;
        
        if(str.equals("0"))
            out = "0101010";
        else if(str.equals("1"))
            out = "0111111";
        else if(str.equals("-1"))
            out = "0111010";
        else if(str.equals("D"))
            out = "0001100";
        else if(str.equals("A"))
            out = "0110000";
        else if(str.equals("!D"))
            out = "0001101";
        else if(str.equals("!A"))
            out = "0110001";
        else if(str.equals("-D"))
            out = "0001111";
        else if(str.equals("-A"))
            out = "0110011";
        else if(str.endsWith("D+1"))
            out = "0011111";
        else if(str.equals("A+1"))
            out = "0110111";
        else if(str.equals("D-1"))
            out = "0001110";
        else if(str.equals("A-1"))
            out = "0110010";
        else if(str.equals("D+A"))
            out = "0000010";
        else if(str.equals("D-A"))
            out = "0010011";
        else if(str.equals("A-D"))
            out = "0000111";
        else if(str.equals("D&A"))
            out = "0000000";
        else if(str.equals("D|A"))
            out = "0010101";                    //end of a=0;
        else if(str.equals("M"))
            out = "1110000";
        else if(str.equals("!M"))
            out = "1110001";
        else if(str.equals("-M"))
            out = "1110011";
        else if(str.equals("M+1"))
            out = "1110111";
        else if(str.equals("M-1"))
            out = "1110010";
        else if(str.equals("D+M"))
            out = "1000010";
        else if(str.equals("D-M"))
            out = "1010011";
        else if(str.equals("M-D"))
            out = "1000111";
        else if(str.equals("D&M"))
            out = "1000000";
        else if(str.equals("D|M"))
            out = "1010101";
        
        return out;
                    
    }
    
    String Jump(String str)
    {
         String out = "000";
        
        if(str.equals("JGT"))
            out = "001";
        else if(str.equals("JEQ"))
            out = "010";
        else if(str.equals("JGE"))
            out = "011";
        else if(str.equals("JLT"))
            out = "100";
        else if(str.equals("JNE"))
            out = "101";
        else if(str.equals("JLE"))
            out = "110";
        else if(str.equals("JMP"))
            out = "111";
        
        return out;
    }
    
}
