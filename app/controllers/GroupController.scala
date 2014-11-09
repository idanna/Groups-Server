package controllers

import org.reactivecouchbase.play.crud.CouchbaseCrudSourceController
import org.reactivecouchbase.play.PlayCouchbase
import play.api.Play.current
import play.api.mvc.{Action, EssentialAction}
import play.api.libs.json._
import scala.Some

/**
 * Created by idannahum on 8/20/14.
 */
object GroupController extends CouchbaseCrudSourceController[models.Group] {
  def bucket = PlayCouchbase.bucket("Analytics_Hadoop")

  def getMembers(id: String): EssentialAction = Action.async {
    res.get(id).map {
      case None       => NotFound(s"ID '${id}' not found")
      case Some(tid)  => {
        val jsObj = Json.toJson(tid._1)(res.writer).as[models.Group]
        Ok( jsObj.members.toString )
      }
    }
  }

//  def addMember(id: String) = Action.async { request =>
//    res.get(id).map {
//      case None => NotFound(s"ID '${id}' not found")
//      case Some(tid) => {
//        val jsObj = Json.toJson(tid._1)(res.writer).as[models.Group]
//        val updatedGroup = models.Group(jsObj.creatorId, jsObj.adminId, jsObj.securityLevel, jsObj.members ++ Array(request.body.toString), jsObj.messages)
//        res.update(id, updatedGroup).map{ _ => Ok(Json.obj("id" -> id)) }
//        Ok("updated")
//      }
//    }
//  }

}
