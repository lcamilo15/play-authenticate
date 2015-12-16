package com.feth.play.module.pa.providers.openid_connect;

import com.fasterxml.jackson.databind.JsonNode;
import com.feth.play.module.pa.providers.oauth2.BasicOAuth2AuthUser;
import com.feth.play.module.pa.providers.oauth2.OAuth2AuthInfo;

/**
 * Created by lcamilo on 12/15/15.
 */
public class OpenIDConnectUser extends BasicOAuth2AuthUser {

    private static class Constants {
        //REQUIRED. Subject Identifier. A locally unique and never reassigned identifier within the Issuer
        //for the End-User, which is intended to be consumed by the Client, e.g., 24400320 or
        //AItOawmwtWwcT0k51BayewNvutrJUqsvl6qs7A4. It MUST NOT exceed 255 ASCII characters in length.
        //The sub value is a case sensitive string.
        public static final String ID = "sub"; // "00000000000000",
        public static final String EMAIL = "email"; // "fred.example@gmail.com",
        public static final String NAME = "name"; // "Fred Example",
        public static final String EMAIL_IS_VERIFIED = "email_verified"; // true,
        public static final String ADDRESS = "address";
        public static final String OFFLINE_ACCESS = "offline_access";
    }

    private String name;
    private String email;
    private boolean emailIsVerified = false;

    public OpenIDConnectUser(final JsonNode n, OAuth2AuthInfo info, String state) {
        super("id", info, state);

        if (n.has(Constants.NAME)) {
            this.name = n.get(Constants.NAME).asText();
        }

        if (n.has(Constants.EMAIL)) {
            this.email = n.get(Constants.EMAIL).asText();
        }

        if (n.has(Constants.EMAIL_IS_VERIFIED)) {
            this.emailIsVerified = n.get(Constants.EMAIL_IS_VERIFIED)
                    .asBoolean();
        }

    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public String getProvider() {
        return OpenIDAuthProvider.PROVIDER_KEY;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public boolean isEmailVerified() {
        return emailIsVerified;
    }

}

