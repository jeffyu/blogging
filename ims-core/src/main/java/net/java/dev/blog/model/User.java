/**
 *
 */
package net.java.dev.blog.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;


/**
 * @author Jeff.Yu
 *
 */
public class User {
    private static final String COMMA_DELIMETER = ",";
    private long userID;
    private String loginName;
    private String userPassword;
    private String userName;
    private String userEmail;
    private Date userDate;
    private List<String> authorities = new ArrayList<String>();

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Date getUserDate() {
        return userDate;
    }

    public void setUserDate(Date userDate) {
        this.userDate = userDate;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public String getAuthoritiesAsString() {
        StringBuffer thePrivileges = new StringBuffer();

        for (int i = 0; i < authorities.size(); i++) {
            thePrivileges.append(authorities.get(i));

            if (i < (authorities.size() - 1)) {
                thePrivileges.append(COMMA_DELIMETER);
            }
        }

        return thePrivileges.toString();
    }

    public void setAuthoritiesFromString(String privileges) {
        authorities = new ArrayList<String>();

        StringTokenizer st = new StringTokenizer(privileges, COMMA_DELIMETER);

        while (st.hasMoreTokens()) {
            authorities.add(st.nextToken());
        }
    }
}
