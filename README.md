# gRPC

## Service definition in Proto Buffers language

https://github.com/charroux/CarServiceGrpc/blob/main/src/main/proto/carservice.proto

## Server side implementation

https://github.com/charroux/CarServiceGrpc/blob/main/src/main/java/carservice/CarRentalServiceImpl.java

## Bukld and run

Open the project in Intellij or import this project as a Gradle project in Eclipse.

Build the project within Eclipse or Intellij.

Look at the generated code in the build folder.

Launch the main at the server side: https://github.com/charroux/CarServiceGrpc/blob/main/src/main/java/carservice/GrpcServer.java

## Make a single request from the client, response as a stream (sequence of messages))

https://github.com/charroux/CarServiceGrpc/blob/main/src/main/java/carservice/GrpcClientReturnStream.java

## The client sends a sequence of messages then waits for an asynchronous response

https://github.com/charroux/CarServiceGrpc/blob/main/src/main/java/carservice/GrpcClientSendStream.java

## The client receive a dynamic response each time the server receives a request

https://github.com/charroux/CarServiceGrpc/blob/main/src/main/java/carservice/GrpcClientSendStreamReturnStream.java
