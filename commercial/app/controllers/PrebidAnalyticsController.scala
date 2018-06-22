package commercial.controllers

import common.Logging
import conf.Configuration.commercial.prebidAnalyticsStream
import conf.switches.Switches
import play.api.mvc._

import scala.concurrent.ExecutionContext

class PrebidAnalyticsController(val controllerComponents: ControllerComponents) extends BaseController with Logging {

  private implicit val ec: ExecutionContext = controllerComponents.executionContext

  private val stream = Analytics.storeJsonBody(Switches.prebidAnalytics, prebidAnalyticsStream, log) _

  def insert(): Action[String] = Action(parse.text) { implicit request =>
    stream(request)
  }
}
