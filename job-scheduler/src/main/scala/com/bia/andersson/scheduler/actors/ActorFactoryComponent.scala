package com.bia.andersson.scheduler.actors

import com.bia.andersson.scheduler.config.app.AppConfigComponent
import com.bia.andersson.scheduler.dao.DaoServiceComponent

trait ActorFactory {
  def createMasterActor: Master
  def createWorkerActor: Worker
}

trait ActorFactoryComponent {
  this: AppConfigComponent with DaoServiceComponent =>

  val actorFactory: ActorFactory

  class ActorFactoryImpl extends ActorFactory {
    override def createMasterActor: Master =
      new Master(appConfigService.workers, this)

    override def createWorkerActor: Worker = new Worker(daoService)
  }
}
