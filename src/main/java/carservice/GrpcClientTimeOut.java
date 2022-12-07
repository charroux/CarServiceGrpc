package carservice;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.carservice.Car;
import io.grpc.carservice.CarRentalServiceGrpc;
import io.grpc.carservice.GetCarsRequest;
import io.grpc.carservice.Invoice;
import io.grpc.stub.StreamObserver;

import java.util.Iterator;

public class GrpcClientReturnStream {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        CarRentalServiceGrpc.CarRentalServiceBlockingStub stub = CarRentalServiceGrpc.newBlockingStub(channel);

        Iterator<Car> cars = stub.getCars(GetCarsRequest.newBuilder().build());
        while(cars.hasNext()){
            Car car = cars.next();
            System.out.println(car);
        }

        channel.shutdown();
    }
}
