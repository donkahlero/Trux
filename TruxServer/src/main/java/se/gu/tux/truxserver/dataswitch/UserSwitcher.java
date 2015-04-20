/*
 * Copyright 2015 jonas.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package se.gu.tux.truxserver.dataswitch;

import se.gu.tux.trux.datastructure.Data;
import se.gu.tux.trux.datastructure.User;
import se.gu.tux.truxserver.dbconnect.UserHandler;

/**
 *
 * @author jonas
 */
public class UserSwitcher {
        /**
     * Static part.
     */
    private static UserSwitcher us = null;
    
    static {
        if(us == null)
            us = new UserSwitcher();
    }
    
    protected static UserSwitcher getInstance() {
        return us;
    }
    
    protected static UserSwitcher gI() {
        return us;
    }
    
    /**
     * Non-static part.
     */
    private UserSwitcher() {}
    
    protected Data handleUser(User ud) {
    	//Do something.
        //Session null
        if(ud.getSessionId() == -1)
            return UserHandler.gI().login(ud);
        else
            return null;
    }
}
