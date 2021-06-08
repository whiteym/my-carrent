package carrent;

import carrent.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired StoreRepository storeRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverBooked_PrepareCar(@Payload Booked booked){

        if(booked.isMe()){            
            Store store = new Store();
            store.setBookingId(booked.getId());
            store.setStatus("CarRentStarted");

            storeRepository.save(store);
        }     
            
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverBookingCancelled_DeleteBooking(@Payload BookingCancelled bookingCancelled){       

        if(bookingCancelled.isMe()){            
            Store store2 = new Store();
            store2.setBookingId(bookingCancelled.getId());
            store2.setStatus("BookingCancelled");

            storeRepository.save(store2);
        }  
            
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
