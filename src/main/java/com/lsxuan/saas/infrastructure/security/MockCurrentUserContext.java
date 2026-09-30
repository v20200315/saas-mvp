package com.lsxuan.saas.infrastructure.security;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MockCurrentUserContext implements CurrentUserContext {

    private static final UUID USER_ID = UUID.fromString("0198f3c2-7a21-7000-8000-000000000001");

    private static final UUID TENANT_ID = UUID.fromString("0198f3c2-7a21-7000-8000-000000000002");

    @Override
    public UUID userId() {

        return USER_ID;
    }

    @Override
    public UUID tenantId() {
        
        return TENANT_ID;
    }
}