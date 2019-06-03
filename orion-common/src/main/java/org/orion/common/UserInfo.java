package org.orion.common;

import com.google.common.base.Objects;
import lombok.Getter;
import lombok.Setter;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-29
 */
@Getter
@Setter
public class UserInfo {
    private String userId;
    private String role;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return Objects.equal(userId, userInfo.userId) &&
                Objects.equal(role, userInfo.role) &&
                Objects.equal(name, userInfo.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(userId, role, name);
    }
}
