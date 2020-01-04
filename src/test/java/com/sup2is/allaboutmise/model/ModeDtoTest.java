package com.sup2is.allaboutmise.model;

import org.junit.Test;

import java.util.List;
import static org.junit.Assert.*;

public class ModeDtoTest {

    @Test
    public void ModeToModeDto변환() throws Exception{
        //given
        List<ModeDto> modes = ModeDto.createModeDtoList(Mode.getModes());
        //when

        //then
        assertNotNull(modes);
        System.out.println(modes.size());
        System.out.println(modes);
    }

}