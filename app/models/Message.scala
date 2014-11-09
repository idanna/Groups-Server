package models

/**
 * Created by idannahum on 8/20/14.
 * Should use pattern matching for the different messages.
 */
case class Message(creatorId: String, timestamp: String, likesCount: Int, replays: Array[Message])
