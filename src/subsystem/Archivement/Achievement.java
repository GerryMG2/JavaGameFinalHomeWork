package subsystem.Archivement;

import java.util.ArrayList;

public class Achievement {

    private String Logro;
    private boolean Estado;
    
    Achievement(){
        
    }

    Achievement(String Logro, boolean Estado) {
        this.Logro = Logro;
        this.Estado = Estado;
    }

    public Achievement Logros(String Logro) {
        String[] parts = Logro.split("#");
        if (parts[1].equals("true")) {
            return new Achievement(parts[0],true);
        }
        else{
            return new Achievement(parts[0],false);
        }

    }
    
    public String getLogro(){
        return Logro;
    }
    
    public String getBoolean(){
        return Boolean.toString(Estado);
    }
}
