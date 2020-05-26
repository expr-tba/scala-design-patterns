package com.bia.andersson.scheduler.registry

import com.bia.andersson.scheduler.actors.{ActorFactory, ActorFactoryComponent}
import com.bia.andersson.scheduler.config.app.AppConfigComponent
import com.bia.andersson.scheduler.dao.{
  DaoService,
  DaoServiceComponent,
  DatabaseService,
  DatabaseServiceComponent,
  MigrationComponent
}
import com.bia.andersson.scheduler.io.IOServiceComponent
import com.bia.andersson.scheduler.services.JobConfigReaderServiceComponent

object ComponentRegistry
    extends AppConfigComponent
    with IOServiceComponent
    with JobConfigReaderServiceComponent
    with DatabaseServiceComponent
    with MigrationComponent
    with DaoServiceComponent
    with ActorFactoryComponent {
  override val appConfigService: ComponentRegistry.AppConfigService =
    new AppConfigService

  override val ioService: ComponentRegistry.IOService = new IOService

  override val jobConfigReaderService
      : ComponentRegistry.JobConfigReaderService = new JobConfigReaderService

  override val databaseService: DatabaseService = new H2DatabaseService

  override val migrationService: ComponentRegistry.MigrationService =
    new MigrationService

  override val daoService: DaoService = new DaoServiceImpl

  override val actorFactory: ActorFactory = new ActorFactoryImpl
}
