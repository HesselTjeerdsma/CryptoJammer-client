package commands;


import java.util.UUID;



// uses Singleton design pattern
public class UniqueID {
    
    private static UniqueID uniqueID;
    private String clientID;
    
    private UniqueID() {
        clientID = UUID.randomUUID().toString();
    }
    
    // get single instance of uniqueID
    public static UniqueID getUniqueId(){
        if (uniqueID == null) {
            uniqueID = new UniqueID(); 
        }
        return uniqueID;
    }
    
    public String getClientId() {
        return this.clientID;
    }
    
    public void setClientId(String clientID) {
        this.clientID = clientID; 
    }
}
