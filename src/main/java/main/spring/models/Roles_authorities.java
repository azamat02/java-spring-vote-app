package main.spring.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "RoleAuthoritiesEntity")
@Table(name = "roles_authorities")
public class Roles_authorities implements Serializable
{
    private long role_id;
    private long authority_id;


    public Roles_authorities(long role_id, long authority_id) {
        this.role_id = role_id;
        this.authority_id = authority_id;
    }

    public Roles_authorities() {

    }

    @Id
    @Column(name = "role_id")
    public long getRole_id() {
        return role_id;
    }

    public void setRole_id(long role_id) {
        this.role_id = role_id;
    }

    @Id
    @Column(name = "authority_id")
    public long getAuthority_id() {
        return authority_id;
    }

    public void setAuthority_id(long authority_id) {
        this.authority_id = authority_id;
    }

}
