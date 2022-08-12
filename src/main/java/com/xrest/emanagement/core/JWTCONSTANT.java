package com.xrest.emanagement.core;

import org.springframework.beans.factory.annotation.Value;

public class JWTCONSTANT {
//    @Value("${jwt.key}")
    public static String KEY = "publicStaticVoidMain";
    public static final int EXPIRATION = 80_80_80_80;
    public static final String PREFIX = "Bearer";
    public static final String TYPE = "Authorization";
}
