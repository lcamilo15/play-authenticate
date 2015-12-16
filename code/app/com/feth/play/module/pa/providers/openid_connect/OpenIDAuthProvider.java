package com.feth.play.module.pa.providers.openid_connect;

import com.fasterxml.jackson.databind.JsonNode;
import com.feth.play.module.pa.exceptions.AccessTokenException;
import com.feth.play.module.pa.exceptions.AuthException;
import com.feth.play.module.pa.providers.oauth2.OAuth2AuthProvider;
import com.google.inject.Inject;
import play.Application;
import play.Logger;
import play.libs.ws.WS;
import play.libs.ws.WSResponse;

public class OpenIDAuthProvider extends OAuth2AuthProvider<OpenIDConnectUser, OpenIDConnectInfo> {

    public static final  String PROVIDER_KEY = "openid_connect";
    private static final String USER_INFO_URL_SETTING_KEY = "userInfoUrl";

	@Inject
	public OpenIDAuthProvider(final Application app) {
		super(app);
	}

	@Override
	protected OpenIDConnectInfo buildInfo(WSResponse r) throws AccessTokenException {
        final JsonNode n = r.asJson();
        Logger.debug(n.toString());

        if (n.get(OAuth2AuthProvider.Constants.ERROR) != null) {
            throw new AccessTokenException(n.get(
                    OAuth2AuthProvider.Constants.ERROR).asText());
        } else {
            return new OpenIDConnectInfo(n);
        }
	}

	@Override
	protected OpenIDConnectUser transform(OpenIDConnectInfo info, String state) throws AuthException {

        final String url = getConfiguration().getString(
                USER_INFO_URL_SETTING_KEY);
        final WSResponse r = WS
                .url(url)
                .setQueryParameter(OAuth2AuthProvider.Constants.ACCESS_TOKEN,
                        info.getAccessToken()).get()
                .get(getTimeout());

        final JsonNode result = r.asJson();
        if (result.get(OAuth2AuthProvider.Constants.ERROR) != null) {
            throw new AuthException(result.get(
                    OAuth2AuthProvider.Constants.ERROR).asText());
        } else {
            OpenIDConnectUser openIDConnectUser = new OpenIDConnectUser(result, info, state);
            return openIDConnectUser;
        }
	}

	@Override
	public String getKey() {
		return PROVIDER_KEY;
	}

}
