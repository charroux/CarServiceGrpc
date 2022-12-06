package carservice;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GrpcServer {
    public static void main(String[] args) {
        try {
            Server server = ServerBuilder
                    .forPort(8080)
                    .addService(new CarRentalServiceImpl()).build();
            server.start();
            server.awaitTermination();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}