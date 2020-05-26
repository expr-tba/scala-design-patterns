package com.bia.andersson.scheduler.dao

import com.bia.andersson.scheduler.TestEnvironment
import org.scalatest.{BeforeAndAfter, FlatSpec, ShouldMatchers}

class DaoServiceTest
    extends FlatSpec
    with ShouldMatchers
    with BeforeAndAfter
    with TestEnvironment {
  override val databaseService  = new H2DatabaseService
  override val migrationService = new MigrationService
  override val daoService       = new DaoServiceImpl

  before {
    // we run this here. Generally migrations will only
    // be dealing with data layout and we will be able to have
    // test classes that insert test data.
    migrationService.runMigrations()
  }

  after {
    migrationService.cleanupDatabase()
  }

  "readResultSet" should "properly iterate over a result set and apply a function to it." in {
    val connection = daoService.getConnection()
    try {
      val result = daoService.executeSelect(
        connection.prepareStatement("SELECT name FROM people")
      ) { rs =>
        daoService.readResultSet(rs)(row => row.getString("name"))
      }
      result should have size (3)
      result should contain("Ivan")
      result should contain("Maria")
      result should contain("John")
    } finally {
      connection.close()
    }
  }
}
