package controllers

import org.reactivecouchbase.play.crud.CouchbaseCrudSourceController
import org.reactivecouchbase.play.PlayCouchbase
import play.api.Play.current
import play.api.mvc.{Action, EssentialAction}
import play.api.libs.json._

/**
 * Created by idannahum on 8/20/14.
 */
object UserController extends CouchbaseCrudSourceController[models.User] {

  def bucket = PlayCouchbase.bucket("Analytics_Hadoop")

  def joinGroup(id: String): EssentialAction = Action.async { request =>
      res.get(id).map {
        case None => NotFound(s"ID '${id}' not found")
        case Some(tid) => {
          val user = Json.toJson(tid._1)(res.writer).as[models.User]
          // validate that group exist.
          val r = GroupController.res.get(request.body.toString).map {
            case None => NotFound(s"Group ID '${request.body.toString}' not found")
            case Some(tGroup) => {
              res.update(id, models.User(user.name, user.email, user.groups ++ Array(request.body.toString)))
              Ok("Added")
            }

          }

//          r.

           Ok("updated")
        }
      }

    }

}
