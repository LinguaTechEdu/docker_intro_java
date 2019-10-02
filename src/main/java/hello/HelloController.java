package hello;

import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisException;

import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;


@RestController
public class HelloController {
    private static final Logger LOGGER = Logger.getLogger(HelloController.class.getName());

    private Jedis jedis = new Jedis();

    private String pageTitle = "Welcome to Docker with Java";
    private String hostName;
    private Object visits;
    

    @RequestMapping("/")
    public String index() {
        try {
            hostName = InetAddress.getLocalHost().getHostName();
            visits = jedis.incr("counter");
        } catch (UnknownHostException e) {
            hostName = "Unknown";
            LOGGER.log(Level.INFO, "UNKNOWN HOST ERROR - {0}", e.getMessage());
        } catch (JedisConnectionException e) {
            visits = "Couldn't connect to Redis server.";
            LOGGER.log(Level.INFO, "CONNECTION ERROR - {0}", e.getMessage());
        }

        String html = String.format("<h1>%1$s</h1><div><h3>Hostname: %2$s</h3><h3>Visits: %3$s</h3></div>", pageTitle, hostName, visits);
        return html;
    }
}