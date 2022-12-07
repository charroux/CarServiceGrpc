package carservice;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.carservice.Car;
import io.grpc.carservice.CarRentalServiceGrpc;
import io.grpc.carservice.Invoice;
import io.grpc.stub.StreamObserver;

public class GrpcClientSendStreamReturnStream {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        CarRentalServiceGrpc.CarRentalServiceStub nonBlockingStub = CarRentalServiceGrpc.newStub(channel);

        StreamObserver<Invoice> invoiceObserver = new StreamObserver<Invoice>() {
            @Override
            public void onNext(Invoice invoice) {
                System.out.println("onNext client receives: " + invoice);
            }

            @Override
            public void onError(Throwable t) {

            }
            @Override
            public void onCompleted() {
            }
        };

        StreamObserver<Car> carsObserver = nonBlockingStub.bookingCars(invoiceObserver);

        Car ferrari = Car.newBuilder().setPlateNumber("11AA22").setPrice(100).setBrand("Ferrari").build();
        carsObserver.onNext(ferrari);

        Car porshe = Car.newBuilder().setPlateNumber("33BB44").setPrice(200).setBrand("porshe").build();
        carsObserver.onNext(porshe);

        carsObserver.onCompleted();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        channel.shutdown();
    }
}
