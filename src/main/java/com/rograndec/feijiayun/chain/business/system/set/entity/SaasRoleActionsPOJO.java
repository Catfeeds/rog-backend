package com.rograndec.feijiayun.chain.business.system.set.entity;

import java.util.List;

/**
 * Created by madong on 2017/8/27.
 */
public class SaasRoleActionsPOJO {
    private SysRole role;
    private List<SysAction> actions;

    public SysRole getRole() {
        return role;
    }

    public List<SysAction> getActions() {
        return actions;
    }

    public void setRole(SysRole role) {
        this.role = role;
    }

    public void setActions(List<SysAction> actions) {
        this.actions = actions;
    }

    @Override
    public String toString() {
        return "SaasRoleActionsPOJO{" +
                "role=" + role +
                ", actions=" + actions +
                '}';
    }
}
