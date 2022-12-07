package carservice;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.carservice.Car;
import io.grpc.carservice.CarRentalServiceGrpc;
import io.grpc.carservice.GetCarsRequest;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class GrpcClientTimeOut {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        CarRentalServiceGrpc.CarRentalServiceBlockingStub stub = CarRentalServiceGrpc.newBlockingStub(channel);

        try{
            Car car = stub.withDeadlineAfter(10000, TimeUnit.MILLISECONDS).waitForACar(GetCarsRequest.newBuilder().build());
        } catch(Exception e){
            System.out.println(("Time out occured"));
        }
        
        channel.shutdown();
    }
}
