package com.bia.andersson.scheduler.services

import com.bia.andersson.scheduler.TestEnvironment
import com.bia.andersson.scheduler.config.job.{Daily, JobConfig, TimeOptions}
import org.scalatest.{FlatSpec, ShouldMatchers}

class JobConfigReaderServiceTest
    extends FlatSpec
    with ShouldMatchers
    with TestEnvironment {
  override val ioService: IOService = new IOService
  override val jobConfigReaderService: JobConfigReaderService =
    new JobConfigReaderService

  "readJobConfigs" should "read and parse configurations successfully." in {
    val result = jobConfigReaderService.readJobConfigs()
    result should have size 1
    result should contain(
      JobConfig(
        "Test Command",
        "ping google.com -c 10",
        Console,
        Daily,
        TimeOptions(12, 10)
      )
    )
  }
}
