package Assembler;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class Assembler {
    public static void main(String[] args) {
        
        Parser obj = new Parser();
        
        String path = "C:\\Users\\PROXD\\OneDrive\\Documents\\NetBeansProjects\\Project_6\\src\\main\\java\\Assembler\\MaxL.asm";
       
        try
        {
            obj.input(path);
        }
        catch(IOException e)
        {
            System.out.println("file error");
        }
        
    }
    
}