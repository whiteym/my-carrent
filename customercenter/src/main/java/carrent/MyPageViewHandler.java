package carrent;

import carrent.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Date;

@Service
public class MyPageViewHandler {


    @Autowired
    private MyPageRepository myPageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenBooked_then_CREATE_1 (@Payload Booked booked) {
        try {

            if (!booked.validate()) return;

            // view 객체 생성
            MyPage myPage = new MyPage();
            // view 객체에 이벤트의 Value 를 set 함
            myPage.setBookingId(booked.getId());
            myPage.setProductId(booked.getProductId());
            myPage.setQty(booked.getQty());
            //myPage.setStartDate(booked.getBookingFrom());
            //myPage.setEndDate(booked.getBookingTo());
            myPage.setBookingStatus(booked.getStatus());
            // view 레파지 토리에 save
            myPageRepository.save(myPage);
        
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenCarRented_then_UPDATE_1(@Payload CarRented carRented) {
        try {
            if (!carRented.validate()) return;
                // view 객체 조회
            List<MyPage> myPageList = myPageRepository.findByBookingId(carRented.getBookingId());
            for(MyPage myPage : myPageList){
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                myPage.setBookingStatus(carRented.getStatus());        
                // view 레파지 토리에 save
                myPageRepository.save(myPage);
            }
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenCarReturned_then_UPDATE_2(@Payload CarReturned carReturned) {
        try {
            if (!carReturned.validate()) return;
                // view 객체 조회
            List<MyPage> myPageList = myPageRepository.findByBookingId(carReturned.getBookingId());
            for(MyPage myPage : myPageList){
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                myPage.setBookingStatus(carReturned.getStatus());
                // view 레파지 토리에 save
                myPageRepository.save(myPage);
            }
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}