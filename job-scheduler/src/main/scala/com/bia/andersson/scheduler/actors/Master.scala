package com.bia.andersson.scheduler.actors

import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

import akka.actor.{Actor, ActorRef, Cancellable, Props}
import akka.routing.RoundRobinPool
import com.bia.andersson.scheduler.actors.messages.{Done, Schedule, Work}
import com.bia.andersson.scheduler.config.job.{Daily, Hourly}
import com.typesafe.scalalogging.LazyLogging

import scala.collection.mutable.ListBuffer
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global

class Master(numWorkers: Int, actorFactory: ActorFactory)
    extends Actor
    with LazyLogging {
  val cancelabels = ListBuffer[Cancellable]()

  val router: ActorRef = context.actorOf(
    Props(actorFactory.createWorkerActor)
      .withRouter(RoundRobinPool(numWorkers)),
    "scheduler-master-worker-router"
  )

  override def receive: Receive = {
    case Done(name, command, jobType, success) =>
      if (success) {
        logger.info("Successfully completed {} ({})", name, command)
      } else {
        logger.info(
          "Failure! Command {} ({}) returned a non-zero result code",
          name,
          command
        )
      }
    case Schedule(configs) =>
      configs.foreach { config =>
        val cancellable = this.context.system.scheduler.schedule(
          config.timeOptions
            .getInitialDelay(LocalDateTime.now(), config.frequency),
          config.frequency match {
            case Hourly => Duration.create(1, TimeUnit.HOURS)
            case Daily  => Duration.create(1, TimeUnit.DAYS)
          },
          router,
          Work(config.name, config.command, config.jobType)
        )
        cancellable +: cancelabels
        logger.info("Scheduled: {}", config)
      }
  }
  override def postStop(): Unit = {
    cancelabels.foreach(_.cancel())
  }
}
