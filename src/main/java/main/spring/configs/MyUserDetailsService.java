package main.spring.configs;

import main.spring.dao.UserDAO;
import main.spring.models.Authority;
import main.spring.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService
{
    //get user from the database, via Hibernate
    private UserDAO userDAO;
    @Autowired
    public void setUserDAO(UserDAO userDAO)
    {this.userDAO = userDAO;}

    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(final String login) throws UsernameNotFoundException
    {
        System.out.println("Loading user by login: "+login);
        main.spring.models.User user = userDAO.findByLogin(login);
        System.out.println("USER ROLE: "+user.getRole().getName());
        Set<Authority> authoritySet = user.getRole().getAuthorities();
        for(Authority authority: authoritySet)
        {
            System.out.println("Authority: "+authority.getName());
        }
        System.out.println("Email: "+user.getEmail());
        Role role = user.getRole();
        Set<Authority> authorities = role.getAuthorities();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        for (Authority authority : authorities)
        {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getName());
            grantedAuthorities.add( grantedAuthority );
        }

        GrantedAuthority roleAuthority = new SimpleGrantedAuthority( role.getName() );
        grantedAuthorities.add( roleAuthority );

        return buildUserForAuthentication(user, grantedAuthorities);
    }

    // Converts main.spring.models User user to
    // org.springframework.security.core.userdetails.User
    private User buildUserForAuthentication(main.spring.models.User user, List<GrantedAuthority> authorities)
    {
        return new User(user.getLogin(), user.getPassword(),true, true, true, true, authorities);
    }
}