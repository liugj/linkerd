package io.buoyant.linkerd.admin

import com.twitter.finagle.http.{Request, Response, Status}
import com.twitter.finagle.Service
import com.twitter.util.Future

private[admin] class DashboardHandler extends Service[Request, Response] {
  lazy val html = dashboardHtml

  override def apply(req: Request): Future[Response] = req.path match {
    case "/dashboard" =>
      AdminHandler.mkResponse(html)
    case _ =>
      Future.value(Response(Status.NotFound))
  }

  def dashboardHtml = {
    AdminHandler.html(
      content = s"""
        <div class="row text-center">
          Welcome to the beta dashboard!
        </div>
        <hr>
        <div class="row text-center test-div">
        </div>
        <hr>
      """,
      csses = Seq("dashboard.css"),
      javaScripts = Seq("dashboard.js")
    )
  }
}
