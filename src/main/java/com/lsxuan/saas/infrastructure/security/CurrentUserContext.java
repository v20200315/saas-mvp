package com.lsxuan.saas.infrastructure.security;

import java.util.UUID;

public interface CurrentUserContext {
    
    UUID userId();

    UUID tenantId();
}