package com.company;

import java.util.Map;

/**
 * Created by intern on 4/10/15.
 */
public class MapTest extends MapInterfaceTest<Integer, String> {

    public MapTest() {
        super(true, true, true, true, true, true);
    }

    @Override
    protected Map<Integer, String> makeEmptyMap() throws UnsupportedOperationException {
        return new LastTouchMapImpl<Integer, String>();
    }

    @Override
    protected Map<Integer, String> makePopulatedMap() throws UnsupportedOperationException {
        LastTouchMapImpl<Integer, String> result = new LastTouchMapImpl<Integer, String>();
        result.put(1, "1");
        result.put(2, "2");
        result.put(3, "3");
        return result;
    }

    @Override
    protected Integer getKeyNotInPopulatedMap() throws UnsupportedOperationException {
        return 4;
    }

    @Override
    protected String getValueNotInPopulatedMap() throws UnsupportedOperationException {
        return "4";
    }
}