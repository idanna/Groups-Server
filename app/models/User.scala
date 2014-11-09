package models

import play.api.libs.json.Json

/**
 * Created by idannahum on 8/20/14.
 */
case class User(name: String, email: String, groups: Array[String])

object User {
  implicit val fmt = Json.format[User]
}
