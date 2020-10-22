package com.example.task.database;

import androidx.room.TypeConverter;

import com.example.task.enums.TaskState;

import java.util.Date;
import java.util.UUID;

public class Converters {
    @TypeConverter
    public static Long dateToTime(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static Date timeToDate(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static String uuidToString(UUID uuid) {
        return uuid.toString();
    }

    @TypeConverter
    public static UUID stringToUUID(String string) {
        return UUID.fromString(string);
    }

    @TypeConverter
    public static String enumToString(TaskState taskState) {
        return taskState.toString();
    }

    @TypeConverter
    public static TaskState stringToEnum(String string) {
        return TaskState.valueOf(string);
    }
}
