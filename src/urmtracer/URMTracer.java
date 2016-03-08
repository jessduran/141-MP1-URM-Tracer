/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urmtracer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author acer
 */
public class URMTracer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        FileInputStream readFile = new FileInputStream("mp1.in");
        BufferedReader br = new BufferedReader(new InputStreamReader(readFile));
        
        String line = null;
        
        //get register
        line = br.readLine();
        String registerStr[] = line.split(" ");
        int register[] = new int[10];
        for(int i=0; i<registerStr.length; i++){
            register[i] = Integer.parseInt(registerStr[i]);
        }
        System.out.println(java.util.Arrays.toString(register));
        
        //read the rest of the file
        ArrayList<String> instructions = new ArrayList();
        while((line = br.readLine()) != null){
            instructions.add(line);
        } 
        
        //make an arraylist of URM instructions
        ArrayList<Instruction> listOfInstructions = new ArrayList();     
        for(int i=0; i < instructions.size(); i++){
            String instr[] = (instructions.get(i)).split(" ");
            Instruction in = new Instruction();
                in.index = i+1;
                in.command = instr[0];
                in.arguments = Arrays.copyOfRange(instr, 1, instr.length);  
            listOfInstructions.add(in);  
        }
        
        //print URM instructions
        for(int i=0; i < listOfInstructions.size(); i++){
            Instruction instruction = listOfInstructions.get(i);
            System.out.println(instruction.index + "\t" + instruction.command + "\t" + java.util.Arrays.toString(instruction.arguments));
        }
        traceURM(register, listOfInstructions);
    }

    private static void traceURM(int register[], ArrayList<Instruction> listOfInstructions) throws IOException {
        int i=0; 
        File file = writeFile();
        FileWriter fw = new FileWriter(file);
        fw.write(java.util.Arrays.toString(register)+"\n");
        
        while(i < listOfInstructions.size()){
            Instruction instruction = listOfInstructions.get(i);
            if((instruction.command).equals("Z")){
                register = Z(register, instruction.arguments);
                i++;
            }
            else if((instruction.command).equals("S")){
                register = S(register, instruction.arguments);
                i++;
            }
            else if((instruction.command).equals("C")){
                register = C(register, instruction.arguments);
                i++;
            }
            else if((instruction.command).equals("J")){
                int toIndex = J(register, instruction.arguments, i);
                i=toIndex;
            }
            
            
            fw.write(java.util.Arrays.toString(register)+"\n");
            System.out.println(java.util.Arrays.toString(register));
        }
        fw.flush();
        fw.close();
        
        
    }
    

    private static int[] Z(int[] register, String[] arguments) {
        register[Integer.parseInt(arguments[0])]= 0;
        //System.out.println(java.util.Arrays.toString(register));
        return register;
    }

    private static int[] S(int[] register, String[] arguments) {
        register[Integer.parseInt(arguments[0])]++;
        //System.out.println(java.util.Arrays.toString(register));
        return register;
    }

    private static int[] C(int[] register, String[] arguments) {
        register[Integer.parseInt(arguments[1])] = register[Integer.parseInt(arguments[0])];
        //System.out.println(java.util.Arrays.toString(register));
        return register;
    }

    private static int J(int[] register, String[] syntax, int i) {
        //System.out.println(java.util.Arrays.toString(register));
        if(register[Integer.parseInt(syntax[0])] == register[Integer.parseInt(syntax[1])])
            return ((Integer.parseInt(syntax[2]))-1);
        return i+1;
    }
    
 
    private static File writeFile() throws IOException {
        File file = new File("mp1.out");
        file.createNewFile();    
        return file;
    }
}
