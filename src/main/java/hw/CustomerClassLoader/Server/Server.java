package hw.CustomerClassLoader.Server;


import io.vertx.core.Vertx;

/**
 * @author hw 2017.1.2
 * Web服务器, 监听8888端口
 */
public class Server {
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(ServerVerticle.class.getName(), stringAsyncResult -> {
      if(stringAsyncResult.succeeded()) {
        System.out.println("Succeed to deploy http server");
      } else {
        System.out.println(stringAsyncResult.cause());
      }
    });
  }
}