package com.example.echannelverification.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class KeycloakOauth2UserService extends OidcUserService {

    private final JwtDecoder jwtDecoder;
    private final OAuth2Error INVALID_REQUEST = new OAuth2Error(OAuth2ErrorCodes.INVALID_REQUEST);


    @Override
    public OidcUser loadUser(final OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        final OidcUser user = super.loadUser(userRequest);
        final Set<OidcUserAuthority> authorities = new LinkedHashSet<>(extractKeycloakAuthorities(userRequest, user.getUserInfo()));
        return new DefaultOidcUser(authorities, userRequest.getIdToken(), user.getUserInfo());
    }

    private List<OidcUserAuthority> extractKeycloakAuthorities(final OidcUserRequest userRequest, final OidcUserInfo userInfo) {
        final Jwt token = parseJwt(userRequest.getAccessToken().getTokenValue());
        final Map<String, Collection<String>> realmAccess = token.getClaim("realm_access");
        final Collection<String> roles = realmAccess.get("roles");
        return roles.stream()
                .map(role -> String.format("ROLE_%s", role.toUpperCase().trim()))
                .map(role -> new OidcUserAuthority(role, userRequest.getIdToken(), userInfo))
                .toList();
    }

    private Jwt parseJwt(String accessTokenValue) {
        try {
            return jwtDecoder.decode(accessTokenValue);
        } catch (JwtException e) {
            throw new OAuth2AuthenticationException(INVALID_REQUEST, e);
        }
    }
}