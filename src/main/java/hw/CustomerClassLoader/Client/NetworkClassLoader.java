package hw.CustomerClassLoader.Client;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;

import java.util.concurrent.CompletableFuture;

/**
 * @author hw 2017.1.4
 * 自定义类加载器，加载网络类文件
 */
public class NetworkClassLoader extends ClassLoader {
  private String host;
  private int port;
  private HttpClient httpClient;

  public NetworkClassLoader(String host, int port) {
    this.host = host;
    this.port = port;
    httpClient = Vertx.vertx().createHttpClient();
  }

  @Override
  public Class findClass(String name) {

    CompletableFuture<byte[]> future = loadClassData(name);
    Class<?> clazz = null;

    try {
      byte[] b = future.get();
      clazz = defineClass(name, b, 0, b.length);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return clazz;
  }

  //从服务器获取类文件，返回对应的字节码数组
  private CompletableFuture<byte[]> loadClassData(String name) {
    CompletableFuture<byte[]> future = new CompletableFuture<>();

    String path = getRequestPath(name);
    httpClient.getNow(port, host, path, httpResponse -> { //收到响应时的回调函数
      httpResponse.handler(response -> { //响应体接收完毕时的回调函数
        byte[] clazz = response.getBytes();

        future.complete(clazz);
      });
    });

    return future;
  }

  private String getRequestPath(String name) {
    return "/" + name.replace('.', '/') + ".class";
  }
}
