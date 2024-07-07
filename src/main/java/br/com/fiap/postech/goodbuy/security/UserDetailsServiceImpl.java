package br.com.fiap.postech.goodbuy.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Value("${goodbuy.user.url}")
	String baseURI;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		RestClient restClient = RestClient.create();
		User user = restClient.get()
				.uri(baseURI + "/findByLogin/{userName}", userName)
				.retrieve()
				.body(User.class);
		if (user != null) {
			return new UserDetailsImpl(user);
		} else {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
	}
}