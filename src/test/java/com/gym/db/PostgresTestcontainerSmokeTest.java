package com.gym.db;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.assertj.core.api.Assertions.assertThat;

class PostgresTestcontainerSmokeTest extends AbstractIntegrationTest {

    @Test
    void containerSobeEAceitaConexaoJdbc() throws Exception {
        assertThat(postgres.isRunning()).isTrue();

        try (Connection connection = DriverManager.getConnection(
                postgres.getJdbcUrl(), postgres.getUsername(), postgres.getPassword())) {
            assertThat(connection.isValid(2)).isTrue();
        }
    }
}
