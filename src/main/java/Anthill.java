import javax.script.ScriptEngine;

public class Anthill {
    public static void main(String[] argv){
        System.out.println("Ready to create the game!");
        Anthill.create();
    }

    public static void create(){
        var proc = 10;
        for(var i = 0; i < 10; i++){
            System.out.println("Create " + proc * i + "%");
        }
        System.out.println("Finish");
    }
}
