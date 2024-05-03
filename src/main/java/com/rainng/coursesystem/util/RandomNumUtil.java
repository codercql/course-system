package com.rainng.coursesystem.util;

import lombok.Data;

/**
 * @program: course-system
 * @description:
 * @author: chenqiulu
 * @create: 2024-05-03 23:13
 **/
@Data
public class RandomNumUtil {
    public static int getRandomNum(){
        int max = 10000000, min = 1;
        long randomNum = System.currentTimeMillis();
        return (int) (randomNum % (max - min) + min);
    }
}
