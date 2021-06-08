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
    @Autowired ProductRepository productRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverBookingCancelled_IncreaseStock(@Payload BookingCancelled bookingCancelled){

        if(bookingCancelled.isMe()){          
          Product product = productRepository.findByProductId(Long.valueOf(bookingCancelled.getProductId()));
          product.setStock(product.getStock() + bookingCancelled.getQty());

          productRepository.save(product);

        }
            
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverCarReturned_IncreaseStock(@Payload CarReturned carReturned){

        if(carReturned.isMe()){          
            Product product = productRepository.findByProductId(Long.valueOf(carReturned.getProductId()));
            product.setStock(product.getStock() + carReturned.getQty());
  
            productRepository.save(product);
  
          }
            
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
