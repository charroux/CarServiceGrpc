package carservice;

import io.grpc.carservice.*;
import io.grpc.stub.StreamObserver;

public class CarRentalServiceImpl extends CarRentalServiceGrpc.CarRentalServiceImplBase {
    @Override
    public void getCars(GetCarsRequest request, StreamObserver<Car> responseObserver) {

        System.out.println("getCars server call");

        for (int i = 1; i <= 5; i++) {
            Car car = Car.newBuilder()
                    .setPrice(100)
                    .setBrand("Ferrari")
                    .setPlateNumber("AA" + i)
                    .build();
            responseObserver.onNext(car);
        }
    }

    @Override
    public StreamObserver<Car> rentCars(StreamObserver<Invoice> responseObserver) {

        return new StreamObserver<Car>() {
            int price = 0;
            @Override
            public void onNext(Car car) {
                System.out.println("onNext server receives a car: " + car);
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

}
