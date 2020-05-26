package com.bia.andersson.scheduler.services

import java.io.File

import com.bia.andersson.scheduler.config.app.AppConfigComponent
import com.bia.andersson.scheduler.config.job.{
  JobConfig,
  JobFrequencySerializer,
  JobTypeSerializer
}
import com.bia.andersson.scheduler.io.IOServiceComponent
import com.typesafe.scalalogging.LazyLogging
import org.json4s.{DefaultFormats, FileInput, _}
import org.json4s.jackson.JsonMethods._

trait JobConfigReaderServiceComponent {
  this: AppConfigComponent with IOServiceComponent =>

  val jobConfigReaderService: JobConfigReaderService

  class JobConfigReaderService() extends LazyLogging {
    private val customSerializers = List(
      JobFrequencySerializer,
      JobTypeSerializer
    )
    implicit val formats
        : Formats = DefaultFormats ++ customSerializers + JobConfig.jobConfigFieldSerializer

    def readJobConfigs(): List[JobConfig] =
      ioService
        .getAllFilesWithExtension(
          appConfigService.configPath,
          appConfigService.configExtension
        )
        .flatMap { path =>
          try {
            val config = parse(FileInput(new File(path))).extract[JobConfig]
            Some(config)
          } catch {
            case ex: Throwable =>
              logger.error("Error reading config: {}", path, ex)
              None
          }
        }
  }
}
