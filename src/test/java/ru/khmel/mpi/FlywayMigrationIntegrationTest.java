package ru.khmel.mpi;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase.DatabaseProvider;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@FlywayTest
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureEmbeddedDatabase(provider = DatabaseProvider.ZONKY)
public class FlywayMigrationIntegrationTest {

//  @Test
//  void databaseMigrates() {
//
//  }
}
