import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import scala.concurrent.Await
import scala.concurrent.duration.Duration

object WebServer extends App {
  implicit val system = ActorSystem("my-system")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val route =
    path("hello") {
      get {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Say hello to akka-http</h1>"))
      }
    } ~ path("_ah" / "health") {
      get {
        complete("OK")
      }
    }

  val f = Http().bindAndHandle(route, "0.0.0.0", 8080)
  Await.ready(f, Duration.Inf)
}
