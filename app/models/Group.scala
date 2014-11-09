package models

import play.api.libs.json.Json

/**
 * Created by idannahum on 8/20/14.
 */
case class Group (creatorId: String, adminId: String, securityLevel: String, members: Array[String], messages: Array[String])

object Group {
  implicit val fmt = Json.format[Group]
}