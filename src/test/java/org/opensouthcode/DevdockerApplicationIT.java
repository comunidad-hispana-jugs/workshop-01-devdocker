//package org.opensouthcode;
//
//import static com.jayway.restassured.RestAssured.when;
//
//import org.apache.http.HttpStatus;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Value;
//
//import com.jayway.restassured.RestAssured;
//
//public class DevdockerApplicationIT {
//
//    ReservationRepository repository;
//
//    Reservation pedros;
//    Reservation angels;
//    Reservation anas;
//
////    @Value("${tomcat.port}")
////    int port;
//
//    @Test
//    public void canFetchAll1() {
//    	System.out.println("Reservation save successfull.");
//    	System.out.println("");
//    }
//
//    @Test
//    public void canFetchAll2() {
//    	System.out.println("Reservation update successfull.");
//    	System.out.println("");
//    }
//
//    @Test
//    public void canFetchAll3() {
//    	System.out.println("Reservation deletion successfull.");
//    	System.out.println("");
//    }
//
////    @Test
////    public void canFetchAll() {
////    	RestAssured.baseURI = "http://www.google.com";
////    	RestAssured.port = 80;
////        when().
////                get().
////        then().
////                statusCode(HttpStatus.SC_OK);
////    }
////
////    private static final boolean action() {
////    	return true;
////    }
//
//}
////RestAssured.baseURI = "http://192.168.99.100";
////RestAssured.port = port;
////when().
////        get("/reservations").
////then().
////        statusCode(HttpStatus.SC_OK);
