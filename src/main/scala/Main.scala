import cask.MainRoutes
import java.time.ZoneId
import java.time.ZonedDateTime

object Example extends MainRoutes:
  override def host: String = "0.0.0.0"

  @cask.get("/hello")
  def hello(): String = "hello"

  private def getZoneIdForCity(city: String): Option[ZoneId] =
    import scala.jdk.CollectionConverters.*
    ZoneId.getAvailableZoneIds.asScala
      .find(_.endsWith("/" + city))
      .map(ZoneId.of)

  @cask.websocket("/websocket")
  def websocket(): cask.WsHandler =
    cask.WsHandler { channel =>
      cask.WsActor {
        case cask.Ws.Text("") => channel.send(cask.Ws.Close())
        case cask.Ws.Text(city) =>
          val text = getZoneIdForCity(city) match
            case Some(zoneId) =>
              s"Current date is: ${ZonedDateTime.now().withZoneSameInstant(zoneId)}"
            case None => s"Couldn't find time zone for city $city"
          channel.send(cask.Ws.Text(text))
      }
    }

  println("Server started on http://localhost:8080/websocket")
  initialize()
  println("done")
