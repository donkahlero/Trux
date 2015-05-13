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

import se.gu.tux.trux.datastructure.Message;
import se.gu.tux.trux.datastructure.Data;
import se.gu.tux.trux.datastructure.ProtocolMessage;
import se.gu.tux.truxserver.dbconnect.FriendshipHandler;
import se.gu.tux.truxserver.dbconnect.SessionHandler;
import se.gu.tux.truxserver.dbconnect.UserHandler;

/**
 *
 * @author jonas
 */
public class MessageSwitcher {
    /**
     * Static part.
     */
    private static MessageSwitcher ms = null;
    
    public static MessageSwitcher getInstance()
    {
        if(ms == null)
            ms = new MessageSwitcher();
        return ms;
    }
    
    public static MessageSwitcher gI()
    {
        return getInstance();
    }
    
    /**
     * Non-static part.
     */
    private MessageSwitcher() {}
    
    public Data handleMessage(Data m)
    {
        if(m instanceof ProtocolMessage) {
            ProtocolMessage pm = (ProtocolMessage) m;
            if(pm.getType() == ProtocolMessage.Type.AUTO_LOGIN_REQUEST)
                return UserHandler.gI().autoLogin(pm);
            else if(pm.getType() == ProtocolMessage.Type.LOGOUT_REQUEST)
                return SessionHandler.gI().endSession(pm);
            else if(pm.getType() == ProtocolMessage.Type.PEOPLE_SEARCH)
                return UserHandler.gI().findUsers(pm);
            else if(pm.getType() == ProtocolMessage.Type.FRIEND_REQUEST)
                return FriendshipHandler.gI().sendFriendRequest(pm);
            else if(pm.getType() == ProtocolMessage.Type.FRIEND_REMOVE)
                return FriendshipHandler.gI().unfriendUser(pm);
        } else if (m instanceof Message){
            return m;
        }
        
        return m;
    }
}
