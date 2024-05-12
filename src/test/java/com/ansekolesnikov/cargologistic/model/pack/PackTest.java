package com.ansekolesnikov.cargologistic.model.pack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PackTest {
    @Test
    void testPackWidthForTypes1to5() {
        Pack pack1 = new Pack(1);
        assertEquals(1, pack1.getWidth());

        Pack pack2 = new Pack(2);
        assertEquals(2, pack2.getWidth());

        Pack pack3 = new Pack(3);
        assertEquals(3, pack3.getWidth());

        Pack pack4 = new Pack(4);
        assertEquals(4, pack4.getWidth());

        Pack pack5 = new Pack(5);
        assertEquals(5, pack5.getWidth());
    }

    @Test
    void testPackWidthForTypes6and9() {
        Pack pack6 = new Pack(6);
        assertEquals(3, pack6.getWidth());

        Pack pack9 = new Pack(9);
        assertEquals(3, pack9.getWidth());
    }

    @Test
    void testPackWidthForTypes7and8() {
        Pack pack7 = new Pack(7);
        assertEquals(4, pack7.getWidth());

        Pack pack8 = new Pack(8);
        assertEquals(4, pack8.getWidth());
    }

    @Test
    void testSetCarId() {
        Pack pack = new Pack(1);
        pack.setCarId(123);
        assertEquals(123, pack.getCarId());
    }

    /*
    @Test
    void testGetType() {
        Pack pack = new Pack(2);
        assertEquals(2, pack.getCode());
    }

     */
}
