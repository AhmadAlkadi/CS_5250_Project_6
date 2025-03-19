package Assembler;

import java.util.LinkedList;

public class SymbolTable {
    LinkedList ll = new LinkedList();
    
    String intTobin(String s)
    {
        String out;
        
        int dec = Integer.parseInt(s);
        out = Integer.toBinaryString(dec);
        int len = out.length();
            
        String bits = "0000000000000000";
            
        if( len < 16)
            out = bits.substring(0,16-len).concat(out);
        else 
            out = out.substring(len-16);
            
        return out;
    }
    
    void Add(String sym,String val)
    {
        Symbol a = new Symbol();
        a.sym = sym;
        a.val = intTobin(val);
        ll.add(a);   
    }
    
    void AddDefault()
    {
        Add("SP","0");
        Add("LCL","1");
        Add("ARG","2");
        Add("THIS","3");
        Add("THAT","4");
        Add("R0","0");
        Add("R1","1");
        Add("R2","2");
        Add("R3","3");
        Add("R4","4");
        Add("R5","5");
        Add("R6","6");
        Add("R7","7");
        Add("R8","8");
        Add("R9","9");
        Add("R10","10");
        Add("R11","11");
        Add("R12","12");
        Add("R13","13");
        Add("R14","14");
        Add("R15","15");
        Add("SCREEN","16384");
        Add("KBD","24576");
    }
    
    String Search(String s)
    {
        String out = null;
        
        for(int i=0;i<ll.size();i++)
        {
            Symbol a;
            a = (Symbol) ll.get(i);
            
            if(a.sym.equals(s))
            {
                out = a.val;
                break;
            }
        }
        
        return out;
    }
    
}
