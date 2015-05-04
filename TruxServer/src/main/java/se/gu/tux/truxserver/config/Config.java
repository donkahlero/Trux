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
package se.gu.tux.truxserver.config;

/**
 * Wrapper class for the system config.
 * @author jonas
 */
public class Config {
    /**
     * Static part.
     */
    private static Config config = null;

    static {
    	if (config == null) {
    		config = new Config();
    	}
    }
    
    private Config() {}
    
    public static Config getInstance()
    {
        return config;
    }
    
    public static Config gI()
    {
        return config;
    }
    
    /**
     * Non-static part.
     */
    private boolean verbose;
    
    private String dbaddress;
    private int port;
    private String dbname;
    private String dbuser;
    private String dbpass;
    private int cleanupInterval;
    private int sessionTimeout;
    private short maxNoDBConnections;
    private String gmailUser;
    private String gmailPass;

    public String getDbaddress() {
        return dbaddress;
    }

    public void setDbaddress(String dbaddress) {
        this.dbaddress = dbaddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    public String getDbuser() {
        return dbuser;
    }

    public void setDbuser(String dbuser) {
        this.dbuser = dbuser;
    }

    public String getDbpass() {
        return dbpass;
    }

    public void setDbpass(String dbpass) {
        this.dbpass = dbpass;
    }

    public boolean isVerbose() {
        return verbose;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }
    
    public int getCleanupInterval() {
        return cleanupInterval;
    }

    public void setCleanupInterval(int cleanUpInterval) {
        this.cleanupInterval = cleanUpInterval;
    }
    
    public short getMaxNoDBConnections() {
        return maxNoDBConnections;
    }

    public void setMaxNoDBConnections(short maxNoDBConnections) {
        this.maxNoDBConnections = maxNoDBConnections;
    }
    
    public int getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(int sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }
    
        public String getGmailUser() {
        return gmailUser;
    }

    public void setGmailUser(String gmailUser) {
        this.gmailUser = gmailUser;
    }

    public String getGmailPass() {
        return gmailPass;
    }

    public void setGmailPass(String gmailPass) {
        this.gmailPass = gmailPass;
    }
}
