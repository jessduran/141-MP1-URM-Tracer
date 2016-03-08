/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urmtracer;

/**
 *
 * @author acer
 */
public class Instruction {
    int index;
    String command;
    String[] arguments;
    
    Instruction(){
        index = 0;
        command = null;
        arguments = null;
    }
    
    Instruction(int index, String command, String[] syntax){
        this.index = index;
        this.command = command;
        this.arguments = syntax;
    }
}
