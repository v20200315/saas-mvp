CREATE TABLE IF NOT EXISTS customer
(
    id            CHAR(36)     NOT NULL,
    tenant_id     CHAR(36)     NOT NULL,
    name          VARCHAR(100) NOT NULL,
    contact_name  VARCHAR(50),
    contact_phone VARCHAR(30),
    email         VARCHAR(100),
    status        TINYINT      NOT NULL DEFAULT 1,

    created_at    DATETIME     NOT NULL,
    created_by    CHAR(36)     NOT NULL,
    updated_at    DATETIME     NOT NULL,
    updated_by    CHAR(36)     NOT NULL,

    PRIMARY KEY (id),
    KEY idx_customer_tenant_id (tenant_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;