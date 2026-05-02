package service;

import annotations.Pattern;
import annotations.Validate;
import annotations.username;
import database.UserRepository;
import model.Check;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;


public class AnnotationEngine {

    UserRepository userRepository = new UserRepository();
    final Check chech = new Check();
    public Check run(Object obj) throws IllegalAccessException {
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
                    }
                    else if (value.length() < username.min()) {
                        System.out.println("username juda qisqa");
                    }
                    else if (userRepository.existsUserName(value)) {
                        System.out.println("Bu username allaqachon mavjud!");
                    }
                    else {
                        chech.setUserName(true);
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            if (field.isAnnotationPresent(Validate.class)) {
                field.setAccessible(true);
                Validate annotation = field.getAnnotation(Validate.class);
                String vlaue;
                try {
                    vlaue = field.get(obj).toString();
                    if (vlaue.length()> annotation.min()){
                        chech.setPassword(true);
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            if (field.isAnnotationPresent(Pattern.class)){
                field.setAccessible(true);
                Pattern annotation = field.getAnnotation(Pattern.class);
                String regex = annotation.regexp();
                String message = annotation.msg();

                var value = field.get(obj);

                if (value!=null){
                    String str = value.toString();
                    if (!str.matches(regex)){
                        System.out.println(field.getName()+" xato "+message);
                        chech.setEmail(false);
                        chech.setPhone(false);
                    }else{
                        chech.setEmail(true);
                        chech.setPhone(true);
                    }
                }

            }
        }
        return chech;
            
    }
    
}