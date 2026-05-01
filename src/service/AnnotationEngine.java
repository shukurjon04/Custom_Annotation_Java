package service;

import annotations.username;
import database.UserRepository;

import java.lang.reflect.Field;


public class AnnotationEngine {

    UserRepository userRepository = new UserRepository();
    public void run(Object obj){
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()){
            if (field.isAnnotationPresent(username.class)){
                field.setAccessible(true);
                username username = field.getAnnotation(username.class);
                String value;
                try {
                    value = (String)field.get(obj);

                    if (value == null || value.isBlank()) {
                        System.out.println(username.msg());
                        continue;
                    }

                    // length check
                    if (value.length() < username.min()) {
                        System.out.println("username juda qisqa");
                        return;
                    }

                    if (value.length() > username.max()) {
                        System.out.println("username juda uzun");
                        return;
                    }

                    if (userRepository.existsUserName(value)) {
                        System.out.println("Bu username allaqachon mavjud!");
                        return;
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        System.out.println("Hush  kelibsz");
            
    }
    
}