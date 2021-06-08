package carrent;

import carrent.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PolicyHandler{
    @Autowired BookingRepository bookingRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverCarRented_UpdateStatus(@Payload CarRented carRented){

        if(carRented.isMe()){
            //Optional<Order> optionalOrder = orderRepository.findById(shipped.getOrderId());
            Optional<Booking> optionalBooking = bookingRepository.findById(carRented.getBookingId());
            Booking booking = optionalBooking.get();

            booking.setStatus(carRented.getStatus());
            bookingRepository.save(booking);
        }
            
    }
    
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverCarReturned_UpdateStatus(@Payload CarReturned carReturned){

        if(!carReturned.validate()) return;

        System.out.println("\n\n##### listener UpdateStatus : " + carReturned.toJson() + "\n\n");

        // Sample Logic //
        Booking booking = new Booking();
        bookingRepository.save(booking);
            
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
