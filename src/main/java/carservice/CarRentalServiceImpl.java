package carservice;

import io.grpc.carservice.*;
import io.grpc.stub.StreamObserver;

import java.awt.desktop.PreferencesEvent;

public class CarRentalServiceImpl extends CarRentalServiceGrpc.CarRentalServiceImplBase {
    @Override
    public StreamObserver<Car> bookingCars(StreamObserver<Invoice> responseObserver) {
        return new StreamObserver<Car>() {
            int price = 0;
            @Override
            public void onNext(Car car) {
                System.out.println("onNext of bookingCars receives a car: " + car);
                price = price + car.getPrice();
                responseObserver.onNext(Invoice.newBuilder().setPrice(price).build());
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public void waitForACar(GetCarsRequest request, StreamObserver<Car> responseObserver) {
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Car car = Car.newBuilder().setPrice(100).setBrand("Ferrari").setPlateNumber("11AA22").build();
        responseObserver.onNext(car);
    }

    @Override
    public StreamObserver<Car> rentCars(StreamObserver<Invoice> responseObserver) {
        return new StreamObserver<Car>() {
            int price = 0;
            @Override
            public void onNext(Car car) {
                System.out.println("onNext of rentCars receives a car: " + car);
                price = price + car.getPrice();
            }

            @Override
            public void onError(Throwable t) {
            }

            @Override
            public void onCompleted() {
                responseObserver.onNext(Invoice.newBuilder().setPrice(price).build());
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public void getCars(GetCarsRequest request, StreamObserver<Car> responseObserver) {

        System.out.println("getCars call");

        for (int i = 1; i <= 5; i++) {
            Car car = Car.newBuilder()
                    .setPrice(100)
                    .setBrand("Ferrari")
                    .setPlateNumber("AA" + i)
                    .build();
            responseObserver.onNext(car);
        }
    }

}
