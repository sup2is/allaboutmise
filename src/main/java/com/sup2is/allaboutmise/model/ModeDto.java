package com.sup2is.allaboutmise.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ModeDto {

    private String name;
    private String value;
    private String grade;
    private double max;

    public static List<ModeDto> createModeDtoList(List<Mode> mode) {
        return mode.stream()
                .map(m -> new ModeDto(m.getName(), m.getValue(), m.getGrade(), m.getMax()))
                .collect(toList());
    }

}
