package x.y.z.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailServiceImpl implements UserDetailsService, AuthenticationProvider {

	@Resource(name="userDAO")
	private UserDAO userDAO;
	private static final String DB_COLUMN_NM_USERNAME = "email";
	private static final String DB_COLUMN_NM_PASSWORD = "pwd";
	private static final String DB_COLUMN_NM_ROLE = "role";
	
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		String userId = authentication.getName();
		
		UserDetails userDetail = loadUserByUsername(userId);
		String inputPassword = authentication.getCredentials().toString();
		String dbPassowrd = userDetail.getPassword();
		if(!dbPassowrd.equals(inputPassword)) {
			throw new BadCredentialsException("invalid password!!");
		}
		
		Collection<? extends GrantedAuthority> authorities = userDetail.getAuthorities();
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userId,	dbPassowrd, authorities);
		
		// user 정보 setting
		token.setDetails(userDetail);
		
		return token;
	}


	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Map user = userDAO.getUser(username);
		if(user == null)
			throw new UsernameNotFoundException(username +"not exist!!!!");
		
		Collection<GrantedAuthority> role = getAutority(user.get(DB_COLUMN_NM_ROLE).toString());
		String userId = user.get(DB_COLUMN_NM_USERNAME).toString();
		String password = (String)user.get(DB_COLUMN_NM_PASSWORD);
		
		System.out.println("id:" + userId);
		System.out.println("pwd:" + password);
		
		User userdetail = new User(userId, password, true, true, true, true, role);
		
		return userdetail;
	}


	private Collection<GrantedAuthority> getAutority(String role) {
		Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
		roles.add(new SimpleGrantedAuthority(role));
		return roles;
	}

}
