import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {

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
        Mockito.when(localizationService.locale(Mockito.<Country>any())).thenReturn("Добро пожаловать");
        MessageSender messageSenderIml = new MessageSenderImpl(geoService, localizationService);
        assertEquals("Добро пожаловать", messageSenderIml.send(headers));//Кажется, в код закралась ошибка. Так как в данном случае сообщение должно было быть на английском. Ведь Location здесь ("New York", ...). Строка 38.
    }

    @Test
    public void testGeoServiceImpl() {
        Location expected;
        expected = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
        GeoService gsi = new GeoServiceImpl();
//        GeoService gsi = Mockito.mock(GeoServiceImpl.class);
//        Mockito.when(gsi.byIp(Mockito.anyString())).thenReturn(Mockito.<Location>any());
        int hash1 = Objects.hash(gsi.byIp("172.0.32.11").getCountry(), gsi.byIp("172.0.32.11").getCity(), gsi.byIp("172.0.32.11").getStreet(), gsi.byIp("172.0.32.11").getBuiling());
        int hash2 = Objects.hash(expected.getCountry(), expected.getCity(), expected.getStreet(), expected.getBuiling());
        assertEquals(hash1, hash2);
        expected = new Location("New York", Country.USA, " 10th Avenue", 32);
        hash1 = Objects.hash(gsi.byIp("96.44.183.149").getCountry(), gsi.byIp("96.44.183.149").getCity(), gsi.byIp("96.44.183.149").getStreet(), gsi.byIp("96.44.183.149").getBuiling());
        hash2 = Objects.hash(expected.getCountry(), expected.getCity(), expected.getStreet(), expected.getBuiling());
        assertEquals(hash1, hash2);
    }

    @Test
    public void testLocalizationServiceImpl() {
        Country c;
        LocalizationService ls = new LocalizationServiceImpl();
        c = Country.RUSSIA;
        assertEquals("Добро пожаловать", ls.locale(c));
        c = Country.USA;
        assertEquals("Welcome", ls.locale(c));
        c = Country.BRAZIL;
        assertEquals("Welcome", ls.locale(c));
    }
}

