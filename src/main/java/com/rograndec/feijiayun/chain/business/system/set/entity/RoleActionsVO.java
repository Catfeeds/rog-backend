package com.rograndec.feijiayun.chain.business.system.set.entity;

import java.util.List;

/**
 * Created by madong on 2017/8/30.
 */
public class RoleActionsVO {
    private SysRole role;
    private List<SysRoleAction> roleActions;

    public SysRole getRole() {
        return role;
    }

    public List<SysRoleAction> getRoleActions() {
        return roleActions;
    }

    public void setRole(SysRole role) {
        this.role = role;
    }

    public void setRoleActions(List<SysRoleAction> roleActions) {
        this.roleActions = roleActions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleActionsVO that = (RoleActionsVO) o;

        if (role != null ? !role.equals(that.role) : that.role != null) return false;
        return roleActions != null ? roleActions.equals(that.roleActions) : that.roleActions == null;
    }

    @Override
    public int hashCode() {
        int result = role != null ? role.hashCode() : 0;
        result = 31 * result + (roleActions != null ? roleActions.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RoleActionsVO{" +
                "role=" + role +
                ", roleActions=" + roleActions +
                '}';
    }
}
