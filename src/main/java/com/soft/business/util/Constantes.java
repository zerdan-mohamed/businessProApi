package com.soft.business.util;

import java.text.SimpleDateFormat;
import java.util.Locale;

public interface Constantes {

    public static final String UUID_REGEX = "([A-Z0-9]{8}-[A-Z0-9]{4}-[A-Z0-9]{4}-[A-Z0-9]{4}-[A-Z0-9]{12})";
    public static final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
}
