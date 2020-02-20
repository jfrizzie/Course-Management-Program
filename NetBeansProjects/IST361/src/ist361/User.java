package ist361;

import java.util.List;
import java.util.*;

/**
 * @author rqe5116
 */
public class User {
    
    private String username, password;
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public String getUsername() {
        return username;
    }
    
    public boolean authenticate(String password) {
        return this.password.equals(password);
    }
    
}
