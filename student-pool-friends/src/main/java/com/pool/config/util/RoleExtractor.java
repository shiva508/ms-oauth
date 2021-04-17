package com.pool.config.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

public class RoleExtractor implements Converter<Jwt, Collection<GrantedAuthority>> {

	@Override
	public Collection<GrantedAuthority> convert(Jwt source) {
			 Map<String, Object> realmRccess=(Map<String, Object>) source.getClaims().get("realm_access");
			 if(null==realmRccess || realmRccess.isEmpty()){
				 return new ArrayList();
			 }
			 Collection<GrantedAuthority> grantedAuthorities=((List<String> )realmRccess.get("roles")).stream().map(role->"ROLE_"+role).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
			return grantedAuthorities;
		}
}
