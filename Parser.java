package Assembler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class Parser extends SymbolTable
{
    public static final int A_COMMAND = 0;
    public static final int C_COMMAND = 1;
    public static final int L_COMMAND = 2;
    public static final int COMMENT = 3;
    
    int commandType(String str)
    {
        int x;
        
        if('@'==str.charAt(0))
        {
            x = A_COMMAND;
        }
        else if(str.charAt(0)=='(' && str.charAt(str.length()-1)==')' )
        {
            x = L_COMMAND;
        }
        else if(str.charAt(0)=='A'||str.charAt(0)=='D'||str.charAt(0)=='M'||str.charAt(0)=='0')
        {
            x = C_COMMAND;
        }
        else if(str.charAt(0)=='/' && str.charAt(1)=='/' )
        { 
            x = COMMENT;
        }
        else
        {
            x = -1;
            System.out.println("Invalid Command");
            System.exit(0);
        }
        
        return x;
    }
    
    String acom(String str)
    {
        String out = null;
        if(str.charAt(0) == '@')
        {
            out = intTobin(str.substring(1));
        }
        return out;
    }
    
    String comp(String str)
    {
        String out = null;
        int i = str.indexOf('=');
        int j = str.indexOf(';');
        int k = str.indexOf("//");
        
        if(i != -1 && k == -1)
            out = str.substring(i+1);
        else if(i !=-1 && k != -1)
            out = str.substring(i+1, k);
        else if(j != -1)
            out = str.substring(0, j);
        
        return out;
    }
 
    int DestOrJump(String str)
    {
        int x = -1;
        int i = str.indexOf('=');
        int j = str.indexOf(';');
        
        if(i != -1 )
        {
            x = 0 ;
        }
        else if(j != -1)
        {
            x = 1;
        }
        
        return x;
    }
    
    String dest(String str)
    {
        String out = null;
        int i = str.indexOf('=');
        if(i != -1)
        {
            out = str.substring(0, i);
        }
        
        return out;
    }
    
    String jump(String str)
    {
        String out = null;
        int i = str.indexOf(';');
        int j = str.indexOf("//");
        if(i != -1 && j!= -1)
        {
            out = str.substring(i+1,j);
        }
        else if( i!= -1 && j == -1)
            out = str.substring(i+1);
 
        return out;
    }
    
    
    boolean checkN(char ch)
    {
        return ch=='0'||ch=='1'||ch=='2'||ch=='3'||ch=='4'||ch=='5'||ch=='6'||ch=='7'||ch=='8'||ch=='9';
    }
    
    void pass1(String infile)throws IOException
    {
        AddDefault();
        
        FileReader in = new FileReader(infile);
        BufferedReader inF  = new BufferedReader(in);
       
        String line; 
        
        int no = 0;
        
        while((line = inF.readLine())!= null)
        {
            line = line.trim();
            line = line.replaceAll("\\s", "");
       
            
            if(!line.isEmpty())
            {
                int c = commandType(line);
                 
                if(c == A_COMMAND)
                {
                    no++;
                }
                else if( c == L_COMMAND)
                {
                    int len = line.length();
                    
                    String sno = Integer.toString(no);
                    
                    Add(line.substring(1,len-1),sno);
                
                }
                else if(c == C_COMMAND)
                    no++;
                
            }
            
        }
      
        inF.close();
        
    }
    
    
    void pass2(String infile) throws IOException
    {
        pass1(infile);
        
        FileReader in = new FileReader(infile);
        BufferedReader inF  = new BufferedReader(in);
       
        String line; 
        
        int var = 16;
        
        while((line = inF.readLine())!= null)
        {
            line = line.trim();
            line = line.replaceAll("\\s", "");
       
            if(!line.isEmpty())
            {
                int c = commandType(line);
                 
                if(c == A_COMMAND)
                {
                    if(!checkN(line.charAt(1)))
                    {
                        String ch = Search(line.substring(1));
                        if(ch == null)
                        {
                            String forVar = Integer.toString(var);
                            Add(line.substring(1),forVar);
                            var++;
                        }
                    }
                    
                }
            }
            
        }
      
        inF.close();
        
        
    }
    
    
   
    
    void input(String infile)throws IOException
    {
        pass2(infile);
        
        FileReader in = new FileReader(infile);
        BufferedReader inF  = new BufferedReader(in); 
        FileWriter out = new FileWriter("C:\\Users\\PROXD\\OneDrive\\Documents\\NetBeansProjects\\Project_6\\src\\main\\java\\Assembler\\MaxL.hack");
        BufferedWriter outF = new BufferedWriter(out);
       
        String line; 
        
        Code a = new Code();
       
        while((line = inF.readLine())!= null)
        {
            line = line.trim();
            line = line.replaceAll("\\s", "");
       
            if(!line.isEmpty())
            {   
                int c = commandType(line);
            
                
                if(c == A_COMMAND)
                {   
                    if(!checkN(line.charAt(1)))
                    {
                        String sym = Search(line.substring(1));
                        outF.write(sym);
                        outF.newLine();
                      
                    }
                    else
                    {
                        String Acom = acom(line);
                        outF.write(Acom);
                        outF.newLine();
                    }
                  
                }
                else if(c == C_COMMAND)
                {
                    String Comp = comp(line);
                    String Dest;
                    String Jump; 
                    String AComp = a.Comp(Comp);
                    String ADest = "000";
                    String AJump = "000";
                        
                   int DorJ = DestOrJump(line);
                    
                   if(DorJ == 0)
                    {
                        Dest = dest(line);
                        ADest = a.Dest(Dest);
                    }
                    else if(DorJ == 1)
                    {
                        Jump = jump(line);
                        AJump = a.Jump(Jump);
                    }
                
                    outF.write("111"+AComp+ADest+AJump);
                    outF.newLine();
                }
                else if(c == L_COMMAND)
                {
                    
                    
                }
                else if(c == COMMENT)
                {
                    
                }
            }
          
        }
       
        outF.flush();
        outF.close();
        inF.close();
    }
    
}
