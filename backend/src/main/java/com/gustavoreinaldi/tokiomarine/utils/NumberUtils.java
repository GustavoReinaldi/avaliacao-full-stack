package com.gustavoreinaldi.tokiomarine.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberUtils {
    public static Double twoDecimalsOnly(Double value) {
        return BigDecimal.valueOf(value)
                .setScale(2, RoundingMode.CEILING)
                .doubleValue();
    }
}
