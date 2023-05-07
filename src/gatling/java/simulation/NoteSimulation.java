package simulation;

import static io.gatling.javaapi.core.CoreDsl.StringBody;
import static io.gatling.javaapi.core.CoreDsl.csv;
import static io.gatling.javaapi.core.CoreDsl.feed;
import static io.gatling.javaapi.core.CoreDsl.rampUsers;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.FeederBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

public class NoteSimulation extends Simulation {

  FeederBuilder<String> feeder = csv("notes.scv").random();

  ChainBuilder create = feed(feeder).exec(
          http("createNotes")
              .put("/note")
              .body(StringBody("{\"id\": \"#{noteId}\",\"description\": \"#{description}\",\"created\": \"#{created}\"}")));
  ChainBuilder find = feed(feeder).exec(
      http("getUserById").get("/note/#{noteId}"));

  HttpProtocolBuilder protocol = http.baseUrl("http://localhost:8080").contentTypeHeader("application/json");
  ScenarioBuilder createUsers = scenario("createNotes").exec(create);
  ScenarioBuilder findUser = scenario("findNotes").exec(find);


  {
    setUp(
        createUsers.injectOpen(rampUsers(40).during(5)),
        findUser.injectOpen(rampUsers(1000).during(20))
    ).protocols(protocol);
  }

}
