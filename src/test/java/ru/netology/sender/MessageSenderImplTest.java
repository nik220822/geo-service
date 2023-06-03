package ru.netology.sender;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MessageSenderImplTest {
    @Test
    public void testMessageSenderImplEn() {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.123.12.19");
        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(Mockito.anyString())).thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Mockito.<Country>any())).thenReturn("Welcome");
        MessageSender messageSenderIml = new MessageSenderImpl(geoService, localizationService);
        String s = messageSenderIml.send(headers);
        assertEquals("Welcome", s);
    }

    @Test
    public void testMessageSenderImplRu() {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.0.32.11");
        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(Mockito.anyString())).thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Mockito.<Country>any())).thenReturn("Добро пожаловать!");
        MessageSender messageSenderIml = new MessageSenderImpl(geoService, localizationService);
        assertEquals("Добро пожаловать!", messageSenderIml.send(headers));
    }
}