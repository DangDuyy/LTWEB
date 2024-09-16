package model;

import java.io.Serializable;

public class RoleModel implements Serializable {
    public static final long serialVersionUID = 1;

    private int roleId;
    private String roleName;
    public RoleModel(){
        super();
    }

    public RoleModel(int roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }


    @Override
    public String   toString() {
        return "RoleModel{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
