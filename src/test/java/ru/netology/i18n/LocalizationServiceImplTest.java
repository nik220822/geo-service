package ru.netology.i18n;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceImplTest {
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