//package com.example.advert.util;
//
//
//import com.example.advert.repository.AdvertRepository;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockedStatic;
//import org.mockito.Mockito;
//import org.springframework.boot.test.context.SpringBootTest;
//import static org.mockito.ArgumentMatchers.any;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//import java.time.ZonedDateTime;
//
//@SpringBootTest
//public class DateFormatUtilsTest {
//
//    @InjectMocks
//    private DateFormatUtils dateFormatUtils;
//
//    @Test
//    void addFromLocalTime(){
//
//        MockedStatic<DateFormatUtils> utilities = Mockito.mockStatic(DateFormatUtils.class);
//        Mockito.when(DateFormatUtils.addFromLocalTime(ZonedDateTime.now())).thenReturn(ZonedDateTime.now());
//
//
//    }
//
//}
