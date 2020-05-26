package com.bia.andersson.scheduler.actors

import akka.actor.Actor
import com.bia.andersson.scheduler.actors.messages.{Done, Work}
import com.bia.andersson.scheduler.config.job.{Console, Sql}
import com.bia.andersson.scheduler.dao.DaoService
import com.typesafe.scalalogging.LazyLogging

import sys.process._

class Worker(daoService: DaoService) extends Actor with LazyLogging {
  private def doWork(work: Work): Unit = {
    work.jobType match {
      case Console =>
        val result = work.command.!
        sender ! Done(work.name, work.command, work.jobType, result == 0)
      case Sql =>
        val connection = daoService.getConnection()
        try {
          val statement = connection.prepareStatement(work.command)
          val result: List[String] = daoService.executeSelect(statement) { rs =>
            val metadata   = rs.getMetaData
            val numColumns = metadata.getColumnCount
            daoService.readResultSet(rs) { row =>
              (1 to numColumns)
                .map(i => row.getObject(i))
                .mkString("\t")
            }
          }
          logger.info("SQL query results: ")
          result.foreach(r => logger.info(r))
          sender ! Done(work.name, work.command, work.jobType, success = true)
        } finally connection.close()
    }
  }
  override def receive: Receive = {
    case w @ Work(_, _, _) => doWork(w)
  }
}
