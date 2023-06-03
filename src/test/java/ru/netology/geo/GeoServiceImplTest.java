package ru.netology.geo;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class GeoServiceImplTest {
    @Test
    public void testGeoServiceImpl() {
        Location expected;
        expected = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
        GeoService gsi = new GeoServiceImpl();
        int hash1 = Objects.hash(gsi.byIp("172.0.32.11").getCountry(), gsi.byIp("172.0.32.11").getCity(), gsi.byIp("172.0.32.11").getStreet(), gsi.byIp("172.0.32.11").getBuiling());
        int hash2 = Objects.hash(expected.getCountry(), expected.getCity(), expected.getStreet(), expected.getBuiling());
        assertEquals(hash1, hash2);
        expected = new Location("New York", Country.USA, " 10th Avenue", 32);
        hash1 = Objects.hash(gsi.byIp("96.44.183.149").getCountry(), gsi.byIp("96.44.183.149").getCity(), gsi.byIp("96.44.183.149").getStreet(), gsi.byIp("96.44.183.149").getBuiling());
        hash2 = Objects.hash(expected.getCountry(), expected.getCity(), expected.getStreet(), expected.getBuiling());
        assertEquals(hash1, hash2);
    }
}