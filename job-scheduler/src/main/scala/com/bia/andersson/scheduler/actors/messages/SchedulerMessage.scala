package com.bia.andersson.scheduler.actors.messages

import com.bia.andersson.scheduler.config.job.{JobConfig, JobType}

sealed trait SchedulerMessage
final case class Work(name: String, command: String, jobType: JobType)
final case class Done(
    name: String,
    command: String,
    jobType: JobType,
    success: Boolean
)
final case class Schedule(configs: List[JobConfig])
