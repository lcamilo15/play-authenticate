package com.feth.play.module.pa.providers.openid_connect;

import com.fasterxml.jackson.databind.JsonNode;
import com.feth.play.module.pa.providers.oauth2.OAuth2AuthInfo;
import com.feth.play.module.pa.providers.oauth2.OAuth2AuthProvider;

import java.util.Date;

/**
 * Created by lcamilo on 12/15/15.
 */
public class OpenIDConnectInfo extends OAuth2AuthInfo {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static final String ID_TOKEN = "id_token";
    private String bearer;
    private String idToken;

    public OpenIDConnectInfo(final JsonNode node) {
        super(	node.get(OAuth2AuthProvider.Constants.ACCESS_TOKEN) != null ? node.get(OAuth2AuthProvider.Constants.ACCESS_TOKEN).asText() : null,
                node.get(OAuth2AuthProvider.Constants.EXPIRES_IN) != null ? new Date().getTime() + node.get(OAuth2AuthProvider.Constants.EXPIRES_IN).asLong() * 1000 : -1,
                node.get(OAuth2AuthProvider.Constants.REFRESH_TOKEN) != null ? node.get(OAuth2AuthProvider.Constants.REFRESH_TOKEN).asText() : null);

        bearer = node.get(OAuth2AuthProvider.Constants.TOKEN_TYPE).asText();
        idToken = node.get(ID_TOKEN).asText();
    }

    public String getBearer() {
        return bearer;
    }

    public String getIdToken() {
        return idToken;
    }
}
