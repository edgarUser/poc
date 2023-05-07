package simulation;

import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.core.CoreDsl.rampUsers;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

public class NoteSimulation extends Simulation {

  ChainBuilder find = exec(http("getAll").get("/note"));

  HttpProtocolBuilder protocol = http.baseUrl("http://localhost:8080");
  ScenarioBuilder users = scenario("users").exec(find);

  {
    setUp(users.injectOpen(rampUsers(20).during(5))).protocols(protocol);
  }

}
