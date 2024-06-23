package com.introme.infrastructure.security.oauth;


import com.introme.infrastructure.security.user.IntromeUserContext;
import com.introme.user.entity.Role;
import com.introme.user.entity.User;
import com.introme.user.service.UserCommand;
import com.introme.user.service.UserQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class IntromeOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserCommand userCommand;
    private final UserQuery userQuery;
    private final OAuth2ProviderUserResolver oAuth2ProviderUserResolver;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2Provider oAuth2Provider = OAuth2Provider.from(userRequest.getClientRegistration().getRegistrationId());
        OAuth2User oAuth2User = new DefaultOAuth2UserService().loadUser(userRequest);
        OAuth2ProviderUser providerUser = oAuth2ProviderUserResolver.resolve(userRequest, oAuth2User);

        User user = userQuery.getUserOrNull(oAuth2User.getAttribute(StandardClaimNames.EMAIL), oAuth2Provider.userProviderType());
        if (user == null) {
            user = userCommand.createUser(providerUser, Role.USER);
        }
        return IntromeOauth2User.builder()
                .oauth2User(oAuth2User)
                .userContext(IntromeUserContext.of(user, providerUser))
                .build();
    }
}