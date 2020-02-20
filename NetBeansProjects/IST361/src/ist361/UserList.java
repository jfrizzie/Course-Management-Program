package ist361;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HJC5283
 */
public class UserList {
    
    private final List<User> users;
    
    public UserList() {
        users = new ArrayList();
    }
    
    public List<User> getUsers() {
        return users;
    }
    
    public User find(String username) {
        for(User i: users) {
            if(i.getUsername().equals(username)) {
                return i;
            }
        }
        return null;
    }
    
}
