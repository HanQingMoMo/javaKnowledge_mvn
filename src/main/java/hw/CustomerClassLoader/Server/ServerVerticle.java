package hw.CustomerClassLoader.Server;

import io.vertx.core.AbstractVerticle;

/**
 * @author hw 2017.1.4
 * 监听8888端口，并将webroot下的资源文件发送给客户端
 */
public class ServerVerticle extends AbstractVerticle {

  @Override
  public void start() {

      vertx.createHttpServer().requestHandler(req -> {
        String file = req.path();
        req.response().sendFile("webroot/" + file);
      }).listen(8888);
  }
}