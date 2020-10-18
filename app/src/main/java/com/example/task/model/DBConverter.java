package com.example.task.model;

import androidx.room.TypeConverter;

import com.example.task.enums.TaskState;

import java.sql.Date;
import java.util.UUID;

public class DBConverter {

    public static class DateConverter {
        @TypeConverter
        public Date longToDate(long date) {
            return new Date(date);
        }

        @TypeConverter
        public long dateToLong(Date date) {
            return date.getTime();
        }

    }

    public static class UUIDConverter {
        @TypeConverter
        public String uuidToString(UUID uuid) {
            return uuid.toString();
        }

        @TypeConverter
        public UUID stringToUUID(String string) {
            return UUID.fromString(string);
        }
    }

    public static class EnumConverter {
        @TypeConverter
        public String enumToString(TaskState taskState) {
            return taskState.toString();
        }

        @TypeConverter
        public TaskState stringToEnum(String string) {
            return TaskState.valueOf(string);
        }
    }
}
